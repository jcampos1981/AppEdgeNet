package com.crdesigns.appedgenet;

public class Interests {
    private int id;
    private String name;

    public Interests(){}

    public Interests(String name) {
        super();
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }
    public Integer getId(){
        return this.id;
    }
    public void setId(Integer id){
        this.id = id;
    }
    @Override
    public String toString() {
        return "Interests [id=" + id + ", name=" + name + "]";
    }
}
