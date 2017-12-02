package com.rover;

//Using JUnit 5

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class RoverTest {
    final Integer N = 1;
    final Integer E = 2;
    final Integer S = 3;
    final Integer W = 4;

    com.rover.Plateau plateau = new com.rover.Plateau();
    com.rover.Rover rover = new com.rover.Rover(plateau);

    //checks the supplied test data
    @Test
    public void suppied_test_data() throws Exception {
        // get system out
        ByteArrayOutputStream console_output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(console_output));

        // setup plateau
        plateau.setSize(5, 5);

        // setup first rover
        rover.setPosition(1, 2, N);
        rover.execute("LMLMLMLMM");

        // setup second rover
        rover.setPosition(3, 3, E);
        rover.execute("MMRMMRMRRM");

        String expected_output = "1 3 N\r\n" +
                                "5 1 E\r\n";

        // assert that the console output matches the expected output
        assertEquals(expected_output, console_output.toString());
    }

    //test the rover drives off of the plateau
    @Test
    public void outOfBounds() throws Exception {
        // get system out
        ByteArrayOutputStream console_output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(console_output));

        // setup plateau
        plateau.setSize(5, 5);

        rover.setPosition(5, 5, N);
        rover.execute("MMMM");

        // assumes that the rover drives off and stops responding to command.
        // record where the rover went outside of the plateau.
        String expected_output = "The rover drove off of the plateau into the darkness, never to be recovered! Last seen at: 5 6 N\r\n";

        // assert that the console output matches the expected output
        assertEquals(expected_output, console_output.toString());
    }

    //test the error if a invalid plateau is specified, for instance 0 or negative width and height
    @Test
    public void invalidPlateau() throws Exception {
        // setup invalid plateau where width is 0
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            plateau.setSize(0, 5);
        });
        assertEquals("Invalid plateau.", exception.getMessage());
    }
}