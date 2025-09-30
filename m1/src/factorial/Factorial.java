package factorial;

public class Factorial {

    public static void main(String[] args) {
        int n = 4; // Число, для якого потрібно обчислити факторіал
        final long startTime = System.nanoTime();
        System.out.println("Factorial of " + n + " is: " + factorial(n));
        final long duration = System.nanoTime() - startTime;
        System.out.println("Time taken: " + duration + " nanoseconds");
    }

    //0, 1, 2, 6, 24, 120, 720
    private static int factorial(int n) {
        if (n > 0) {
            return n * factorial(n - 1);
        } else if (n == 0) {
            return 1;
        }
        return 0;
    }

}
