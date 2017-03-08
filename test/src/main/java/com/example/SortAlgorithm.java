package com.example;

/**
 * Created by jie on 2017/3/8.
 */

public class SortAlgorithm {
    public int[] StraightSelectionSort(int[] a){
        int size = a.length;
        for (int i=0;i<size-1;i++){
            int k=i;
            for (int j=i+1;j<size;j++){
                if (a[j]<a[k])
                    k=j;
            }
            if (k!=i){
                int temp=a[i];
                a[i]=a[k];
                a[k]=temp;
            }
        }
        return a;
    }

}
