import java.util.*;

// This class represents the whole social network
public class SocialNetwork {

    // This is our graph (Adjacency List)
    // Key = User
    // Value = List of users they follow
    private Map<String, List<String>> graph;

    // Constructor
    public SocialNetwork() {
        graph = new HashMap<>();
    }

    // Add a user
    public void addUser(String user) {
        graph.putIfAbsent(user, new ArrayList<>());
    }

    // Add a follow relationship
    public void addFollow(String from, String to) {
        graph.get(from).add(to);
    }

    // Print the network
    public void printNetwork() {
        for (String user : graph.keySet()) {
            System.out.println(user + " -> " + graph.get(user));
        }
    }

    // Degree centrality
    public void degreeCentrality() {
        System.out.println("\nDegree Centrality:");
        for (String user : graph.keySet()) {
            System.out.println(user + ": " + graph.get(user).size());
        }
    }

    // BFS shortest path
    public void shortestPath(String start, String end) {
        Queue<String> queue = new LinkedList<>();
        Map<String, String> parent = new HashMap<>();
        Set<String> visited = new HashSet<>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            String current = queue.poll();

            if (current.equals(end)) {
                break;
            }

            for (String neighbor : graph.get(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    parent.put(neighbor, current);
                    queue.add(neighbor);
                }
            }
        }

        // Reconstruct path
        List<String> path = new ArrayList<>();
        String step = end;

        while (step != null) {
            path.add(step);
            step = parent.get(step);
        }

        Collections.reverse(path);

        System.out.println("\nShortest Path:");
        System.out.println(path);
    }

    // Main method
    public static void main(String[] args) {

        SocialNetwork sn = new SocialNetwork();

        // Add users
        sn.addUser("Alice");
        sn.addUser("Bob");
        sn.addUser("Carol");
        sn.addUser("David");

        // Add follow relationships
        sn.addFollow("Alice", "Bob");
        sn.addFollow("Alice", "Carol");
        sn.addFollow("Bob", "David");
        sn.addFollow("Carol", "Bob");

        System.out.println("THREADS SOCIAL NETWORK ANALYSIS\n");

        sn.printNetwork();
        sn.degreeCentrality();
        sn.shortestPath("Alice", "David");
    }
}
