package app.kompleksitet;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Task1 {

    public static void main(String[] args) {
        //--------- O(1) ---------
        Map<String, Integer> ratings = new HashMap<>();

        ratings.put("Titanic", 8);
        ratings.put("Jumanji", 7);
        ratings.put("Anyone but you", 6);
        System.out.println("Rating af Titanic: " + ratings.get("Titanic"));

        //--------- O(log n) ------------
        int[] numbers = {2, 4, 6, 8, 10, 12, 14};

        int index = binarySearch(numbers, 10);
        System.out.println("Index af 10: " + index);

        //----------- O(n) ---------
        List<Boolean> values = Arrays.asList(true, false, true, true, false);

        int trueCount = countTrueValues(values);
        System.out.println("Number of true values: " + trueCount);

    }


    //-------------------------------------------------------
    //O(1)
    //Hvis alle keys får forskellige buckets O(1).
    //men hvis de får samme bucket, så vi skal iterere gennem alle elementer i bucket O(n).
    public static int getMovieRating(Map<String,Integer > ratings, String movie) {
        return ratings.get(movie);
    }

    //O(logn)
    public static int binarySearch(int[] array, int target) {
        int left = 0, right = array.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (array[mid] == target) return mid;
            else if (array[mid] < target) left = mid + 1;
            else right = mid - 1;
        }

        return -1;
    }

    //O(n)
    public static int countTrueValues(List<Boolean> values){
        int count = 0;
        for (int i = 0; i < values.size(); i++) {
            if(values.get(i)){
                count++;
            }
        }
        return count;
    }



    //---------------------------------------
    private static void quadTime(int n){
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.println("Hi!");
            }
        }
    }
}
