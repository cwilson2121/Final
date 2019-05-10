//Chris, Austin, Ethan, Cayden
//Build an order starting with the least neighbored vertex.
import java.util.*;
import java.io.*;

public class FinalRev7
{
    private static int[][] adjacencyList;
    private static int vertexCount;
    private static int[] result, order;


    //Find the next unused color for each vertex (greedy).
    public static void color()
    {
        int color;
        result[0] = 0;
        for (int vertex = 0; vertex < vertexCount; vertex++)
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

    private static void order()
    {
        int minLength = vertexCount;
        for (int vertex = 0; vertex < vertexCount; vertex++)
        {
            order[vertex] = vertex;
        }
        for (int i = 0; i < vertexCount-1; i++)
        {
            for (int j = 0; j < vertexCount - i -1; j++)
            {
                if (adjacencyList[order[j]].length > adjacencyList[order[j+1]].length)
                {
                    int temp = order[j];
                    order[j] = order[j+1];
                    order[j+1] = temp;
                }
            }
        }
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
        order = new int[vertexCount];

        //Order vertices such that the ones with the least number of neighbors comes first.
        order();

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
