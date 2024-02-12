package com.example.myapplication.string;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class PalindromicPartitions {

    @Test
    public void testPalindromicPartitions() {
        // Create object of GFG class
        GFG ob = new GFG();

        // Get all valid partitionings of the string "geeks"
        List<List<String>> allPart
                = ob.partition("geeks");

        // Print all partitionings
        for (List<String> partition : allPart) {
            for (String str : partition) {
                System.out.print(str + " ");
            }
            System.out.println();
        }
    }
}

class GFG {

    public List<List<String>> partition(String s) {
        List<List<String>> ans = new ArrayList<>();
        
        return ans;
    }

    boolean isPalindrome(String s) {
        int lower = 0;
        int higher = s.length() - 1;
        while (lower < higher) {
            if (s.charAt(lower) != s.charAt(higher)) return false;
            lower++;
            higher--;
        }
        return true;
    }
}
