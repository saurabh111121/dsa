package com.interview.EPAM;

import java.util.Arrays;

public class StreamAPI {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};


        // stream to get max element
        int max = Arrays.stream(arr).max().getAsInt();
        System.out.println(max);
    }

}
