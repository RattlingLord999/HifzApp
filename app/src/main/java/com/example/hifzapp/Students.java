package com.example.hifzapp;
import java.io.Serializable;


public class Students implements Serializable {
    private int id;

    private String name;
    private String age;
    private String sclass;
    private int sabaq;
    private int sabaqi;
    private int manzil;

    public String getSclass() {
        return sclass;
    }

    public void setSclass(String sclass) {
        this.sclass = sclass;
    }



    public Students(int id, String name, String age, String sclass, int sabaq, int sabaqi, int manzil) {
        this.id = id;
        this.name = name;
        this.sclass=sclass;
        this.age=age;

        this.sabaq = sabaq;
        this.sabaqi = sabaqi;
        this.manzil = manzil;
    }






    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Students(String name, String age, String sclass) {
        this.age=age;
        this.sclass=sclass;
        this.id = id;
        this.name = name;
        this.sabaq = 0;
        this.sabaqi = -1;
        this.manzil = -1;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSabaq() {
        return sabaq;
    }

    public void setSabaq(int sabaq) {
        this.sabaq = sabaq;
    }

    public int getSabaqi() {
        return sabaqi;
    }

    public void setSabaqi(int sabaqi) {
        this.sabaqi = sabaqi;
    }

    public int getManzil() {
        return manzil;
    }

    public void setManzil(int manzil) {

        this.manzil = manzil;
    }
}




