package app.sorting;

import java.util.Arrays;

public class QuickSort {

    public static void main(String[] args) {
        int[] numbers = {5,2,4,3,9};

        int low = 0;
        int high = numbers.length-1;


        quickSort(numbers, low, high);
        System.out.println(Arrays.toString(numbers));

    }
// Algoritmen kører med O(n logn) - worst case O(n2).
    //den arbejder in-place og ift plads fylder det kun O(1) - "konstant plads"
    public static void quickSort(int[] numbers, int low, int high){
        //base case - vi hopper ud af rekursion, hvis low er >=  high
        if(low < high){
            int pivot = partition(numbers, low, high);
            //rekursivt sorterer vi begge sider
            quickSort(numbers,low,pivot-1);             //Sortere venstre side når pivot er rykket
            quickSort(numbers,pivot+1,high);            //sortere højre side når pivot er rykket
        }
    }

    private static int partition(int[] numbers, int low, int high){
        //partition: mindre værdier til venstre og større værdier til højre ift pivot(sidste element i array)
        int pivotValue = numbers[high];
        int i = low-1;
        for (int j = low; j < high ; j++) {
            if (numbers[j] <= pivotValue){
                //Hvis j'ne element er mindre eller lig med pivot, så swappes de
            i++;
            swap(numbers,i,j);
            }
        }
        swap(numbers, i+1, high);
        return i+1;
    }

    private static void swap(int[] numbers, int i, int j){
        int temp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = temp;
    }
}
