package com.uialert.tictactoe.engine;

import java.util.ArrayList;
import java.util.Vector;

public class VectorsFindingVictory {

    static public ArrayList<Vector> getVector(){
        ArrayList<Vector> vectors = new ArrayList<>();
        vectors.add(new Vector(1,0));
        vectors.add(new Vector(1,1));
        vectors.add(new Vector(0,1));
        vectors.add(new Vector(-1,0));
        vectors.add(new Vector(-1,-1));
        vectors.add(new Vector(0,-1));
        vectors.add(new Vector(1,-1));
        vectors.add(new Vector(-1,1));
        return vectors;
    }

    public static class Vector {
        private int x;
        private int y;

        public int getX() {
            return x;
        }
        public void setX(int x) {
            this.x = x;
        }
        public int getY() {
            return y;
        }
        public void setY(int y) {
            this.y = y;
        }

        public Vector(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
