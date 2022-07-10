package com.example.sambo_tablo.data;

public class Player {
    private String name;
    private String surname;
    private String city;
    private String weight;

    public Player(String name, String surname, String city, String weight) {
        this.name=name;
        this.surname=surname;
        this.city=city;
        this.weight=weight;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name=name;
    }

    public String getSurname(){
        return surname;
    }

    public void setSurname(String surname){
        this.surname=surname;
    }

    public String getCity(){
        return city;
    }

    public void setCity(String city){
        this.city=city;
    }

    public String getWeight(){ return weight; }

    public void setWeight(String weight){
        this.weight=weight;
    }
}
