package app.grafer.dijkstra.signes;

import java.util.*;

public class Dijkstra {

    public static void main(String[] args) {
        Integer gnsKmPris = 4;

        WeightedNode Helsingør = new WeightedNode("Helsingør");
        WeightedNode Nødebo = new WeightedNode("Nødebo");
        WeightedNode NykøbingSj = new WeightedNode("Nykøbing Sj.");
        WeightedNode Asnæs = new WeightedNode("Asnæs");
        WeightedNode Brøndby = new WeightedNode("Brøndby");
        WeightedNode KirkeHyllinge = new WeightedNode("Kirke Hyllinge");
        WeightedNode Dianalund = new WeightedNode("Dianalund");
        WeightedNode Ringsted = new WeightedNode("Ringsted");
        WeightedNode Køge = new WeightedNode("Køge");
        WeightedNode Tappernøje = new WeightedNode("Tappernøje");
        WeightedNode Kalvehave = new WeightedNode("Kalvehave");


        Helsingør.addNeighbor(NykøbingSj, 155+(70*gnsKmPris)); //Færgeafgift = 155kr
        Helsingør.addNeighbor(Nødebo, 20*gnsKmPris);
        Helsingør.addNeighbor(Brøndby, 250+(53*gnsKmPris));  //Vi leger der er en byafgift på 250kr
        Brøndby.addNeighbor(Køge,32*gnsKmPris);
        Nødebo.addNeighbor(KirkeHyllinge, 51*gnsKmPris);
        KirkeHyllinge.addNeighbor(Ringsted, 40*gnsKmPris);
        KirkeHyllinge.addNeighbor(Køge, 45*gnsKmPris);
        NykøbingSj.addNeighbor(Asnæs, 18*gnsKmPris);
        Asnæs.addNeighbor(Dianalund, 46*gnsKmPris);
        Dianalund.addNeighbor(Ringsted, 28*gnsKmPris);
        Ringsted.addNeighbor(Tappernøje, 42*gnsKmPris);
        Køge.addNeighbor(Tappernøje, 43*gnsKmPris);
        Tappernøje.addNeighbor(Kalvehave, 27*gnsKmPris);


        findShortestPath(Helsingør, Kalvehave);
    }


    public static void findShortestPath(WeightedNode source, WeightedNode destination) {
        // En node og den node vi kom fra. Skal bruges til at printe vejen fra start til slut
        Map<WeightedNode, WeightedNode> prev = new HashMap<>();

        // Gemmer den billigste kendte dist til hver node
        Map<WeightedNode, Integer> dist = new HashMap<>();

        // Noder vi har besøgt og ikke skal afsøge igen
        Set<WeightedNode> visited = new HashSet<>();

        // Køen sorterer selv efter dist fordi NodeWithDist implementerer Comparable
        PriorityQueue<NodeWithDist> queue = new PriorityQueue<>();

        // Startnoden er 0 væk fra sig selv
        queue.add(new NodeWithDist(source, 0));
        dist.put(source, 0);

        while (!queue.isEmpty()) {
            NodeWithDist current = queue.poll();

            // Vi bryder ud af løkken hvis vi finder vores mål-node
            if (current.node.equals(destination)) break;

            // Vi går til næste iteration hvis vi allerede har besøgt denne node
            if (visited.contains(current.node)) continue;

            // Vi er i gang med at undersøge current, så den skal i visited
            // så vi ikke vender tilbage til den senere
            visited.add(current.node);

            // Vi henter alle nodens naboer ud
            for (Map.Entry<WeightedNode, Integer> entry : current.node.getNeighbors().entrySet()) {
                WeightedNode next = entry.getKey();
                int weight = entry.getValue();

                // Hvis det er en node vi før har besøgt, går vi til næste iteration
                if (visited.contains(next)) continue;

                // Currents afstand til startnode + currents afstand til nabo
                int newDist = current.dist + weight;

                // Opdater kun hvis vi har fundet en billigere vej
                if (newDist < dist.getOrDefault(next, Integer.MAX_VALUE)) {
                    dist.put(next, newDist);
                    prev.put(next, current.node);
                    // Vi putter en ny NodeWithDist i køen i stedet for at opdatere den eksisterende.
                    // Ideelt ville vi tjekke om noden allerede er i køen og opdatere dens dist,
                    // men Java's PriorityQueue understøtter ikke det effektivt (contains() og remove() er O(n)).
                    // I stedet bruger vi dist-mappet til at undgå at putte en dårligere vej i køen overhovedet.
                    // Hvis en forældet NodeWithDist alligevel popper ud, fanger visited-tjekket den.
                    queue.add(new NodeWithDist(next, newDist));
                }
            }
        }

        // Rekonstruer stien via prev
        List<String> path = new ArrayList<>();
        WeightedNode step = destination;
        while (step != null) {
            path.add(0, step.getName());
            step = prev.get(step);
        }

        System.out.println("Korteste vej: " + path);
        System.out.println("Samlet dist: " + dist.get(destination));
    }

    // Hjælpeklasse der pakker en node og dens afstand fra startnoden sammen
    // så køen selv kan sortere uden at slå op i et separat dist-map
    // Vi kan ikke bare have dist som attribut på WeighedNode fordi det der kunne være flere algoritmer
    // der kørte samtidig med forskellige startnoder og dermed forskellige distancer
    private static class NodeWithDist implements Comparable<NodeWithDist> {
        WeightedNode node;
        int dist;

        public NodeWithDist(WeightedNode node, int dist) {
            this.node = node;
            this.dist = dist;
        }

        @Override
        public int compareTo(NodeWithDist other) {
            return Integer.compare(this.dist, other.dist);
        }
    }
}