package app.algoritmik;



import java.util.HashMap;
import java.util.Map;

public class Lazy {
    static Map<Integer, Long> memo = new HashMap<>();
    static Map<Integer, Integer> memoSum = new HashMap<>();
    static int counter = 0;

    // ----- Signes -----
    public static long lazyFactorial(int n) {
        if (memo.containsKey(n)) {
            return memo.get(n); // allerede beregnet? returnér resultatet
        }

        long result;
        if (n == 0 || n == 1) {
            result = 1;
        } else {
            result = n * lazyFactorial(n - 1);
        }

        memo.put(n, result); // gem til næste gang
        return result;
    }

    // ----- Opgave fra undervisningen -----
    public static int lazySum(int n){
        counter++;
        if(memoSum.containsKey(n)){
            return memoSum.get(n);
        }
        int result;
        if(n == 0){
            memoSum.put(n,0);
            return 0;
        } else {
            result =  n + lazySum(n-1);
        }
        memoSum.put(n,result);
        return result;
    }

    public static void main(String[] args) {
        System.out.println(lazyFactorial(5));  // udregner og gemmer
        System.out.println(lazyFactorial(4));  // bliver hurtigt, fordi det allerede er gemt
        System.out.println(lazyFactorial(6));  // bruger gemt factorial(5)

        System.out.println(lazySum(5));
        System.out.println(lazySum(4));
        System.out.println(lazySum(6));

        System.out.println("--- memory ---");
        System.out.println(memoSum.get(6));
        System.out.println("Counter: " +counter);
    }
}


