package mestrado;

import java.util.LinkedList;
import java.util.Queue;

/**
  * Busca em largura
  */
public class BFS<T> extends Searchable<T> {
   
    private Queue<T> queue = new LinkedList<>();
    
    public BFS(Graph<T> graph) {
        this.graph = graph;
    }

    @Override
    public void search() {
        if (graph.getVertexs().isEmpty()) {
            return;
        }
        
        /**
         * Inicializando a busca pelo primeiro nó armazenado
         */
        visited.add(graph.getVertexs().get(0).getItem());

        queue.offer(graph.getVertexs().get(0).getItem());
        
        System.out.println(graph.getVertexs().get(0));
        
        while (!queue.isEmpty()) {
            final T head = queue.poll();
            
            int adjacencyUnvisitedVertexIndex;
            while ((adjacencyUnvisitedVertexIndex = getAdjacencyUnvisitedVertex(head)) != -1) {
                final Vertex<T> adjacencyUnvisitedVertex = graph.getVertexs().get(adjacencyUnvisitedVertexIndex);
                        
                visited.add(adjacencyUnvisitedVertex.getItem());
                
                queue.offer(adjacencyUnvisitedVertex.getItem());
                
                System.out.println(adjacencyUnvisitedVertex);
            }
        }
    }
    
    public static void main(String[] args) {
        Graph<Character> graph = new Graph<>();
        
        graph.addVertex('A');
        graph.addVertex('B');
        graph.addVertex('C');
        graph.addVertex('D');
        graph.addVertex('E');
        
        graph.bind('A', 'B'); // AB
        graph.bind('B', 'C'); // BC
        graph.bind('A', 'D'); // AD
        graph.bind('D', 'E'); // DE
        
        BFS<Character> bfs = new BFS<>(graph);
        
        bfs.search();
    }
}
