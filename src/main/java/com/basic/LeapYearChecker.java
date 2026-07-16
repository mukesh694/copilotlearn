package com.basic;

public class LeapYearChecker {
    
    public static void main(String[] args) {
        // Test comprehensive cases: regular years, century years, and edge cases
        int[] testYears = {1900, 2000, 2004, 2020, 2021, 2024, 2100, 2400};
        
        System.out.println("=== Leap Year Checker ===\n");
        for (int year : testYears) {
            try {
                boolean isLeap = isLeapYear(year);
                String result = isLeap ? "✓ Leap Year" : "✗ NOT a Leap Year";
                System.out.println(year + ": " + result);
            } catch (IllegalArgumentException e) {
                System.out.println(year + ": ERROR - " + e.getMessage());
            }
        }
        
        // Test invalid inputs
        System.out.println("\n=== Testing Invalid Inputs ===");
        int[] invalidYears = {-1, 0};
        for (int year : invalidYears) {
            try {
                isLeapYear(year);
            } catch (IllegalArgumentException e) {
                System.out.println(year + ": " + e.getMessage());
            }
        }
    }

    public static boolean isLeapYear(int year) {
        // Validate input
        if (year <= 0) {
            throw new IllegalArgumentException("Year must be a positive number");
        }
        
        // Leap year logic: divisible by 400, or (divisible by 4 and not by 100)
        return (year % 400 == 0) || (year % 4 == 0 && year % 100 != 0);
    }
}