package edu.neu.coe.info6205.union_find;

import java.util.Random;

public class HWQUPC_Solution {

    public static int count(int n) {
        UF_HWQUPC uf = new UF_HWQUPC(n, true);
        Random randomNumber = new Random();

        int count = 0;
        while(uf.components() > 1) {
            int nums1 = randomNumber.nextInt(n);
            int nums2 = randomNumber.nextInt(n);
            if(!uf.connected(nums1, nums2)) {
                uf.union(nums1, nums2);
            }
            count += 1;
        }
        return count;
    }
    public static void main(String[] args) {
        for(int n = 1; n < 100000; n *= 10) {
            System.out.println("n = " + n + ", Number of connects(m) : " + count(n));
        }
    }
}