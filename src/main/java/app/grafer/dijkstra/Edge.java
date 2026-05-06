package app.grafer.dijkstra;

class Edge {

    CityNode target;
    int cost;

    Edge(CityNode target, int cost) {
        this.target = target;
        this.cost = cost;
    }
}
