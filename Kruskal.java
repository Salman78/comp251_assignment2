package A2;
import java.util.*;

public class Kruskal{

    public static WGraph kruskal(WGraph g){

        /* Fill this method (The statement return null is here only to compile) */
        DisjointSets m = new DisjointSets(g.getNbNodes()); //we create Disjoints structure 
        WGraph MSTgraph = new WGraph();
        for(int x=0; x<g.listOfEdgesSorted().size(); x++) {
            if(IsSafe(m, (g.listOfEdgesSorted()).get(x)) == true) {
                m.union(g.listOfEdgesSorted().get(x).nodes[0], g.listOfEdgesSorted().get(x).nodes[1]);
                MSTgraph.addEdge(g.listOfEdgesSorted().get(x));
            }
        }
        //(g.listOfEdgesSorted()).get(0);
        return MSTgraph;
    }

    public static Boolean IsSafe(DisjointSets p, Edge e){

        /* Fill this method (The statement return 0 is here only to compile) */
        if(p.find(e.nodes[0]) != p.find(e.nodes[1])) {
            return true;
        }
        else {
           return false; 
        }        
    
    }

    public static void main(String[] args){

        String file = args[0];
        WGraph g = new WGraph(file);
        WGraph t = kruskal(g);
        System.out.println(t);

   } 
}
