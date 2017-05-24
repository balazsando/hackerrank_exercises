package com.codecool.hackerrankExercise.snakesAndLadders;

import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int games = in.nextInt();
        for (int i = 0; i < games; i++) {
            Gameboard gameboard = new Gameboard();
            int ladders = in.nextInt();
            for (int j = 0; j < ladders; j++) gameboard.gameboard.get(in.nextInt()).value = in.nextInt();
            int snakes = in.nextInt();
            for (int k = 0; k < snakes; k++) gameboard.gameboard.get(in.nextInt()).value = in.nextInt();
            System.out.println(gameboard.bfs());
        }
    }
}

class Gameboard {
    List<Gamefield> gameboard;

    Gameboard() {
        this.gameboard = new ArrayList<>();
        for (int i=0; i < 101; i++) this.gameboard.add(new Gamefield(i));
    }

    int bfs() {
        List<Integer> visited = new ArrayList<>(Arrays.asList(gameboard.get(1).value));
        int steps = 0;
        while (!visited.contains(100)) {
            List<Integer> addition = new ArrayList<>();
            for (int i : visited) {
                if (!gameboard.get(i).visited) {
                    for (int j = i + 1; j < 101 && j < i + 7; j++) if (!gameboard.get(j).visited) addition.add(gameboard.get(j).value);
                    gameboard.get(i).visited = true;
                }
            }
            if (addition.isEmpty()) return -1;
            steps++;
            visited.addAll(addition);
        }
        return steps;
    }
}

class Gamefield {
    int value;
    boolean visited;

    Gamefield(int value) {
        this.value = value;
    }
}
