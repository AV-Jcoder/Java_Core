package com.afoninav.javacore.chapter13;

public class InstanceOfMyExample {
    public static void main(String[] args) {
        Bird bird = new Bird();
        System.out.println(bird instanceof Animal); // true
        System.out.println(bird instanceof Fly);    // true
    }
}

interface Fly {
}

class Animal {
}

class Bird extends Animal implements Fly {
}
