package de.gandalf1783.quadtree;

import java.io.Serializable;

public class Rectangle implements Serializable {

    public float x,y,w,h;

    public Rectangle() {
    }

    public Rectangle(float x, float y, float w, float h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public boolean contains(Point p) {
        boolean result = (p.x > this.x && p.x < this.x + this.w)
                && (p.y > this.y && p.y < this.y + this.h);

        return result;
    }

    public boolean intersects(Rectangle r) {
        float tw = this.w;
        float th = this.h;
        float rw = r.w;
        float rh = r.h;

        if (rw <= 0 || rh <= 0 || tw <= 0 || th <= 0) {
            return false;
        }

        float tx = this.x;
        float ty = this.y;
        float rx = r.x;
        float ry = r.y;

        rw += rx;
        rh += ry;
        tw += tx;
        th += ty;
        //      overflow || intersect
        return ((rw < rx || rw > tx) &&
                (rh < ry || rh > ty) &&
                (tw < tx || tw > rx) &&
                (th < ty || th > ry));
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getW() {
        return w;
    }

    public void setW(float w) {
        this.w = w;
    }

    public float getH() {
        return h;
    }

    public void setH(float h) {
        this.h = h;
    }
}
