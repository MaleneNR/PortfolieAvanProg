package app.grafer.dijkstra;

import java.util.ArrayList;

class CityNode {

    String name;
    int x, y;
    NodeState state = NodeState.UNVISITED;

    int distance = Integer.MAX_VALUE;

    ArrayList<Edge> neighbours = new ArrayList<>();

    CityNode previous;
    CityNode(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    void addNeighbour(CityNode target, int cost) {
        neighbours.add(new Edge(target, cost));
    }
}