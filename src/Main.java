import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

//File needs to be copy pasted into the folder in intellij

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Enter the filename: ");
        String filename = keyboard.nextLine();

        keyboard.close();
    }

    public static void dijkstras(AdjacencyList list, String start) {
        ArrayList<String> vertices = list.getVertices();
        ArrayList<ArrayList<Node>> graph = list.getGraph();
        int size = vertices.size();

        int startIndex = vertices.indexOf(start);
    }
}
