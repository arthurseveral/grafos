package mestrado;

import java.util.Stack;

/**
 * Busca em profundidade
 */
public class DFS<T> extends Searchable<T> {

    private Stack<T> stack = new Stack<>();

    public DFS(Graph<T> graph) {
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

        stack.push(graph.getVertexs().get(0).getItem());

        System.out.println(graph.getVertexs().get(0));

        while (!stack.empty()) {
            int adjacencyUnvisitedVertexIndex = getAdjacencyUnvisitedVertex(stack.peek());
            if (adjacencyUnvisitedVertexIndex == -1) {
                stack.pop();
            } else {
                final Vertex<T> adjacencyUnvisitedVertex = graph.getVertexs().get(adjacencyUnvisitedVertexIndex);
                        
                visited.add(adjacencyUnvisitedVertex.getItem());

                stack.push(adjacencyUnvisitedVertex.getItem());
                
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
        
        DFS<Character> dfs = new DFS<>(graph);
        
        dfs.search();
    }

}
