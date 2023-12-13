package com.nvhuy.test;

import java.util.PriorityQueue;
import java.util.Queue;

public class test {
    public static void main(String[] args) {
        Queue<User> userQueue = new PriorityQueue<>();
        userQueue.add(new User(3, "Huy"));
        userQueue.add(new User(2, "Huy"));
        userQueue.add(new User(5, "Huy"));
        userQueue.add(new User(7, "Huy"));
        userQueue.add(new User(4, "Huy"));
        System.out.println(userQueue);
        System.out.println(userQueue.poll());
        System.out.println(userQueue);
        System.out.println(userQueue.poll());
        System.out.println(userQueue);
    }
}
