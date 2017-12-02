package com.rover;

public class Plateau {
    //default minimum values: bottom left corner (these never change)
    private final int min_X = 0, min_Y = 0;
    //default maximum values: top right corner
    private int max_X = 0, max_Y = 0;

    public void setSize(int input_max_x, int input_max_y){
        if(input_max_x < 1 || input_max_y < 1){
            throw new IllegalArgumentException("Invalid plateau.");
        }
        this.max_X = input_max_x;
        this.max_Y = input_max_y;
    }

    private boolean inRange(int component, int min, int max){
        return (component >= min && component <= max);
    }

    public boolean isPositionInBound(int x, int y){
        return inRange(x, min_X, max_X) && inRange(y, min_Y, max_Y);
    }
}
