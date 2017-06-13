# 8-Puzzle-Problem-Solver

Uses the A* search to solve the 8-puzzle problem.  Uses the following two heuristic functions: 
  1) the number of misplaced tiles
  2) the sum of the distances of the tiles from their goal positions
This program implements the A* using both heuristics and compares their efficiency in terms of depth of the solution and search costs.

The program allows the user to set the input as the following:
  1) a randomly generated 8-puzzle problem 
  2) a specific 8-puzzle configuration entered through the standard input
  
Configurations for the puzzles are in the format:
1 2 4
0 5 6
8 3 7

The goal state is 
0 1 2
3 4 5
6 7 8

0 represents the empty tile.

This program will also check if a puzzle is solvable before attempting to solve it.
