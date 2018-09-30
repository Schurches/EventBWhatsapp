package com.company.dsii.whatsapp.Util;

import android.util.SparseBooleanArray;

import java.util.ArrayList;

public class SparseIDs {

    private static final ArrayList<Boolean> isIDAvailable = new ArrayList<>();
    private static final ArrayList<Integer> availableIDs = new ArrayList<>();

    public static int findAnAvailableID(){
        int size = availableIDs.size();
        for(int i = 0; i < size; i++){
            if(isIDAvailable.get(i)){
                isIDAvailable.set(i,false);
                return availableIDs.get(i);
            }
        }
        return -1;
    }

    public static void releaseID(int ID){
        isIDAvailable.set(ID+100,true);
    }

    public static void initValues(int lower, int upper){
        for(int i = lower; i < upper; i++){
            isIDAvailable.add(true);
            availableIDs.add(i);
        }
    }


}
