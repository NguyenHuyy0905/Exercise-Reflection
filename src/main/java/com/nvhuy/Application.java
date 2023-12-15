package com.nvhuy;

import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.TreeMap;
import java.util.TreeSet;

public class Application {
    public static void main(String[] args) {
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(1);
        priorityQueue.add(2);
        priorityQueue.add(3);
        priorityQueue.add(4);
        priorityQueue.add(5);
        priorityQueue.add(8);
        priorityQueue.add(7);
        priorityQueue.add(6);
        System.out.println(priorityQueue);
        priorityQueue.poll();
        System.out.println(priorityQueue);
    }
}