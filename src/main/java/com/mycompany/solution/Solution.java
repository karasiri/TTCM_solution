/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.solution;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author irina
 */

public class Solution {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); 
        System.out.println("Enter a first number:");
        String outputFileName = null;
        String inputFileName = null;
        List<Integer> list = new ArrayList<>();
        inputFileName = readInputFileNameOrInteger(scanner);
        outputFileName = readOutputNameIfProvided(scanner);
        if (inputFileName != null) {
            try {
                scanner = new Scanner(new File(inputFileName));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Solution.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Input file is not found");
            }
        }
        
        readNumbersToListFromInput(scanner, list);
        if (list.size() % 2 == 0) {
            printEvenNumbers(list, outputFileName);
        } 
        else {
            printOddNumbers(list, outputFileName);
        }
        scanner.close();
    }

    private static void readNumbersToListFromInput(Scanner scanner, List<Integer> list) {
        while (scanner.hasNext()){
            if (scanner.hasNextInt()) {
                list.add(scanner.nextInt());
            } else scanner.next();
        }
    }

    private static String readInputFileNameOrInteger(Scanner scanner) {
        String inputFileName = null;
        if ( scanner.hasNextInt()) { //stdin
            scanner.next();
        } else {
            inputFileName = scanner.next();
        }
        return inputFileName;
    }

    private static String readOutputNameIfProvided(Scanner scanner) {
        String outputFileName = null;
        if ( !(scanner.hasNextInt()) && scanner.hasNext()) {
            outputFileName = scanner.next();
        }
        return outputFileName;
    }
    
    public static void printEvenNumbers (List<Integer> list, String outputFileName) {
        List<Integer> evenList = new ArrayList<>();
        for (Integer num : list) {
            if (num % 2 == 0) {
                evenList.add(num); 
            }
        }
        printResult(evenList, outputFileName);
    }
    
    public static void printOddNumbers (List<Integer> list, String outputFileName) {
        List<Integer> oddList = new ArrayList<>();      
        for (Integer num : list) {
            if ( !(num % 2 == 0 )) {
                oddList.add(num);
            }
        }
         printResult(oddList, outputFileName);
    }
    
    public static void printResult(List<Integer> result, String outputFileName) {
        if ( outputFileName != null) {
            try (PrintWriter printWriter = new PrintWriter(outputFileName)) {
                for (Integer num : result) {
                    printWriter.print(num +  " ");
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Solution.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Output file not found");
            }
        } 
        else {
            for (Integer num : result) {
               System.out.println(num);
            }
        }
    }
}
