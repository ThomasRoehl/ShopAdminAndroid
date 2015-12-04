package com.example.thomasroehl.shopadminandroid.container;

/**
 * Created by Thomas Roehl on 04.12.2015.
 */
public class ExampleContainer {
    private String name;
    private final int accNr;

    public ExampleContainer(int accNr){
        this.accNr = accNr;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public int getAccNr(){
        return this.accNr;
    }

    @Override
    public String toString(){
        return "name: " + this.name + "\taccNr: " + accNr;
    }

    @Override
    public boolean equals(Object eq){
        if (!(eq instanceof ExampleContainer)) return false;
        ExampleContainer eqO = (ExampleContainer) eq;
        if (eqO.getAccNr() == this.accNr) return true;
        return false;
    }
}
