package com.example.eightpuzzle;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class PuzzleMenu {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose an option:");
        System.out.println("1) Enter your own 8-puzzle");
        System.out.println("2) Create randomly generated 8-puzzle");
        System.out.println("3) Test 200 cases");
        int choice = sc.nextInt();
        sc.nextLine();
        PuzzleBoard pb = new PuzzleBoard("012345678", 2);
        if (choice == 1) {
            boolean solvable = false;
            while (!solvable) {
                System.out.println("Enter an 8x8 puzzle in the format: ");
                System.out.println("x x x");
                System.out.println("x x x");
                System.out.println("x x x");
                String input;
                input = sc.nextLine();
                input += sc.nextLine();
                input += sc.nextLine();
                input = input.replaceAll("\\s", "");
                pb.setBoard(input);
                solvable = pb.isSolvable();
                if (!solvable) System.out.println("Puzzle cannot be solved!");
            }
        }
        else if (choice == 2) pb.randomize(100);
        else if (choice == 3) {
            System.out.println("What depth would you like to test? (Pick an even number between 2 and 20):");
            int testNum = sc.nextInt();
            manyTests(testNum);
        }
        System.out.println("\nBoard Initial State: ");
        pb.printBoard();
        PuzzleSolver solver = new PuzzleSolver(pb.getBoardString());
        System.out.println("\n===========================");
        System.out.println("Misplaced Tiles Heuristic:");
        System.out.println("===========================");
        System.out.println("\n\nNodes generated: " + solver.solveH1());
        System.out.println("Depth: " + solver.getSteps());
        System.out.println("\n===========================");
        System.out.println("Sum of Distance Heuristic:");
        System.out.println("===========================");
        System.out.println("\n\nNodes generated: " + solver.solveH2());
        System.out.println("Depth: " + solver.getSteps());
    }

    public static void manyTests(int depth) {
        String fileName = depth + ".txt";
        String line = null;
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            int lineCount = 0;
            double h2Nodes = 0;
            double h2Steps = 0;
            long h2Time = 0;
            double h1Nodes = 0;
            double h1Steps = 0;
            long h1Time = 0;
            long startTime;
            PuzzleSolver boardSolver;
            while((line = bufferedReader.readLine()) != null){
                lineCount++;
                boardSolver = new PuzzleSolver(line);
                startTime = System.nanoTime();
                h2Nodes += boardSolver.solveH2noPrint();
                h2Steps += boardSolver.getSteps();
                h2Time += System.nanoTime() - startTime;
                startTime = System.nanoTime();
                h1Nodes += boardSolver.solveH1noPrint();
                h1Time += System.nanoTime() - startTime;
                h1Steps += boardSolver.getSteps();
            }
            System.out.println("===========================");
            System.out.println("Misplaced Tiles Heuristic:");
            System.out.println("Depth: " + depth);
            System.out.println("Average nodes: " + (h1Nodes / lineCount));
            System.out.println("Average steps: " + (h1Steps / lineCount));
            System.out.println("Average time(nanoseconds): " + (h1Time / lineCount));
            System.out.println("===========================");
            System.out.println("Sum of Distances Heuristic:");
            System.out.println("Depth: " + depth);
            System.out.println("Average nodes: " + (h2Nodes / lineCount));
            System.out.println("Average steps: " + (h2Steps / lineCount));
            System.out.println("Average time(nanoseconds): " + (h2Time / lineCount));
            System.out.println("===========================");
            System.exit(0);
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");
        }
    }
}

