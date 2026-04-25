package app.sorting;

import java.util.Arrays;

public class Bubblesort {

    public static void main(String[] args) {
        int[] numbers = {5,2,3,1,9};

        System.out.println((Arrays.toString(bubbleSort(numbers))));

    }
  // Algoritmen kører med O(n2) - kvadratisk tid
    public static int[] bubbleSort(int[] numbers){

        for (int i = 0; i < numbers.length-1; i++) {
            for (int j = 0; j < numbers.length-i-1; j++) {
                if(numbers[j] > numbers[j+1]){
                    int temp = numbers[j];
                    numbers[j] = numbers[j+1];
                    numbers[j+1] = temp;
                }
            }

        }

        return numbers;
    }
}
