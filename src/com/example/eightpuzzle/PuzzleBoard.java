package com.example.eightpuzzle;

import java.util.Random;

public class PuzzleBoard {
    private StringBuffer values;
    private int heuristic;
    private int depth;
    private PuzzleBoard parent;
    private int emptyLocation;
    private int aStar; //f(n) = g(n) + h(n)

    public PuzzleBoard(String value, int h) {
        this(value, 0, null, h);
    }

    private PuzzleBoard(String value, int d, PuzzleBoard p, int h) {
        this.values = new StringBuffer(value);
        for (int i = 0; i < 9; i++) {
            if (this.values.charAt(i) == '0') {
                this.emptyLocation = i;
                break;
            }
        }
        this.depth = d;
        this.parent = p;
        this.heuristic = h;
        if (h == 1) this.aStar = d + this.h1();
        else if (h == 2) this.aStar = d + this.h2();
    }

    public void setBoard(String s) {
        this.values = new StringBuffer(s);
        if (this.heuristic == 1) this.aStar = this.depth + this.h1();
        else if (this.heuristic == 2) this.aStar = this.depth + this.h2();
    }

    public int getAStar() {
        return this.aStar;
    }

    public String getBoardString() {
        return this.values.toString();
    }

    public PuzzleBoard getParent() {
        return this.parent;
    }

    public void printBoard() {
        System.out.println(this.values.charAt(0) + " " + this.values.charAt(1) + " " + this.values.charAt(2));
        System.out.println(this.values.charAt(3) + " " + this.values.charAt(4) + " " + this.values.charAt(5));
        System.out.println(this.values.charAt(6) + " " + this.values.charAt(7) + " " + this.values.charAt(8));
    }

    public void randomize(int n) {
        Random rand = new Random();
        int move;
        for (int i = 0; i < n; i++) {
            move = rand.nextInt(4);
            if (move == 0) {
                if (this.emptyLocation > 2) { //empty isn't on top row
                    this.values.setCharAt(this.emptyLocation, this.values.charAt(this.emptyLocation - 3));
                    this.emptyLocation -= 3;
                    this.values.setCharAt(this.emptyLocation, '0');
                }
            }
            else if (move == 1) {
                if (this.emptyLocation % 3 != 0) { //empty isn't on left column
                    this.values.setCharAt(this.emptyLocation, this.values.charAt(this.emptyLocation - 1));
                    this.emptyLocation--;
                    this.values.setCharAt(this.emptyLocation, '0');
                }
            }
            else if (move == 2) {
                if (this.emptyLocation % 3 != 2) { //empty isn't on right column
                    this.values.setCharAt(this.emptyLocation, this.values.charAt(this.emptyLocation + 1));
                    this.emptyLocation++;
                    this.values.setCharAt(this.emptyLocation, '0');
                }
            }
            else if (move == 3) {
                if (this.emptyLocation < 6) { //empty isn't on bottom row
                    this.values.setCharAt(this.emptyLocation, this.values.charAt(this.emptyLocation + 3));
                    this.emptyLocation += 3;
                    this.values.setCharAt(this.emptyLocation, '0');
                }
            }
        }
    }

    private int h1() {
        int h1Result = 0;
        for (int i = 0; i < 9; i++) {
            if (this.values.charAt(i) != (char)(48 + i)) { //character numbers off by 48
                h1Result++;
            }
        }
        return h1Result;
    }

    private int h2() {
        int h2Result = 0;
        for (int i = 0; i < 9; i++) {
            int row = i / 3;
            int col = i % 3;
            int currentValue = (int)this.values.charAt(i) - 48;
            int rowVal = currentValue / 3;
            int colVal = currentValue % 3;
            h2Result += (Math.abs(row - rowVal) + Math.abs(col - colVal));
        }
        return h2Result;
    }

    public PuzzleBoard moveUp() {
        StringBuffer sb = new StringBuffer(this.values.toString());
        if (this.emptyLocation > 2) { //if empty location isn't in top row
            sb.setCharAt(this.emptyLocation, this.values.charAt(this.emptyLocation - 3));
            sb.setCharAt(this.emptyLocation - 3, '0');
            return new PuzzleBoard(sb.toString(), this.depth + 1, this, this.heuristic);
        }
        else return null;
    }

    public PuzzleBoard moveDown() {
        StringBuffer sb = new StringBuffer(this.values.toString());
        if (this.emptyLocation < 6) { //if empty location isn't in bottom row
            sb.setCharAt(this.emptyLocation, this.values.charAt(this.emptyLocation + 3));
            sb.setCharAt(this.emptyLocation + 3, '0');
            return new PuzzleBoard(sb.toString(), this.depth + 1, this, this.heuristic);
        }
        else return null;
    }

    public PuzzleBoard moveLeft() {
        StringBuffer sb = new StringBuffer(this.values.toString());
        if (this.emptyLocation % 3 != 0) { //if empty location isn't in left column
            sb.setCharAt(this.emptyLocation, this.values.charAt(this.emptyLocation - 1));
            sb.setCharAt(this.emptyLocation - 1, '0');
            return new PuzzleBoard(sb.toString(), this.depth + 1, this, this.heuristic);
        }
        else return null;
    }

    public PuzzleBoard moveRight() {
        StringBuffer sb = new StringBuffer(this.values.toString());
        if (this.emptyLocation % 3 != 2) { //if empty location isn't in right column
            sb.setCharAt(this.emptyLocation, this.values.charAt(this.emptyLocation + 1));
            sb.setCharAt(this.emptyLocation + 1, '0');
            return new PuzzleBoard(sb.toString(), this.depth + 1, this, this.heuristic);
        }
        else return null;
    }

    public boolean isSolvable() {
        int inversions = 0;
        for (int i = 0; i < 8; i++) {
            if (this.values.charAt(i) != '0') {
                for (int j = i + 1; j < 9; j++) {
                    if (this.values.charAt(j) != '0' && (int)this.values.charAt(i) > (int)this.values.charAt(j)) inversions++;
                }
            }
        }
        if (inversions % 2 == 0) return true;
        else return false;
    }

    public boolean isGoal() {
        return (h1() == 0);
    }



}
