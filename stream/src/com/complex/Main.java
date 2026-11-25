/**
 * 
 */
package com.start.test.stream;

import java.util.*;
import java.util.stream.Collectors;

class Main {
    public static void main(String[] args) {
        // System.out.println("Try clicking the Run button.");
        List<Integer> numbers = List.of(10, 15, 20, 25, 30, 35);
        System.out.println(numbers);
        numbers.stream().filter(n->n%2==0).collect(Collectors.toList());
    }
}
