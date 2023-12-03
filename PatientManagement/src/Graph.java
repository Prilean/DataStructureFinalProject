import java.util.*;

public class Graph {
 
    int dist[];
    private Set<Integer> settled;
    private PriorityQueue<MNode> pq;
    private int V;
    List<List<MNode> > adj;
    public Graph(int V)
    {
 
        
        this.V = V;
        dist = new int[V];
        settled = new HashSet<Integer>();
        pq = new PriorityQueue<MNode>(V, new MNode());
    }
 
    public void dijkstra(List<List<MNode> > adj, int src)
    {
        this.adj = adj;
 
        for (int i = 0; i < V; i++)
            dist[i] = Integer.MAX_VALUE;
 
       
        pq.add(new MNode(src, 0));
 
       
        dist[src] = 0;
 
        while (settled.size() != V) {
 
            
            if (pq.isEmpty())
                return;
 
            
            int u = pq.remove().MNode;
 
            
            if (settled.contains(u))
 
                
                continue;
 
            
            settled.add(u);
 
            e_Neighbours(u);
        }
    }
 
    
    private void e_Neighbours(int u)
    {
 
        int edgeDistance = -1;
        int newDistance = -1;
 
        
        for (int i = 0; i < adj.get(u).size(); i++) {
            MNode v = adj.get(u).get(i);
 
            if (!settled.contains(v.MNode)) {
                edgeDistance = v.cost;
                newDistance = dist[u] + edgeDistance;
 

                if (newDistance < dist[v.MNode])
                    dist[v.MNode] = newDistance;
 
                pq.add(new MNode(v.MNode, dist[v.MNode]));
            }
        }
    }

}
class MNode implements Comparator<MNode> {
	
    public int MNode;
    public int cost;
 
    public MNode() {}
    public MNode(int MNode, int cost)
    {
        this.MNode = MNode;
        this.cost = cost;
    }
 
    @Override public int compare(MNode MNode1, MNode MNode2)
    {
 
        if (MNode1.cost < MNode2.cost)
            return -1;
 
        if (MNode1.cost > MNode2.cost)
            return 1;
 
        return 0;
    }
}