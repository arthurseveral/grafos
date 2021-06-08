package mestrado;

import java.util.HashSet;
import java.util.Set;

public abstract class Searchable<T> {
    
    protected Graph<T> graph;

    protected Set<T> visited = new HashSet<>();
    
    protected Integer getAdjacencyUnvisitedVertex(T source) {
        Integer index = graph.getVertexs().indexOf(new Vertex<>(source));
                
        for (int i = 0; i < graph.getAdjacencyMatrix()[index].length; i++) {										
            if (graph.getAdjacencyMatrix()[index][i] == 1 && !visited.contains(graph.getVertexs().get(i).getItem())) {		
                return i;																	
            }
        }
        
        return -1;
    }
    
    public abstract void search();
    
}
