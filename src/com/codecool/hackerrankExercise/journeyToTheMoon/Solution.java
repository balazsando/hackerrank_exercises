package com.codecool.hackerrankExercise.journeyToTheMoon;

import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws Exception{
        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = bfr.readLine().split(" ");
        int N = Integer.parseInt(temp[0]);
        int I = Integer.parseInt(temp[1]);
        long totalPairs = (long) N * (N - 1) / 2;
        if (I == 0) {
            System.out.println(totalPairs);
        } else {
            Crew crew = new Crew(N);
            for (int i = 0; i < I; i++){
                temp = bfr.readLine().split(" ");
                int a = Integer.parseInt(temp[0]);
                int b = Integer.parseInt(temp[1]);
                crew.members[a].compatriots.add(b);
                crew.members[b].compatriots.add(a);
            }
            System.out.println(totalPairs - crew.invalidPairs());
        }

    }
}

class Crew {
    Member[] members;
    private int size;

    Crew(int crew) {
        this.members = new Member[crew];
        for (int i = 0; i < crew; i++) this.members[i] = new Member();
    }

    long invalidPairs (){
        List<Integer> groups = new ArrayList<>();
        for (Member member : members) {
            if (!member.visited) {
                size = 0;
                collectCompatriots(member);
                groups.add(size);
            }
        }
        long result = 0;
        for (int i : groups) result += i * (i - 1) / 2;
        return result;
    }

    private void collectCompatriots(Member member) {
        member.visited = true;
        size++;
        for (int compatriot : member.compatriots) if (!members[compatriot].visited) collectCompatriots(members[compatriot]);
    }
}

class Member {
    Set<Integer> compatriots;
    boolean visited;

    Member() {
        this.compatriots = new HashSet<>();
    }
}