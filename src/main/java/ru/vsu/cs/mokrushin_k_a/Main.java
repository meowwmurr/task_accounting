package ru.vsu.cs.mokrushin_k_a;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<Integer, String> maps = new HashMap<>();
        maps.put(0, "false");
        System.out.println(maps.getOrDefault(0, "true"));
        System.out.println(maps.toString());
    }
}