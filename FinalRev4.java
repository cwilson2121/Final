//Chris, Austin, Ethan, Cayden
//Instances
import java.util.*;
import java.io.*;

public class FinalRev4
{
    private static int[][] adjacencyList;
    private static int vertexCount;
    private static int[] result;
    private static int[] instanceArray;
    private static int bestInstance = 0;

    //Find the next unused color for each vertex.
    public static void instanceColor()
    {
        for (int instance = 0; instance < vertexCount; instance++)
        {
            int color;
            int colorCount;
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
            //Find the best color over and over to fill this array.
            instanceArray[instance] = colorCount();
        }
        //Find the best instance.
        bestInstance = bestInstance();
        //Re-color result up to that point.
        color(bestInstance);
    }

    //Re-color the graph after finding the best instance.
    private static void color(int correctInstance)
    {
        for (int instance = 0; instance < correctInstance; instance++)
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
    }

    //Test to see if a color has been taken by a connected vertex.
    private static boolean testTouchingVertices(int vertex, int color)
    {
        for (int i = 0; i < adjacencyList[vertex].length; i++)
            if (result[adjacencyList[vertex][i]] == color)
                return false;
        return true;
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
        result = new int[vertexCount];
        instanceArray = new int[vertexCount];

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
