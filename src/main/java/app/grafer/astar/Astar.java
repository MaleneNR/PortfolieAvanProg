package app.grafer.astar;

import processing.core.PApplet;

import java.util.ArrayList;

public class Astar extends PApplet {

    public static void main(String[] args) {
        PApplet.main("app.grafer.astar.Astar");
    }

    // ----------------------------
    // GRID
    // ----------------------------

    int[][] grid = {

            {0,0,0,0,0,0,1,0},
            {1,1,0,1,1,0,1,0},
            {0,0,0,0,1,0,0,0},
            {0,1,1,0,1,1,1,0},
            {0,0,1,0,0,0,1,0},
            {1,0,1,1,1,0,1,0},
            {0,0,0,0,0,0,0,0},
            {1,1,1,1,1,1,0,0}
    };

    int rows = 8;
    int cols = 8;

    int cellSize = 80;

    Node[][] nodes = new Node[rows][cols];

    ArrayList<Node> openSet = new ArrayList<>();
    ArrayList<Node> closedSet = new ArrayList<>();

    Node start;
    Node goal;

    boolean finished = false;

    // ----------------------------
    // NODE
    // ----------------------------

    class Node {

        int row;
        int col;

        float g = Float.MAX_VALUE;
        float h = 0;
        float f = 0;

        boolean wall;

        Node previous;

        Node(int row, int col, boolean wall) {
            this.row = row;
            this.col = col;
            this.wall = wall;
        }
    }

    // ----------------------------
    // SETTINGS
    // ----------------------------

    @Override
    public void settings() {
        size(cols * cellSize, rows * cellSize);
    }

    // ----------------------------
    // SETUP
    // ----------------------------

    @Override
    public void setup() {

        // Opret nodes
        for (int r = 0; r < rows; r++) {

            for (int c = 0; c < cols; c++) {

                boolean wall = grid[r][c] == 1;

                nodes[r][c] = new Node(r, c, wall);
            }
        }

        // Helsingør
        start = nodes[0][0];

        // Rødby
        goal = nodes[7][7];

        start.g = 0;
        start.h = heuristic(start, goal);
        start.f = start.h;

        openSet.add(start);
    }

    // ----------------------------
    // DRAW
    // ----------------------------

    @Override
    public void draw() {

        background(255);

        drawGrid();
        drawPath();
    }

    // ----------------------------
    // GRID TEGNING
    // ----------------------------

    void drawGrid() {

        for (int r = 0; r < rows; r++) {

            for (int c = 0; c < cols; c++) {

                Node node = nodes[r][c];

                int x = c * cellSize;
                int y = r * cellSize;

                // Walls
                if (node.wall) {
                    fill(0);
                }

                // Visited
                else if (closedSet.contains(node)) {
                    fill(255, 0, 0);
                }

                // Open set
                else if (openSet.contains(node)) {
                    fill(255, 255, 0);
                }

                // Normal
                else {
                    fill(255);
                }

                stroke(0);

                rect(x, y, cellSize, cellSize);
            }
        }

        // Start
        fill(0, 0, 255);
        rect(start.col * cellSize,
                start.row * cellSize,
                cellSize,
                cellSize);

        // Goal
        fill(0, 255, 0);
        rect(goal.col * cellSize,
                goal.row * cellSize,
                cellSize,
                cellSize);

        fill(255);

        text("Helsingør", 20, 40);
        text("Rødby", width - 40, height - 40);
    }

    // ----------------------------
    // PATH
    // ----------------------------

    void drawPath() {

        Node current = goal;

        stroke(0,255,0);
        strokeWeight(6);

        while (current.previous != null) {

            line(
                    current.col * cellSize + cellSize/2,
                    current.row * cellSize + cellSize/2,
                    current.previous.col * cellSize + cellSize/2,
                    current.previous.row * cellSize + cellSize/2
            );

            current = current.previous;
        }
    }

    // ----------------------------
    // A*
    // ----------------------------

    void astarStep() {

        if (finished) return;

        if (openSet.isEmpty()) {

            println("Ingen vej fundet");
            finished = true;
            return;
        }

        Node current = openSet.get(0);

        for (Node node : openSet) {

            if (node.f < current.f) {
                current = node;
            }
        }

        if (current == goal) {

            println("MÅL FUNDET!");
            finished = true;
            return;
        }

        openSet.remove(current);
        closedSet.add(current);

        for (Node neighbor : getNeighbors(current)) {

            if (neighbor.wall || closedSet.contains(neighbor)) {
                continue;
            }

            float tentativeG = current.g + 1;

            if (tentativeG < neighbor.g) {

                neighbor.previous = current;

                neighbor.g = tentativeG;
                neighbor.h = heuristic(neighbor, goal);
                neighbor.f = neighbor.g + neighbor.h;

                if (!openSet.contains(neighbor)) {
                    openSet.add(neighbor);
                }
            }
        }
    }

    // ----------------------------
    // NABOER
    // ----------------------------

    ArrayList<Node> getNeighbors(Node node) {

        ArrayList<Node> neighbors = new ArrayList<>();

        int r = node.row;
        int c = node.col;

        // op
        if (r > 0)
            neighbors.add(nodes[r - 1][c]);

        // ned
        if (r < rows - 1)
            neighbors.add(nodes[r + 1][c]);

        // venstre
        if (c > 0)
            neighbors.add(nodes[r][c - 1]);

        // højre
        if (c < cols - 1)
            neighbors.add(nodes[r][c + 1]);

        return neighbors;
    }

    // ----------------------------
    // HEURISTIK
    // ----------------------------

    float heuristic(Node a, Node b) {

        // Manhattan distance
        return abs(a.row - b.row) + abs(a.col - b.col);
    }

    // ----------------------------
    // STEP MODE
    // ----------------------------

    @Override
    public void keyPressed() {

        if (key == ' ') {

            astarStep();
        }
    }
}