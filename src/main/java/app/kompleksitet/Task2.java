package app.kompleksitet;

import java.util.*;

public class Task2 {


    public static void main(String[] args) {

        List<Integer> arrayList = new ArrayList<>();
        List<Integer> linkedList = new LinkedList<>();

        int size = 500000;
        //Vi fylder listerne
        for (int i = 0; i < size; i++) {
            arrayList.add(i);
            linkedList.add(i);
        }

        System.out.println("------------------------------------------");
        measureGet(arrayList,"Array (get)");
        measureGet(linkedList,"Linked (get)");

        measureAdd(arrayList, "Array (Add)");
        measureAdd(linkedList, "Linked (Add)");

        measureRemove(arrayList, "Array (remove)");
        measureRemove(linkedList, "Linked (remove)");

        //Linkedlist tager generelt længere tid, da man gennemsnitligt skal igennem flere led før man er på det rette index
        //Array har nemmere access til et specifikt index.
        System.out.println("------------------------------------------");
        //_____________________________
        List<Integer> arrayList2 = new ArrayList<>();
        Set<Integer> hashSet = new HashSet<>();

        // Fyld begge med samme data
        for (int i = 0; i < size; i++) {
            arrayList2.add(i);
            hashSet.add(i);
        }

        int existing = 250000;
        int notExisting = 1000000;


        measureContains(arrayList2, existing, "ArrayList");
        measureContains(hashSet, existing, "HashSet");


        measureContains(arrayList2, notExisting, "ArrayList");
        measureContains(hashSet, notExisting, "HashSet");

        //Arraylist iterere gennem hele listen for at finde det vi leder efter. O(n)
        //Hashset'et hasher der vi leder efter og springer direkte til den bucket, hvor der ligger det vi leder efter O(1)
        // worst case O(n)
        System.out.println("------------------------------------------");
        //__________________________

            Set<Movie> hashSet2 = new HashSet<>();
            Set<Movie> treeSet = new TreeSet<>();

            Movie m1 = new Movie("Titanic", 9);
            Movie m2 = new Movie("Jumanji", 8);

            hashSet2.add(m1);
            hashSet2.add(m2);

            System.out.println("Hashset contains Titanic: " + hashSet.contains(m1));
            hashSet.remove(m1);

            treeSet.add(m1);
            treeSet.add(m2);

            System.out.println("Treeset contains Titanic: " + treeSet.contains(m1));
            treeSet.remove(m1);
        System.out.println("------------------------------------------");
        measureSet(new HashSet<>(), "HashSet");
        measureSet(new TreeSet<>(), "TreeSet");


        /*
        Hashsettet bruger hashing/equals til at slå en værdi op direkte. Den
        vil derfor være O(1) som regel.

        TreeSet har brug for en comparator, men den bygger et træ som den kan
        navigere rundt i og derfor kan den gøre det med en kompleksitet på O(log n)


         */



    }

    private static void measureGet(List<Integer> list, String name) {
        long start = System.nanoTime();
        list.get(250000);
        long end = System.nanoTime();

        System.out.println(name + ": " + (end - start) + " ns");
    }

    private static void measureAdd(List<Integer> list, String name) {
        long start = System.nanoTime();
        list.add(250000, 99);
        long end = System.nanoTime();

        System.out.println(name + ": " + (end - start) + " ns");
    }

    private static void measureRemove(List<Integer> list, String name) {
        long start = System.nanoTime();
        list.remove(250000);
        long end = System.nanoTime();

        System.out.println(name + ": " + (end - start) + " ns");
    }
//_______________________________________

    private static void measureContains(Collection<Integer> collection, int value, String name) {
        long start = System.nanoTime();
        boolean result = collection.contains(value);
        long end = System.nanoTime();

        System.out.println(name + " (" + value + "): " + result + ", " + (end - start) + " ns");
    }

    //-------------------
    private static void measureSet(Set<Movie> set, String name) {
        int size = 1000;

        List<Movie> movies = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            movies.add(new Movie("Movie" + i, i));
        }
        Movie movie = new Movie("Anyone but You", 6);

        // ADD
        long startAdd = System.nanoTime();
        set.add(movie);
        long endAdd = System.nanoTime();

        // CONTAINS
        long startContains = System.nanoTime();
        set.contains(new Movie("Anyone but You", 6));
        long endContains = System.nanoTime();

        // REMOVE
        long startRemove = System.nanoTime();
        set.remove(new Movie("Anyone But You", 6));
        long endRemove = System.nanoTime();

        System.out.println(name + " ADD: " + (endAdd - startAdd)+ " ns");
        System.out.println(name + " CONTAINS: " + (endContains - startContains)+ " ns");
        System.out.println(name + " REMOVE: " + (endRemove - startRemove)+ " ns");
    }

}


