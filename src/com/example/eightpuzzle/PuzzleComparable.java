package com.example.eightpuzzle;

import java.util.Comparator;

class PuzzleComparable implements Comparator<PuzzleBoard> {
    @Override
    public int compare(PuzzleBoard p1, PuzzleBoard p2){
        if (p1.getAStar() < p2.getAStar()) return -1;
        if (p1.getAStar() > p2.getAStar()) return 1;
        return 0;
    }
}