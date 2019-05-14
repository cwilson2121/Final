//Chris, Austin, Ethan, Cayden
import java.util.*;
import java.io.*;

public class FinalRev8
{
    private static int[][] adjacencyList, instances;
    private static int vertexCount;
    private static int[] result;
    private static boolean[] takenVertices;

    private static void driver()
    {
        //Find and store the instances of random order.

        for (int i = 0; i < vertexCount; i++)
        {
            instances[i] = randomColor();
        }

        //Find the biggest color from each ordering.
        int[] maxColor = new int[vertexCount];
        for (int i = 0; i < vertexCount; i++)
        {
            maxColor[i] = colorCount(instances[i]);
        }

        //Find the best instance.
        int minColorCount = vertexCount;
        //Find the minimum amount of colors in any instance.
        for (int instance = 0; instance < vertexCount; instance++)
        {
            if (maxColor[instance] < minColorCount)
            {
                minColorCount = maxColor[instance];
            }
        }
        //Find the number instance where this coloring occured.
        int instance;
        for (instance = 0; instance < vertexCount; instance++)
        {
            if (maxColor[instance] == minColorCount)
            {
                break;
            }
        }
        color(instance);
    }

    public static void color(int instance)
    {
        int color;
        result[0] = 0;
        for (int vertex = 1; vertex < vertexCount; vertex++)
        {
            //Reset color to 0 for each vertex.
            color = 0;
            //Try colors until one is not taken.
            while (!testTouchingVertices(instances[instance][vertex], color))
            {
                color++;
            }
            result[vertex] = color;
        }
    }

    //Find the next unused color for each vertex (greedy).
    private static int[] randomColor()
    {
        int[] order = new int[vertexCount];
        int color;
        result[0] = 0;
        for (int vertex = 0; vertex < vertexCount; vertex++)
        {
            for (int i = 0; i < vertexCount; i++)
            {
                takenVertices[i] = false;
            }
            //Reset color to 0 for each vertex.
            color = 0;
            //Try colors until one is not taken.
            int randomVertex = nextRandomVertex();
            order[vertex] = randomVertex;
            while (!testTouchingVertices(vertex, color))
            {
                color++;
            }
            result[vertex] = color;
        }
        return order;
    }

    private static int nextRandomVertex()
    {
        int randomVertex;
        do {
            Random rand = new Random();
            randomVertex = rand.nextInt(vertexCount-1);
            randomVertex++;
        } while(isUsed(randomVertex));
        takenVertices[randomVertex] = true;
        return randomVertex;
    }

    private static boolean isUsed(int randomVertex)
    {
        if (takenVertices[randomVertex])
        {
            return true;
        }
        return false;
    }

    public static boolean testTouchingVertices(int vertex, int color)
    {
        for (int i = 0; i < adjacencyList[vertex].length; i++)
            if (result[adjacencyList[vertex][i]] == color)
                return false;
        return true;
    }

    private static int colorCount(int[] currentInstance)
    {
        int max = 0;
        for (int vertex = 0; vertex < vertexCount; vertex++)
        {
            if (result[vertex] > max)
            {
                max = result[vertex];
            }
        }
        return max;
    }

    public static void main(String[] args) throws FileNotFoundException
    {
        //Construct the graph.
        File graphFile = new File(args[0]);
        Graph g = new Graph(graphFile);

        //Set the class variables.
        adjacencyList = g.adjacencyList;
        vertexCount = g.getVertexCount();
        result = new int[vertexCount];
        instances = new int[vertexCount][vertexCount];
        takenVertices = new boolean[vertexCount];

        //Color the vertices.
        driver();
        //Print the colors in order of vertex.
        System.out.println();
        for(int i = 0; i < result.length-1; i++)
        {
            System.out.print(result[i] + " ");
        }
        System.out.print(result[result.length-1]);
        System.out.println();
    }
}
