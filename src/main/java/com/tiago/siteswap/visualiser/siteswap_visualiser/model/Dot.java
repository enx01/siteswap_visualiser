package com.tiago.siteswap.visualiser.siteswap_visualiser.model;

public class Dot {
    int num;

    Dot destination;

    public Dot(int num) {
        this.num = num;
        this.destination = null;
    }

    public Dot(int num, Dot destination) {
        this.num = num;
        this.destination = destination;
    }

    public int getNum() {
        return num;
    }

    public Dot getDestination() {
        return destination;
    }
}