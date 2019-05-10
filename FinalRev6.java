//Chris, Austin, Ethan, Cayden
//Random Instances
import java.util.*;
import java.io.*;

//NOT WORKING
public class FinalRev6
{
    private static int[][] adjacencyList, randomInstances;
    private static int[] result, instanceArray;
    private static boolean[] takenVertices;
    private static int vertexCount;
    private static int bestInstance = 0;

    //Find the instance of random verteces that produces the best coloring.
    public static void instanceColor()
    {
        int color;
        for (int instance = 0; instance < 1; instance++)
        {
            for (int i = 0; i < vertexCount; i++)
            {
                takenVertices[i] = false;
            }
            result[0] = 0;
            for (int vertex = 0; vertex < vertexCount; vertex++)
            {
                //Reset color to 0 for each vertex.
                color = 0;
                //Try colors until one is not taken.
                int randomVertex = nextRandomVertex();
                randomInstances[instance][vertex] = randomVertex;
                while (!testTouchingVertices(randomVertex, color))
                {
                    color++;
                }
                result[randomVertex] = color;
            }
            //Find the best color over and over to fill this array.
            instanceArray[instance] = colorCount();
        }
        //Find the best instance.
        bestInstance = bestInstance();
        //Re-color result up to that point.
        color(bestInstance);
    }

    //Find the next unused color for each vertex.
    public static void color(int correctInstance)
    {
        int color;
        result[0] = 0;
        for (int vertex = 0; vertex < vertexCount; vertex++)
        {
            //Reset color to 0 for each vertex.
            color = 0;
            //Try colors until one is not taken.

            while (!testTouchingVertices(randomInstances[correctInstance][vertex], color))
            {
                color++;
            }
            result[vertex] = color;
        }
    }

    private static boolean testTouchingVertices(int vertex, int color)
    {
        for (int i = 0; i < adjacencyList[vertex].length; i++)
            if (result[adjacencyList[vertex][i]] == color)
                return false;
        return true;
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

    //Find the number of colors in result.
    private static int colorCount()
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

    //Find the best instance of coloring the graph.
    private static int bestInstance()
    {
        int minColorCount = vertexCount;
        //Find the minimum amount of colors in any instance.
        for (int instance = 0; instance < vertexCount; instance++)
        {
            if (instanceArray[instance] < minColorCount)
            {
                minColorCount = instanceArray[instance];
            }
        }
        //Find the number instance where this coloring occured.
        for (int instance = 0; instance < vertexCount; instance++)
        {
            if (instanceArray[instance] == minColorCount)
            {
                return instance;
            }
        }
        return 0;
    }

    public static void main(String[] args) throws FileNotFoundException
    {
        //Construct the graph.
        File graphFile = new File(args[0]);
        Graph g = new Graph(graphFile);

        //Set the class variables.
        adjacencyList = g.adjacencyList;
        vertexCount = g.getVertexCount();
        randomInstances = new int[vertexCount][vertexCount];
        result = new int[vertexCount];
        takenVertices = new boolean[vertexCount];
        for(int i = 0; i < vertexCount; i++)
        {
            takenVertices[i] = false;
        }

        //Color the vertices.
        instanceColor();
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
