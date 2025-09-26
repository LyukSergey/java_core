package fibonachi;

public class MainFibonachi {

    public static void main(String[] args) {
        int n = 10; // Порядковий номер числа Фібоначчі, яке ми хочемо знайти
        final long startTime = System.nanoTime();
        System.out.println("Fibonacci number at position " + n + " is: " + fibonacci(n));
        final long duration = System.nanoTime() - startTime;
        System.out.println("Time taken (recursive): " + duration + " nanoseconds");
        System.out.println("--------------------------------------------------");
        final long startTime2 = System.nanoTime();
        System.out.println("Fibonacci number at position " + n + " is: " + fibonacci2(n));
        final long duration2 = System.nanoTime() - startTime2;
        System.out.println("Time taken (iterative): " + duration2 + " nanoseconds");
        System.out.println("--------------------------------------------------");
        System.out.println(duration > duration2 ? "Iterative method is faster" : "Recursive method is faster");
    }

    // Рекурсивна функція для обчислення числа Фібоначчі
    public static int fibonacci(int n) {
        if (n <= 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }

    //0,1,1,2,3,5,8,13,21,34,55
    public static int fibonacci2(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int prevoiu = 0;
        int current = 1;
        int result = 0;
        for (int i = 2; i <= n; i++) {
            result = prevoiu + current;
            prevoiu = current;
            current = result;
        }
        return result;

    }
}
