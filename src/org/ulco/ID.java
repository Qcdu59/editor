package org.ulco;

public class ID {
    static private int ID = 0;
    static private ID id = new ID();
    private ID(){
    }
    static public ID returnInstance(){
    	return id;
    }
    static public int generateID(){
    	return returnInstance().ID++;
    }
}