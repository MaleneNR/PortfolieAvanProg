package app.grafer;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private String name;
    private List<Node> neighbors;

    public Node(String name) {
        this.name = name;
        this.neighbors = new ArrayList<>();
    }

    public String getName() {

        return name;
    }

    public List<Node> getNeighbors() {

        return neighbors;
    }

    public void addNeighbor(Node neighbor) {

        neighbors.add(neighbor);
    }
}
