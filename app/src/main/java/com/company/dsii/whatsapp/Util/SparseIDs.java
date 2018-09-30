package com.company.dsii.whatsapp.Util;

import android.util.SparseBooleanArray;

import com.company.dsii.whatsapp.EventB.eventb_prelude.ID;

import java.util.ArrayList;

public class SparseIDs {

    private static final ArrayList<Boolean> isIDAvailable = new ArrayList<>();
    private static final ArrayList<Integer> availableIDs = new ArrayList<>();

    public SparseIDs(){

    }

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

    public static void initValues(ArrayList<Boolean> isTaken){
        initValues(-100,100);
        for(int i = 0; i < 201; i++){
            isIDAvailable.set(i,isTaken.get(i));
        }
    }

    public static void initValues(int lower, int upper){
        for(int i = lower; i <= upper; i++){
            isIDAvailable.add(true);
            availableIDs.add(i);
        }
    }

    public static ArrayList<Boolean> getIsIDAvailable() {
        return isIDAvailable;
    }

    public static ArrayList<Integer> getAvailableIDs() {
        return availableIDs;
    }

    public static void releaseID(int ID){
        isIDAvailable.set(ID+100,true);
    }

}
