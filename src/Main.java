import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

//File needs to be copy pasted into the folder in intellij

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Enter the filename: ");
        String filename = keyboard.nextLine();
        AdjacencyList list = new AdjacencyList(filename);
        System.out.println("Enter starting vertex: ");
        String start = keyboard.nextLine();

        System.out.println("\nDijkstra's Algorithm: ");
        dijkstras(list, start);

        keyboard.close();
    }

    public static void dijkstras(AdjacencyList list, String start) {
        ArrayList<String> vertices = list.getVertices();
        ArrayList<ArrayList<Node>> graph = list.getGraph();
        int size = vertices.size();

        int startIndex = vertices.indexOf(start);
        if (startIndex == -1) {
            System.out.println("Starting node not found");
            return;
        }

        float[] distances = new float[size];
        boolean[] visited = new boolean[size];
        int[] previous = new int[size];

        for (int i = 0; i < size; i++) {
            distances[i] = Float.MAX_VALUE;
            visited[i] = false;
            previous[i] = -1;
        }
        distances[startIndex] = 0;
        for (int count = 0; count < size; count++) {
            int current = -1;
            float smallest = Float.MAX_VALUE;
            for (int i = 0; i < size; i++) {
                if (!visited[i] && distances[i] < smallest) {
                    smallest = distances[i];
                    current = i;
                }
            }
            if (current == -1) {
                break;
            }
            visited[current] = true;

            for (Node neighbor : graph.get(current)) {
                int neighborIndex = vertices.indexOf(neighbor.cv);
                float newDistance = distances[current] + neighbor.edge;

                if (!visited[neighborIndex] && newDistance < distances[neighborIndex]) {
                    distances[neighborIndex] = newDistance;
                    previous[neighborIndex] = current;
                }
            }
        }
        for (int i = 0; i < size; i++) {
            if (distances[i] == Float.MAX_VALUE) {
                continue;
            }
            ArrayList<String> path = new ArrayList<>();
            int current = i;
            while (current != -1) {
                path.add(vertices.get(current));
                current = previous[current];
            }

            for (int j = path.size() - 1; j >= 0; j--) {
                System.out.print(path.get(j));
                if (j != 0) {
                    System.out.print(" -> ");
                }
            }
            String distance;
            if (distances[i] == (int) distances[i]) {
                distance = String.valueOf((int) distances[i]);
            } else {
                distance = String.valueOf(distances[i]);
            }
            System.out.println(" dist: " + distance);
        }
    }
}
