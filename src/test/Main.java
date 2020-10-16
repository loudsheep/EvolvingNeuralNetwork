package test;

import brain.Brain;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Brain brain = new Brain(2, 5);

        System.out.println(Arrays.toString(brain.query(new float[]{2f, -0.5f})));
    }
}
