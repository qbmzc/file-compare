package com.cong.filecompare.controller;
public class QuickSort{
    public static int partition(int[] arr, int low, int high){
           int pivot = arr[low];
           while (low < high){
               while (low < high && arr[high] >= pivot) --high;
               arr[low] = arr[high];
               while (low < high && arr[low] <= pivot) ++low;
               arr[high] = arr[low];
           }
           arr[low] = pivot;
           return low;
       }
        public static void quickSort(int[] arr, int low, int high){
           if (low < high){
               int pivot = partition(arr, low, high);
               quickSort(arr, low, pivot - 1);
               quickSort(arr, pivot + 1, high);
           }
       }
        public static void main(String[] args){
           int[] arr={2,5,8,9,0,4,7,6,3,1};
           quickSort(arr,0,arr.length-1);
           for(int x:arr){
               System.out.println(x);
           }
       }
   }