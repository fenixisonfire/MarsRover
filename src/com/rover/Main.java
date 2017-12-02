package com.rover;

public class Main {
    public static void main(String arg[]){

        final Integer N = 1;
        final Integer E = 2;
        final Integer S = 3;
        final Integer W = 4;

        //Manual Testing

        com.rover.Plateau plateau = new com.rover.Plateau();

        plateau.setSize(5, 5);

        com.rover.Rover rover = new com.rover.Rover(plateau);

        rover.setPosition(1, 2, N);
        rover.execute("LMLMLMLMM");

        rover.setPosition(3, 3, E);
        rover.execute("MMRMMRMRRM");

        rover.setPosition(5, 5, N);
        rover.execute("EE");

    }
}
