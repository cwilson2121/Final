/*Replace this comment with your name.*/
import java.util.*;

public class Graph
{
    private boolean[][] adjacencyMatrix;
    private int[][] adjacencyList;
    private int[] inDegreeArray;
    private int[] outDegreeArray;
    private int vertexCount;
    private int edgeCount;

    //This constructor reads in a directed graph from System.in.
    //Precondition:  The input is correctly formatted as follows.
    //    The first line contains an integer n representing the number of vertices
    //    in the graph.  Subsequent lines contain the adjacency list representation
    //    of the graph, i.e., an integer u followed by 0 or more integers v such that
    //    (u, v) is an edge in the graph.  Vertices are numbered 0 through n - 1.
    public Graph()
    {
        Scanner input = new Scanner(System.in);
        
        //Read the number of vertices from the first line.
        vertexCount = input.nextInt();
        
        //Skip the rest of the line.
        input.nextLine();
        
        //Allocate space for the adjacency list and matrix.
        adjacencyMatrix = new boolean[vertexCount][vertexCount];
        adjacencyList = new int[vertexCount][];
        
        //Allocate space for the degree lists.
        inDegreeArray = new int[vertexCount];
        outDegreeArray = new int[vertexCount];

        //Read in the remaining lines.
        while(input.hasNextLine())
        {
            Scanner line = new Scanner(input.nextLine());
            
            //Get the first integer on the line.  This represents the u in each edge (u, v).
            int u = line.nextInt();
            
            //Get the remaining integers on the line.  These represent each v in (u, v).
            while(line.hasNextInt())
            {
                int v = line.nextInt();
                
                //We now have the edge (u, v).  Process accordingly.
                
                //If the edge hasn't already been read in somewhere else...
                if(!adjacencyMatrix[u][v])
                {
                    outDegreeArray[u]++;
                    inDegreeArray[v]++;
                    adjacencyMatrix[u][v] = true;
                    edgeCount++;
                }
            }
        }    

        //We now have everything except the adjacency list.  Create that now from the
        //adjacency matrix.
        for(int u = 0; u < vertexCount; u++)
        {
            adjacencyList[u] = new int[outDegreeArray[u]];
           
            int degreeCounter = 0;
              
            for(int v = 0; v < vertexCount; v++)
                if(adjacencyMatrix[u][v])
                    adjacencyList[u][degreeCounter++] = v;
        }
     }
    
    //This method writes the graph to System.out in the format specified above.
    public void output()
    {
        System.out.println(vertexCount);
        
        for(int i = 0; i < vertexCount; i++)
        {
            System.out.print("" + i + " ");
            for(int degree = 0; degree < outDegreeArray[i]; degree++)
                System.out.print("" + adjacencyList[i][degree] + " ");
                
            System.out.println();
        }
    }
    
    public ArrayList<Integer> DFS()
    {
        //The following ArrayList will hold DFS numbers and be returned by this method.  Both of the following 
        //ArrayLists have an extra cell at the end to hold the current counter, so it can be passed to the recursive
        //helper function and can be modified correctly.
        ArrayList<Integer> dfsNumbers = new ArrayList<Integer>(vertexCount+1);
        ArrayList<Integer> finishingNumbers = new ArrayList<Integer>(vertexCount+1);
        
        //Initialize all DFS numbers and all finishing numbers to 0.
        for(int i = 0; i < vertexCount; i++)
        {
            dfsNumbers.add(0);
            finishingNumbers.add(0);
        }
        
        //Initialize the DFS number and finishing number counters to 1.
        dfsNumbers.add(1);
        finishingNumbers.add(1);
            
        //Perform the DFS on each unvisited vertex.
        /*Replace this comment with the appropriate code.*/
    }
    
    //Recursive DFS helper method
    private void dfsHelper(int currentVertex, ArrayList<Integer> dfsNumbers, 
                                              ArrayList<Integer> finishingNumbers)
    {
        //Visit the current vertex by setting its DFS number.
        /*Replace this comment with the appropriate code.*/
        
        //Increment the DFS-number counter.
        /*Replace this comment with the appropriate code.*/
        
        //March through the adjacency list of the current vertex.
        for(/*Replace this comment with the appropriate code.*/)
        {
            /*Replace this comment with the appropriate code.*/
            
            //Identify and output edge types.
            System.out.print("(" + u + ", " + v + ") : ");

            //If v hasn't been visited yet...
            if(/*Replace this comment with the appropriate code.*/)
                System.out.println("Tree Edge");
            //If v has been visited, but is not finished yet...    
            else if(/*Replace this comment with the appropriate code.*/)
                System.out.println("Back Edge");
            //If v is visited and finished, but u was visited before v...
            else if(/*Replace this comment with the appropriate code.*/)
                System.out.println("Forward Edge");
            //If v is visited and finished, but u was visited after v...
            else
                System.out.println("Cross Edge");
            
            //If the adjacent vertex hasn't yet been visited, recursively do a DFS on it.
            /*Replace this comment with the appropriate code.*/
        }
        
        //The current vertex's adjacency list is now used up.  Set its finishing number and increment the counter.
        /*Replace this comment with the appropriate code.*/
    }
}