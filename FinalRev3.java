//Chris, Austin, Ethan, Cayden
import java.util.*;
import java.io.*;

public class FinalRev3
{
    private static int[][] adjacencyList;
    private static int vertexCount;
    private static int[] result;

    //Find the next unused color for each vertex (greedy).
    public static void color()
    {
        int color;
        result[0] = 0;
        for (int vertex = 1; vertex < vertexCount; vertex++)
        {
            //Reset color to 0 for each vertex.
            color = 0;
            //Try colors until one is not taken.
            while (!testTouchingVertices(vertex, color))
            {
                color++;
            }
            result[vertex] = color;
        }
    }

    public static boolean testTouchingVertices(int vertex, int color)
    {
        for (int i = 0; i < adjacencyList[vertex].length; i++)
            if (result[adjacencyList[vertex][i]] == color)
                return false;
        return true;
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
