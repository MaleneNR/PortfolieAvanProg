package app.grafer.dijkstra;

import processing.core.PApplet;
import processing.core.PImage;

import java.util.*;

public class DijkstraMap extends PApplet {
    PriorityQueue<CityNode> queue;

    CityNode startNode;
    CityNode endNode;

    CityNode currentNode;

    boolean finished = false;

    PImage map;

    ArrayList<CityNode> cities = new ArrayList<>();

    public static void main(String[] args) {
        PApplet.main("app.grafer.dijkstra.DijkstraMap");
    }

    public void settings() {
        size(600, 900);
    }

    public void setup() {

        map = loadImage("C:\\Users\\malen\\Documents\\sem4\\PortfolieAvanProg\\src\\main\\java\\app\\grafer\\dijkstra\\Sjaellandskort.png");

        Integer gnsKmPris = 4;

        CityNode Helsingor       = new CityNode("Helsingør", 520, 60);
        CityNode Nodebo          = new CityNode("Nødebo", 445, 130);
        CityNode NykobingSj      = new CityNode("Nykøbing Sj.", 270, 120);
        CityNode Asnaes          = new CityNode("Asnæs", 140, 260);
        CityNode Brondby         = new CityNode("Brøndby", 445, 290);
        CityNode KirkeHyllinge   = new CityNode("Kirke Hyllinge", 310, 260);
        CityNode Dianalund       = new CityNode("Dianalund", 170, 330);
        CityNode Ringsted        = new CityNode("Ringsted", 250, 400);
        CityNode Koge            = new CityNode("Køge", 400, 400);
        CityNode Tappernoje      = new CityNode("Tappernøje", 350, 550);
        CityNode Kalvehave       = new CityNode("Kalvehave", 390, 655);


// NABOER / EDGES

        Helsingor.addNeighbour(NykobingSj, 155 + (70 * gnsKmPris)); // færge
        Helsingor.addNeighbour(Nodebo, 20 * gnsKmPris);
        Helsingor.addNeighbour(Brondby, 250 + (53 * gnsKmPris)); // "by-afgift"

        Brondby.addNeighbour(Koge, 32 * gnsKmPris);

        Nodebo.addNeighbour(KirkeHyllinge, 51 * gnsKmPris);

        KirkeHyllinge.addNeighbour(Ringsted, 40 * gnsKmPris);
        KirkeHyllinge.addNeighbour(Koge, 45 * gnsKmPris);

        NykobingSj.addNeighbour(Asnaes, 18 * gnsKmPris);

        Asnaes.addNeighbour(Dianalund, 46 * gnsKmPris);

        Dianalund.addNeighbour(Ringsted, 28 * gnsKmPris);

        Ringsted.addNeighbour(Tappernoje, 42 * gnsKmPris);

        Koge.addNeighbour(Tappernoje, 43 * gnsKmPris);

        Tappernoje.addNeighbour(Kalvehave, 27 * gnsKmPris);

        cities.add(Helsingor);
        cities.add(Nodebo);
        cities.add(NykobingSj);
        cities.add(Asnaes);
        cities.add(Brondby);
        cities.add(KirkeHyllinge);
        cities.add(Dianalund);
        cities.add(Ringsted);
        cities.add(Koge);
        cities.add(Tappernoje);
        cities.add(Kalvehave);

        startNode = Helsingor;
        endNode = Kalvehave;

        startNode.distance = 0;
        startNode.state = NodeState.CURRENT;

        queue = new PriorityQueue<>(
                Comparator.comparingInt(n -> n.distance)
        );

        queue.add(startNode);

        frameRate(0.2f);
    }

    public void draw() {


        background(0);

        image(map, 0, 0, width, height);

        for (CityNode city : cities) {

            for (Edge e : city.neighbours) {

                stroke(255);

                line(city.x, city.y,
                        e.target.x, e.target.y);

                int midX = (city.x + e.target.x) / 2;
                int midY = (city.y + e.target.y) / 2;

                fill(255);

                text(e.cost + " kr", midX, midY);
            }
        }

        for (CityNode city : cities) {

            switch(city.state) {

                case UNVISITED:
                    fill(180);
                    break;

                case CURRENT:
                    fill(255, 255, 0);
                    break;

                case VISITED:
                    fill(0, 255, 0);
                    break;

                case PATH:
                    fill(255, 0, 0);
                    break;
            }

            ellipse(city.x, city.y, 20, 20);

            fill(255);

            text(city.name, city.x + 10, city.y);

            if(city.distance != Integer.MAX_VALUE) {
                text(city.distance + " kr",
                        city.x + 10,
                        city.y + 15);
            }
        }

        if (!finished) {
            dijkstraStep();
        }
    }

    public void dijkstraStep() {

        // Tidligere current bliver visited
        for (CityNode city : cities) {

            if (city.state == NodeState.CURRENT) {
                city.state = NodeState.VISITED;
            }
        }

        // Stop hvis queue er tom
        if (queue.isEmpty()) {

            println("Queue is empty.");
            finished = true;
            return;
        }

        // Vælg billigste node
        currentNode = queue.poll();

        println("\n========================");
        println("CURRENT NODE: " + currentNode.name);
        println("Current total cost: " +
                currentNode.distance + " kr");

        currentNode.state = NodeState.CURRENT;

        // Hvis vi har fundet destinationen
        if (currentNode == endNode) {

            println("\nSHORTEST PATH FOUND!");
            println("Destination: " + endNode.name);
            println("Total cost: " +
                    endNode.distance + " kr");

            reconstructPath();

            finished = true;
            return;
        }

        // Undersøg naboer
        for (Edge edge : currentNode.neighbours) {

            CityNode neighbour = edge.target;

            int newDistance =
                    currentNode.distance + edge.cost;

            println("\nChecking neighbour: "
                    + neighbour.name);
            println("Edge cost: "
                    + edge.cost + " kr");
            println("Possible new cost: "
                    + newDistance);

            // Billigere vej fundet
            if (newDistance < neighbour.distance) {

                println("UPDATED!");

                neighbour.distance = newDistance;

                neighbour.previous = currentNode;

                queue.add(neighbour);

                println(neighbour.name
                        + " now costs "
                        + neighbour.distance + " kr");

            } else {

                println("No cheaper path found.");
            }
        }
    }

    public void reconstructPath() {

        println("\n===== SHORTEST PATH =====");

        CityNode current = endNode;

        while (current != null) {

            println(current.name);

            current.state = NodeState.PATH;

            current = current.previous;
        }
    }
}