package com.example.eightpuzzle;

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Stack;

public class PuzzleSolver {

    private PuzzleBoard root;
    private String board;
    private int steps;
    private int nodes;

    public PuzzleSolver(String s) {
        //this.board = new String();
        this.board = s;
        this.steps = 0;
        this.nodes = 0;
    }

    public int solveH1() {
        return this.solve(1);
    }

    public int solveH1noPrint() {
        return this.solveNoPrint(1);
    }

    public int solveH2() {
        return this.solve(2);
    }

    public int solveH2noPrint() {
        return this.solveNoPrint(2);
    }

    public int getSteps() {
        return this.steps;
    }

    private int solve(int heuristic) {
        Comparator<PuzzleBoard> compare = new PuzzleComparable();
        PriorityQueue<PuzzleBoard> pq = new PriorityQueue<>(10, compare);
        HashMap<String, PuzzleBoard> hm = new HashMap<>(10);
        this.root = new PuzzleBoard(this.board, heuristic);
        pq.add(this.root);
        this.nodes = 0;
        while (pq.size() > 0) {
            PuzzleBoard pb = pq.remove();
            if (pb.isGoal()) {
                int steps = -1;
                Stack<PuzzleBoard> stack = new Stack<>();
                while (pb != null) {
                    steps++;
                    stack.push(pb);
                    pb = pb.getParent();
                }
                while (stack.size() > 1) {
                    stack.pop().printBoard();
                    System.out.println("NEXT STEP");
                }
                stack.pop().printBoard();
                this.steps = steps;
                return this.nodes;
            }
            PuzzleBoard temp = pb.moveUp();
            if (temp != null) {
                this.nodes++;
                if (!hm.containsKey(temp.getBoardString())) pq.add(temp);
            }
            temp = pb.moveDown();
            if (temp != null) {
                this.nodes++;
                if (!hm.containsKey(temp.getBoardString())) pq.add(temp);
            }
            temp = pb.moveLeft();
            if (temp != null) {
                this.nodes++;
                if (!hm.containsKey(temp.getBoardString())) pq.add(temp);
            }
            temp = pb.moveRight();
            if (temp != null) {
                this.nodes++;
                if (!hm.containsKey(temp.getBoardString())) pq.add(temp);
            }
            hm.put(pb.getBoardString(), pb);
        }
        return 0;
    }

    private int solveNoPrint(int heuristic) {
        Comparator<PuzzleBoard> compare = new PuzzleComparable();
        PriorityQueue<PuzzleBoard> pq = new PriorityQueue<>(10, compare);
        HashMap<String, PuzzleBoard> hm = new HashMap<>(10);
        this.root = new PuzzleBoard(this.board, heuristic);
        pq.add(this.root);
        this.nodes = 0;
        while (pq.size() > 0) {
            PuzzleBoard pb = pq.remove();
            if (pb.isGoal()) {
                int steps = -1;
                Stack<PuzzleBoard> stack = new Stack<>();
                while (pb != null) {
                    steps++;
                    stack.push(pb);
                    pb = pb.getParent();
                }
                while (stack.size() > 1) {
                    stack.pop();
                }
                stack.pop();
                this.steps = steps;
                return this.nodes;
            }
            PuzzleBoard temp = pb.moveUp();
            if (temp != null) {
                this.nodes++;
                if (!hm.containsKey(temp.getBoardString())) pq.add(temp);
            }
            temp = pb.moveDown();
            if (temp != null) {
                this.nodes++;
                if (!hm.containsKey(temp.getBoardString())) pq.add(temp);
            }
            temp = pb.moveLeft();
            if (temp != null) {
                this.nodes++;
                if (!hm.containsKey(temp.getBoardString())) pq.add(temp);
            }
            temp = pb.moveRight();
            if (temp != null) {
                this.nodes++;
                if (!hm.containsKey(temp.getBoardString())) pq.add(temp);
            }
            hm.put(pb.getBoardString(), pb);
        }
        return 0;
    }
}
