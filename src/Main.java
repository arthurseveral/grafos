import static java.lang.String.format;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Main {
    
    static class Graph<T> {
    
        private final Map<T, List<T>> adjacencyList = new HashMap<>();

        public boolean addVertex(T vertex) {
            if(!adjacencyList.containsKey(vertex)) {
                adjacencyList.put(vertex, new ArrayList<>());

                return true;
            }

            return false;
        }

        public void bind(T vertex1, T vertex2) throws Exception {
            if(!adjacencyList.containsKey(vertex1) || !adjacencyList.containsKey(vertex2)) {
                throw new Exception(format("Graph does not contain either %s or %s", vertex1, vertex2));
            }

            if(vertex1.equals(vertex2)) {
                adjacencyList.get(vertex1).add(vertex2);

                return;
            }

            adjacencyList.get(vertex1).add(vertex2);
            adjacencyList.get(vertex2).add(vertex1);
        }

        public Integer degree(T vertex) {
            if(!adjacencyList.containsKey(vertex)) {
                throw new RuntimeException(format("Graph does not contain %s", vertex));
            }

            return adjacencyList.get(vertex).size();
        }

        public Integer minDegree() {
            Integer min = Integer.MAX_VALUE;

            for (Map.Entry<T, List<T>> e : adjacencyList.entrySet()) {
                if(e.getValue().size() < min) {
                    min = e.getValue().size();
                }
            }

            return min;
        }

        public Integer maxDegree() {
            Integer max = Integer.MIN_VALUE;

            for (Map.Entry<T, List<T>> e : adjacencyList.entrySet()) {
                if(e.getValue().size() > max) {
                    max = e.getValue().size();
                }
            }

            return max;
        }

        public boolean isRegular() {
            Integer previousDegree = null;

            for (T vertex : adjacencyList.keySet()) {
                if(previousDegree == null) {
                    previousDegree = degree(vertex);
                }

                if(!Objects.equals(degree(vertex), previousDegree)) {
                    return false;
                }
            }

            return true;
        }

            public boolean isMultigraph() {
            Set<T> helper = null;

            for (Map.Entry<T, List<T>> entry : adjacencyList.entrySet()) {
                helper = new HashSet<>();
                for (T vertex : entry.getValue()) {
                    helper.add(vertex);
                }

                if(entry.getValue().size() != helper.size()) {
                    return true;
                }
            }

            return false;
        }

        public boolean isComplete() {
            return isRegular() && !isCyclic() && !isMultigraph();
        }

        public boolean isCyclic() {
            return adjacencyList.entrySet().stream().anyMatch(entry -> (entry.getValue().contains(entry.getKey())));
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();

            adjacencyList.entrySet().forEach(e -> {
                builder.append(format("%s -> ", e.getKey().toString()));

                e.getValue().forEach((adjacent) -> {
                    builder.append(format("%s ", adjacent.toString()));
                });

                builder.append("\n");
            });

            return builder.toString();
        }
    
    }
        

    public static void main(String[] args) throws Exception {
        Graph<Integer> graph = new Graph<>();
        
        graph.addVertex(3304557); /** Rio de Janeiro/RJ **/
        graph.addVertex(3550308); /** São Paulo/SP **/
        
        graph.bind(3304557, 3550308);
        graph.bind(3304557, 3304557); /** self-bind **/
        graph.bind(3550308, 3550308); /** self-bind **/
        graph.bind(3550308, 3550308); /** self-bind **/
 
        System.out.println(graph);
        
        System.out.println(graph.minDegree());
        System.out.println(graph.maxDegree());
        
        System.out.println(graph.degree(3304557));
        System.out.println(graph.degree(3550308));
        
        System.out.println(graph.isCyclic());
        System.out.println(graph.isRegular());
        System.out.println(graph.isMultigraph());
    }
    
}
