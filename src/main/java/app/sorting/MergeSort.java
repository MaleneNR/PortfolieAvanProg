package app.sorting;

import java.util.Arrays;

public class MergeSort {

    public static void main(String[] args) {
        int[] numbers = {5,2,4,3,9};
        mergeSort(numbers);
    }


    //Kompleksiteten for algoritmen er O(n log n) - men fylder meget, da det optager plads for hvert element.
    // Dvs. den plads denne sortering fylder er lineær O(n)
    private static void mergeSort(int[] numbers) {
        if (numbers.length > 1) {                               //Base case for at undgå stack overflow ved rekursivt kald.
            int mid = numbers.length/2;                         //Finder midten af array
            int[] left = new int[mid];                          //Deler i hhv venstre og højre
            int[] right = new int[numbers.length-mid];

            for (int i = 0; i < mid; i++) {                     //fylder venstre array
                left[i] = numbers[i];
            }

            for (int i = 0; i < numbers.length - mid; i++) {   //og højre
                right[i] = numbers[mid + i];
            }

            mergeSort(left);                                    //kalder sig selv indtil vi rammer basecase
            mergeSort(right);

            merge(numbers, left,right);                         //merger dem sammen sorteret, så vi til sidst ender med et sorteret numbers array

            System.out.println(Arrays.toString(numbers));
        }
    }

    private static void merge(int[] numbers, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {                      //sætter venstre side i'ne element ind på k'ne plads i numbers,
                numbers[k] = left[i];                       //hvis den er mindre eller lig med højre j'ne element
                k++;
                i++;
            } else {
                numbers[k] = right[j];                      //ellers sætter vi højre j'ne element ind på k'ne plads i numbers
                k++;
                j++;
            }
        }
            while(i< left.length){                          //restende i venstre array bliver sat ind i numbers, hvis højre er brugt op
                numbers[k] = left[i];
                i++;
                k++;
            }

            while (j < right.length) {                      //restende i højre array -||-, hvis venstre er brugt op.
            numbers[k] = right[j];
            j++;
            k++;
            }


    }

}
