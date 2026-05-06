package app.grafer.dijkstra.signes;

import java.util.HashMap;
import java.util.Map;

public class WeightedNode {
    private String name;
    // Kanterne er vægtede, så vi gemmer afstanden til naboerne i et map
    private Map<WeightedNode, Integer> neighbors;

    public WeightedNode(String name) {
        this.name = name;
        this.neighbors = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public Map<WeightedNode, Integer> getNeighbors() {
        return neighbors;
    }

    public void addNeighbor(WeightedNode neighbor, int weight) {
        neighbors.put(neighbor, weight);
    }
}