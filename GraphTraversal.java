import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Class for traversing a graph and finding connected components.
 */
public class GraphTraversal {

    // Adjacency list representation of the graph
    private static Map<Integer, List<Integer>> graph; // Stores the graph as an adjacency list

    // Define a final constant for the expected number of command-line arguments
    private static final int EXPECTED_NUM_ARGS = 1; // Specifies the expected number of command-line arguments

    /**
     * Main method for reading input, building the graph, and finding connected components.
     *
     * @param args Command-line arguments (expects one argument: the input file path).
     * @throws IOException If an I/O error occurs while reading the input file.
     */
    public static void main(String[] args) throws IOException {
        if (args.length != EXPECTED_NUM_ARGS) {
            // Check if the correct number of command-line arguments is provided
            System.err.println("Usage: java ConnectedComponents <input_file>");
            System.exit(1); // Exit the program if the arguments are not provided as expected
        }

        String inputFile = args[0]; // Stores the input file path provided as a command-line argument
        BufferedReader reader = new BufferedReader(new FileReader(inputFile)); // Reads input from a file
        String line;

        int graphCount = 1; // Keeps track of the number of graphs processed

        while ((line = reader.readLine()) != null) {
            // Read each line from the input file
            String[] tokens = line.split("\\s+");
            int numVertices = Integer.parseInt(tokens[0]);

            // Initialize the graph as an adjacency list
            setGraph(new HashMap<>()); // Create a new empty graph
            for (int i = 1; i <= numVertices; i++) {
                // Create an empty list for each vertex to store its neighbors
                getGraph().put(i, new ArrayList<>());
            }

            for (int i = 1; i < tokens.length; i++) {
                // Parse edge information and add edges to the graph
                String[] edge = tokens[i].replaceAll("\\(", "").replaceAll("\\)", "").split(",");
                int v1 = Integer.parseInt(edge[0]);
                int v2 = Integer.parseInt(edge[1]);
                getGraph().get(v1).add(v2);
                getGraph().get(v2).add(v1);
            }

            List<Set<Integer>> components = findConnectedComponents();

            System.out.println("Graph" + graphCount + ":");
            if (components.size() == 1) {
                // Output connected components information
                System.out.println("One connected component: " + components.get(0));
            } else {
                System.out.println(components.size() + " connected components:");
                for (Set<Integer> component : components) {
                    System.out.println(component);
                }
            }
            graphCount++;
        }
        reader.close(); // Close the input file reader
    }

    /**
     * Find connected components in the graph.
     *
     * @return A list of sets where each set represents a connected component in the graph.
     */
    private static List<Set<Integer>> findConnectedComponents() {
        List<Set<Integer>> components = new ArrayList<>(); // Stores the connected components
        Set<Integer> visited = new HashSet<>(); // Stores visited vertices

        for (int vertex : getGraph().keySet()) {
            if (!visited.contains(vertex)) {
                Set<Integer> component = new HashSet<>(); // Stores a connected component
                dfs(vertex, visited, component); // Explore and find connected components
                components.add(component);
            }
        }
        return components;
    }

    /**
     * Depth-first search (DFS) method to explore connected components.
     *
     * @param vertex    The current vertex being explored.
     * @param visited   A set of visited vertices.
     * @param component The current connected component being constructed.
     */
    private static void dfs(int vertex, Set<Integer> visited, Set<Integer> component) {
        visited.add(vertex); // Mark the vertex as visited
        component.add(vertex); // Add the vertex to the current connected component

        for (int neighbor : getGraph().get(vertex)) {
            if (!visited.contains(neighbor)) {
                // Recursively explore and add neighbors to the current component.
                dfs(neighbor, visited, component);
            }
        }
    }

    /**
     * Get the graph.
     *
     * @return The adjacency list representation of the graph.
     */
    public static Map<Integer, List<Integer>> getGraph() {
        return graph;
    }

    /**
     * Set the graph.
     *
     * @param newGraph The new adjacency list representation of the graph.
     */
    public static void setGraph(Map<Integer, List<Integer>> newGraph) {
        graph = newGraph;
    }
}