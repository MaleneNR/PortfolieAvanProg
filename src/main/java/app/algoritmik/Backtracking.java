package app.algoritmik;

public class Backtracking {
    static final int N = 4;
    static int[][] maze = {
            {1, 0, 1, 1},
            {1, 1, 1, 0},
            {0, 0, 1, 1},
            {1, 1, 0, 1}
    };
    static int[][] path = new int[N][N];

    public static void main(String[] args) {
        if (solveMaze(0, 0)) {
            printPath(path);
        } else {
            System.out.println("Ingen løsning fundet.");
        }
    }

    static boolean solveMaze(int row, int col) {

        //Udenfor labyrintens grænser.
        if(row < 0 || col < 0 || row >= N ||col >= N){
            return false;
        };
        //Er det en sti eller et blindt felt == 0
        if(maze[row][col] != 1){
            return false;
        }
        //Er det allerede en del af path?
        if (path[row][col] == 1){
            return false;
        }

        //Vi er nået højre nederste hjørne, hvilket var målet.
        if(row == N-1 && col == N-1){
            path[row][col] = 1;
            return true;
        }

        path[row][col] = 1;

        // Vi prøver at finde næste mulige vej
        if (solveMaze(row, col + 1)){
            return true;
        } //Højre

        if (solveMaze(row + 1, col)){
            return true;
        } //Ned

        if (solveMaze(row - 1, col)){
            return true;
        } //Op

        if (solveMaze(row, col - 1)){
            return true;
        } //Venstre


        //Vi finder ikke nogen udvej og sætter derfor seneste path til 0, så vi ikke går herhen igen, og returnere false.
        path[row][col] = 0;

        return false;
    }

    static void printPath(int[][] path){
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                System.out.print(path[i][j]);
            }
            System.out.println("");

}
    }

}
