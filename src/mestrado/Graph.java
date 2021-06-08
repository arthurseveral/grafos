package mestrado;

import java.util.ArrayList;
import java.util.List;

public class Graph<T> {

    private List<Vertex<T>> vertexs = new ArrayList<>();

    private final int[][] adjacencyMatrix;

    public Graph() {
        adjacencyMatrix = new int[100][100];

        /**
         * Haja vista que o grafo é inicialmente vazio, a matrix de adjacência é inicializada com 0's
         */
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            for (int j = 0; j < adjacencyMatrix[i].length; j++) {
                adjacencyMatrix[i][j] = 0;
            }
        }
    }
    
    public void addVertex(T label) {
        vertexs.add(new Vertex<>(label));
    }
    
    public void bind(T label01, T label02) {
        Integer index01 = vertexs.indexOf(new Vertex<>(label01));
        Integer index02 = vertexs.indexOf(new Vertex<>(label02));
        
        adjacencyMatrix[index01][index02] = 1;
        adjacencyMatrix[index02][index01] = 1;
    }
    
    public List<Vertex<T>> getVertexs() {
        return this.vertexs;
    }
    
    public List<Vertex<T>> getNeighbors(T label) {
        Integer index = getVertexs().indexOf(new Vertex<>(label));
        
        List<Vertex<T>> neighbors = new ArrayList<>();
        for (int i = 0; i < getAdjacencyMatrix()[index].length; i++) {
            if (getAdjacencyMatrix()[index][i] == 1) {
                neighbors.add(getVertexs().get(i));
            }
        }
        
        return neighbors;
    }

    public int[][] getAdjacencyMatrix() {
        return adjacencyMatrix;
    }

}
