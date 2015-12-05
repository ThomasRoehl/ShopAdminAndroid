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
        assert(name != null);
        this.name = name;
    }

    public String getName(){
        assert (name != null);
        return this.name;
    }

    public int getAccNr(){
        return this.accNr;
    }

    @Override
    public String toString(){
        assert(this.name != null);
        return "name: " + this.name + "\taccNr: " + accNr;
    }

    @Override
    public boolean equals(Object eq){
        assert(eq != null);
        if (!(eq instanceof ExampleContainer)) return false;
        ExampleContainer eqO = (ExampleContainer) eq;
        if (eqO.getAccNr() == this.accNr && this.name.equals(eqO.getName())) return true;
        return false;
    }

    @Override
    public int hashCode(){
        return this.accNr;
    }
}
