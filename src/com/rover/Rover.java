package com.rover;

public class Rover {
    public static final Integer N = 1;
    public static final Integer E = 2;
    public static final Integer S = 3;
    public static final Integer W = 4;

    private Integer x = 0;
    private Integer y = 0;
    private Integer current_direction = N;

    private com.rover.Plateau plateau;

    public Rover(Plateau plateau) {
        this.plateau = plateau;
    }

    public void setPosition(Integer x, Integer y, Integer current_direction) {
        this.x = x;
        this.y = y;
        this.current_direction = current_direction;
    }

    /////////////////////////////////////////////////////////////////////////////////////////
    ////// Rover Actions             ////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////

    private void turnLeft() {
        current_direction = (current_direction - 1) < N ? W : current_direction - 1;
    }

    private void turnRight() {
        current_direction = (current_direction + 1) > W ? N : current_direction + 1;
    }

    private void move() {
        switch (current_direction) {
            case 1:
                this.y++; //North facing
                break;
            case 2:
                this.x++; //East facing
                break;
            case 3:
                this.y--; //South facing
                break;
            case 4:
                this.x--; //West facing
                break;
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////
    ////// Rover Execution            ///////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////

    private char directionToChar(int direction) {
        switch (direction) {
            case 1:
                return 'N';
            case 2:
                return 'E';
            case 3:
                return 'S';
            case 4:
                return 'W';
        }
        return 0;
    }

    private String finalPosition() {
        return x + " " + y + " " + directionToChar(current_direction);
    }

    // execute rover actions depending on character command
    private Integer execute(Character command) {
        switch (command) {
            case 'M':
                move();
                break;
            case 'L':
                turnLeft();
                break;
            case 'R':
                turnRight();
                break;
            default:
                return 0; //failed to execute command
        }
        return 1; //successfully executed command
    }

    // execute the specified string of commands
    public void execute(String commands) {
        //if no movement command, print current position
        if(commands.isEmpty())
            System.out.println(finalPosition());
        for (int i = 0; i < commands.length(); i++) {
            //stop the robot if invalid command
            if (execute(commands.charAt(i)) == 0) {
                System.out.println("The Rover's on-board computer crashed due to invalid command. " + "Last seen at: " + finalPosition());
                break;
            } else {
                //check if the rover has driven past the plateau boundaries
                if (!plateau.isPositionInBound(this.x, this.y)) {
                    System.out.println("The rover drove off of the plateau into the darkness, never to be recovered! " + "Last seen at: " + finalPosition());
                    break;
                }
                //print rover position at end of execution command
                if (i == commands.length() - 1) {
                    System.out.println(finalPosition());
                }
            }
        }
    }
}