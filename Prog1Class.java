import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
/**********************************************************************/
/* Nishat Quyoum 
 * Student ID: 015087722
 * CS 3310, Fall 2023
 * Programming Assignment 1
/* Class for traversing a graph and finding connected components. 
 * This file acts as a class seperation for GraphTraversal.java
/**********************************************************************/
public class Prog1Class {

    private static Map<Integer, List<Integer>> graph;
    private static final int EXPECTED_NUM_ARGS = 1;

    public static void main(String[] args) throws IOException {
        // Main method code remains the same
        // ...
    }

    private static List<Set<Integer>> ConnectedComponents() {
        // ConnectedComponents method code remains the same
        // ...
    }

    private static void dfs(int vertex, Set<Integer> visited, Set<Integer> component) {
        // dfs method code remains the same
        // ...
    }

    public static Map<Integer, List<Integer>> getGraph() {
        return graph;
    }

    public static void setGraph(Map<Integer, List<Integer>> newGraph) {
        graph = newGraph;
    }
}