package com.pianma.bubble.ex15;


public interface Moveable {
    public abstract void up();
    public abstract void left();
    public abstract void right();
    default public void down(){

    };

    default public void attack(){

    };
}