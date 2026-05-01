package app.grafer;

import java.util.*;

public class SearchStrategies {

    public static void main(String[] args) {
        // Vi bygger en simpel graf:
        Node A = new Node("A");
        Node B = new Node("B");
        Node C = new Node("C");
        Node D = new Node("D");
        Node E = new Node("E");

        A.addNeighbor(B);
        A.addNeighbor(C);
        A.addNeighbor(D);
        C.addNeighbor(E);
        // Hvad sker der hvis vi lade E have A som nabo?
        // E.addNeighbor(A);

        System.out.println("BFS starting...");
        Node foundBFS = searchBFS("F", A);
        if (foundBFS != null) {
            System.out.println("Found by BFS: " + foundBFS.getName());
        } else {
            System.out.println("Found nothing with BFS!");
        }

        System.out.println("DFS starting...");
        Node foundDFS = searchDFS("F", A);
        if (foundDFS != null) {
            System.out.println("Found by DFS: " + foundDFS.getName());
        } else {
            System.out.println("Found nothing with DFS!");
        }
    }


    public static Node searchBFS(String targetName, Node start) {
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(start); //Vi får startnoden

        while (!queue.isEmpty()) { //Mens køen ikke er tom, så
            System.out.print("Current queue: ");
            for (Node item : queue) {
                System.out.print(item.getName() + " "); //print hele køen
            }
            System.out.println();

            Node currentNode = queue.remove(); //vi fjerner den forreste i køen

            if (currentNode.getName().equals(targetName)) {
                System.out.println("Finished searching!"); //ser om vi er færdig/ved slutpunkt
                return currentNode;
            }
            else queue.addAll(currentNode.getNeighbors()); //alle elementets naboer er sat bagerst i vores kø
        }
        return null;
    }

    public static Node searchDFS(String targetName, Node start) {
        Deque<Node> stack = new ArrayDeque<>();
        stack.push(start); //starten af stacken

        while (!stack.isEmpty()) { //mens stacken ikke er tom

            System.out.print("Current stack: ");
            for (Node item : stack) {
                System.out.print(item.getName() + " "); //print hele stacken
            }
            System.out.println();

            Node currentNode = stack.pop(); //vi tager første element i stacken

            if (currentNode.getName().equals(targetName)) {
                System.out.println("Finished searching!"); //tjekker om vi er færdige
                return currentNode;
            } else {
                for (Node neighbor : currentNode.getNeighbors()) {
                    stack.push(neighbor); //alle current nodes naboer er nu først i stacken som prioritet.
                }
            }

        }

        return null;
    }



}
