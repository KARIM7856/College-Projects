
import java.io.InputStream;
import java.util.*;

// TODO : Incidence Matrix

public class mGraph {
    public int V;				//# Vertices
    public int E;			        // # Edges
    public LinkedList<Integer>[] adj;	// Adjacency List
    public LinkedList<Integer>[] adj2;
    public int[][] adjMat; // adjacency matrix.
    public int[][ ] incMat; // incidence matrix.
    private int currentIncColum; // incidence matrix related variable.
    private String euler;

    public String getEuler() {
		return euler;
	}
	public void setEuler(String euler) {
		this.euler = euler;
	}
	mGraph(int vertex, int edges)
    {
        V = vertex;
        E = edges;
        currentIncColum = 0;
        adj = new LinkedList[V];
        adj2 = new LinkedList[V];
        adjMat = new int[V][V];
        incMat = new int[V][E];
        for (int i =0; i < V; i++)
        {
            adj[i] = new LinkedList<Integer>();          // Initializing Adjacency List
            adj2[i] = new LinkedList<Integer>(); 
        }

        for (int j = 0; j < V; j++)
        {
            for (int k = 0; k < V; k++)
            {
                adjMat[j][k] = 0;               // Adjacency Matrix Initialization
            }
        }
        
        for (int i = 0; i < V; i++) {
			for (int j = 0; j < E; j++) {
				incMat[i][j] = 0;  // incidence matrix initialization.
			}
		}
        
    }
    mGraph(Scanner in){
        this(in.nextInt(), in.nextInt());

        for (int i =0; i < E; i++)
        {
            int v1 = in.nextInt();
            int v2 = in.nextInt();
            addEdge(v1, v2);
        }
    }

    void addEdge(int v1, int v2)
    {
        adj[v2].add(v1);
        adj2[v2].add(v1);
        adj2[v1].add(v2);
        
        adjMat[v1][v2] = 1;
        incMat[v1][currentIncColum] = 1;
        incMat[v2][currentIncColum++] = 1;   
    }

    Iterable<Integer> adj(int v){
        return adj[v];				// Returns adjacent vertices to vertex(v)
    }


    public String toString(){
        String s = "";
        for (int v = 0; v < V; v++)
        {
            s += v + ": ";
            for (int j : this.adj(v))			// Utility Function
            {
                s+= j + " ";
            }
            s+= "\n";
        }

        return s;
    }
    
    public int checkEuler() {
    	int counter = 0;
    	int result = 0;
    	for(int i = 0; i < adj2.length; i++) {
    		if(adj2[i].size() % 2 != 0) {
    			System.out.println(adj[i].size());
    			counter++;
    			result = i;
    		}
    	}
    	if(counter == 0) {
    		System.out.println("Euler curcite");
    		return 0;
    	}
    	else if(counter == 2) {
    		System.out.println("Euler Path");
    		return result;
    	}
    	else {
    		System.out.println("no Euler");
    		return -1;
    	}
    }
    
    public ArrayList<Integer> fleurys() {
    	euler = "";
    	
    	int condition = checkEuler();
    	LinkedList <Integer>[] availEdges = adj2;
    	ArrayList<Integer> ePath = new ArrayList();
    	Integer i = condition;
    	Integer nextVertix = 0;
    	
    	int counter = 0;
    	if(condition == 0) {
    		
    		
    		ePath.add(0);
    		euler += 0;
    		
    		
    		while(counter < E) {
    			
    			for(int j = 0; j < availEdges[i].size(); j++) {
    				
    				if(availEdges[availEdges[i].get(j)].size() == 1) {
    					if(availEdges[i].size() > 1) {
    						
    						continue;
    					}
    					else {
    						
    						ePath.add(availEdges[i].get(j));
    						euler += availEdges[i].get(j);
    						nextVertix = availEdges[i].get(j);
    						availEdges[i].remove(Integer.valueOf(j));
    						availEdges[nextVertix].remove(i);
    						counter++;
    						break;
    					}
    				}
    				else {
    					ePath.add(availEdges[i].get(j));
    					euler += availEdges[i].get(j);
    					nextVertix = availEdges[i].get(j);
    					availEdges[nextVertix].remove(i);
    					availEdges[i].remove(j);
    					counter++;
    					break;
    				}
    			}
    			i = nextVertix;
    		}
    		//ePath.add(0);
    	}
    	else if(condition == -1) {
    		ePath.add(-1);
    		euler += "-1";
    	}
    	else {
    		i = condition;
    		ePath.add(condition);
    		euler += condition;
    		while(counter < E) {
    			
    			for(int j = 0; j < availEdges[i].size(); j++) {
    				if(availEdges[availEdges[i].get(j)].size() == 1) {
    					if(availEdges[i].size() > 1) {
    						continue;
    					}
    					else {
    						ePath.add(availEdges[i].get(j));
    						euler += availEdges[i].get(j);
    						nextVertix = availEdges[i].get(j);
    						availEdges[i].remove(Integer.valueOf(j));
    						availEdges[nextVertix].remove(i);
    						counter++;
    						break;
    					}
    				}
    				else {
    					ePath.add(availEdges[i].get(j));
    					euler += availEdges[i].get(j);
    					nextVertix = availEdges[i].get(j);
    					availEdges[nextVertix].remove(i);
    					availEdges[i].remove(j);
    					counter++;
    					break;
    				}
    			}
    			i = nextVertix;
    		}
    	}
		return ePath;
    }

    
}
