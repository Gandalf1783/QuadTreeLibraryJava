package de.gandalf1783.quadtree;

import java.io.Serializable;

public class Point implements Serializable {

    public float x,y;

    public Point() {

    }

    public Point(float x, float y) {
        this.x = x;
        this.y = y;
    }

}
