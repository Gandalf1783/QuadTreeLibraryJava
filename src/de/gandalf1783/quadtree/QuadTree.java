package de.gandalf1783.quadtree;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

public class QuadTree implements Serializable {

    public Rectangle boundary;

    public int capacity;

    public ArrayList<Point> points = new ArrayList<>();

    public boolean divided;

    public QuadTree northwest, northeast,southwest,southeast;

    public QuadTree(Rectangle boundary, int capacity) {
        this.boundary = boundary;
        this.capacity = capacity;
        this.divided = false;
    }

    public void subdivide() {
        float x = this.boundary.x;
        float y = this.boundary.y;
        float w = this.boundary.w;
        float h = this.boundary.h;


        Rectangle nw = new Rectangle(x, y , w/2, h/2);

        Rectangle ne = new Rectangle(x + (w / 2), y, w/2, h/2);

        Rectangle se = new Rectangle(x + (w / 2), y + (h / 2), w/2, h/2);

        Rectangle sw = new Rectangle(x, y + (h / 2), w/2, h/2);

        northwest = new QuadTree(nw, this.capacity);
        northeast = new QuadTree(ne, this.capacity);
        southwest = new QuadTree(sw, this.capacity);
        southeast = new QuadTree(se, this.capacity);
        this.divided = true;
    }

    public void insert(Point p) {

        if(!this.boundary.contains(p)) {
            return;
        }

        if(this.points.size() < this.capacity) {
            this.points.add(p);
        } else {
            if(!this.divided) {
                this.subdivide();
            }

            // Try to insert everywhere
            this.northwest.insert(p);
            this.northeast.insert(p);
            this.southeast.insert(p);
            this.southwest.insert(p);

        }
    }


    public void query (Rectangle area, ArrayList<Point> found) {

        if (!this.boundary.intersects(area)) {
            return;
        } else {
            for(Point p : this.points) {
                if(!area.contains(p)) {
                    continue;
                }
                found.add(p);
            }

            if(this.divided) {
                this.northwest.query(area, found);
                this.northeast.query(area, found);
                this.southwest.query(area, found);
                this.southeast.query(area, found);
            }
        }
    }

    public void show(Graphics g) {
        g.drawRect((int)this.boundary.x, (int)this.boundary.y, (int)this.boundary.w, (int)this.boundary.h);
        for(Point p : this.points) {
            g.setColor(Color.BLACK);
            g.fillRect((int) p.x, (int)p.y, 3, 3);
        }
        if(this.divided) {

            this.northwest.show(g);
            this.northeast.show(g);
            this.southeast.show(g);
            this.southwest.show(g);
        }

    }

}
