//Chris, Austin, Ethan, Cayden
//Random
import java.util.*;
import java.io.*;

public class FinalRev5
{
    private static int[][] adjacencyList;
    private static int vertexCount;
    private static int[] result;
    private static boolean[] takenVertices;

    //Find the next unused color for each vertex (greedy).
    public static void color()
    {
        int color;
        result[0] = 0;
        for (int i = 1; i < vertexCount; i++)
        {
            //Reset color to 0 for each vertex.
            color = 0;
            //Try colors until one is not taken.
            int randomVertex = nextRandomVertex();
            while (!testTouchingVertices(randomVertex, color))
            {
                color++;
            }
            result[randomVertex] = color;
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

    public static void main(String[] args) throws FileNotFoundException
    {
        //Construct the graph.
        File graphFile = new File(args[0]);
        Graph g = new Graph(graphFile);

        //Set the class variables.
        adjacencyList = g.adjacencyList;
        vertexCount = g.getVertexCount();
        result = new int[vertexCount];
        takenVertices = new boolean[vertexCount];
        for(int i = 0; i < vertexCount; i++)
        {
            takenVertices[i] = false;
        }

        //Color the vertices.
        color();
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
