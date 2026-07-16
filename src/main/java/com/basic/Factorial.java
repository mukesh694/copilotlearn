package com.basic;

import java.math.BigInteger;

//class to calculate factorial of a number
public class Factorial {
    //method to calculate factorial of a number
    public static BigInteger factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Factorial is not defined for negative numbers");
        }
        if (n == 0 || n == 1) {
            return BigInteger.ONE;
        }
        BigInteger result = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }

    //main method
    public static void main(String[] args) {
        // Test with small numbers
        int[] smallNumbers = {0, 1, 5, 10};
        System.out.println("=== Small Numbers ===");
        for (int number : smallNumbers) {
            BigInteger result = factorial(number);
            System.out.println("Factorial of " + number + " is: " + result);
        }

        // Test with large numbers
        int[] largeNumbers = {20, 50, 100};
        System.out.println("\n=== Large Numbers ===");
        for (int number : largeNumbers) {
            BigInteger result = factorial(number);
            System.out.println("Factorial of " + number + " is: " + result);
        }

        // Test error handling with negative number
        try {
            System.out.println("\n=== Error Handling ===");
            BigInteger result = factorial(5);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
   
    

}

