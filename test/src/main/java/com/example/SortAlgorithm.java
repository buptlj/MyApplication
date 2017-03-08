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
    public void QuickSort(int[] a,int low, int high){
        if (low>=high)
            return;
        int i = low;
        int j = high;
        int x = a[low];
        while (i<j){
            if (i<j){
                while (a[j]>x)
                    j--;
                int temp=a[j];
                a[j]=a[i];
                a[i]=temp;
                i++;
            }
            if (i<j){
                while (a[i]<x)
                    i++;
                int temp=a[j];
                a[j] = a[i];
                a[i] = temp;
                j--;
            }
        }
        QuickSort(a,low,i-1);
        QuickSort(a,j+1,high);
    }

}
