package com.codecool.hackerrankExercise.roadsAndLibraries;

import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int worlds = in.nextInt();
        for(int world = 0; world < worlds; world++){
            int cities = in.nextInt();
            int roads = in.nextInt();
            int libCost = in.nextInt();
            int roadCost = in.nextInt();
            if (roadCost >= libCost || roads == 0){
                for (int i = 0; i < (roads*2); i++){
                    in.nextInt();
                }
                System.out.println((long) libCost * cities);
            } else {
                World newWorld = new World(cities, libCost, roadCost);
                for (int road = 0; road < roads; road++) {
                    int city_1 = in.nextInt() - 1;
                    int city_2 = in.nextInt() - 1;
                    newWorld.cities[city_1].neighbours.add(city_2);
                    newWorld.cities[city_2].neighbours.add(city_1);
                }
                System.out.println(newWorld.countCost());
            }
        }
    }
}

class World {
    City[] cities;
    private int libCost;
    private int roadCost;

    World(int cities, int libCost, int roadCost) {
        this.cities = new City[cities];
        for(int i = 0; i < cities; i++) this.cities[i] = new City();
        this.libCost = libCost;
        this.roadCost = roadCost;
    }

    long countCost() {
        int graph = countGraph();
        return (long) libCost * graph + (long) roadCost * (cities.length - graph);
    }

    private int countGraph(){
        int result = 0;
        for (City city : cities) {
            if (!city.visited){
                connect(city);
                result++;
            }
        }
        return result;
    }

    private void connect(City city) {
        city.visited = true;
        for (int neighbour : city.neighbours) {
            if (!cities[neighbour].visited) connect(cities[neighbour]);
        }
    }
}

class City {
    List<Integer> neighbours;
    boolean visited;

    City() {
        neighbours = new ArrayList<>();
    }
}