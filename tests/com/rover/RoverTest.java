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

    //stationary test for if rover stays in place with no command
    @Test
    public void stationary_test() throws Exception {
        // get system out
        ByteArrayOutputStream console_output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(console_output));

        // setup plateau
        plateau.setSize(4, 4);

        // setup first rover
        rover.setPosition(2, 2, N);
        rover.execute("");

        String expected_output = "2 2 N\r\n";

        // assert that the console output matches the expected output
        assertEquals(expected_output, console_output.toString());
    }

    //turn left check
    @Test
    public void turn_left_test() throws Exception {
        // get system out
        ByteArrayOutputStream console_output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(console_output));

        // setup plateau
        plateau.setSize(5, 5);

        // setup first rover
        rover.setPosition(2, 2, N);
        rover.execute("L");

        String expected_output = "2 2 W\r\n";

        // assert that the console output matches the expected output
        assertEquals(expected_output, console_output.toString());
    }

    //turn right check
    @Test
    public void turn_right_test() throws Exception {
        // get system out
        ByteArrayOutputStream console_output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(console_output));

        // setup plateau
        plateau.setSize(5, 5);

        // setup first rover
        rover.setPosition(2, 2, N);
        rover.execute("R");

        String expected_output = "2 2 E\r\n";

        // assert that the console output matches the expected output
        assertEquals(expected_output, console_output.toString());
    }

    //move check
    @Test
    public void move_test() throws Exception {
        // get system out
        ByteArrayOutputStream console_output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(console_output));

        // setup plateau
        plateau.setSize(5, 5);

        // setup first rover
        rover.setPosition(2, 2, N);
        rover.execute("M");

        String expected_output = "2 3 N\r\n";

        // assert that the console output matches the expected output
        assertEquals(expected_output, console_output.toString());
    }

    //multi command check to see if we can perform all available commands turn left, right and move forward in a sequence
    @Test
    public void multi_command_test() throws Exception {
        // get system out
        ByteArrayOutputStream console_output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(console_output));

        // setup plateau
        plateau.setSize(5, 5);

        // setup first rover
        rover.setPosition(1, 1, N);
        rover.execute("RMLM");

        String expected_output = "2 2 N\r\n";

        // assert that the console output matches the expected output
        assertEquals(expected_output, console_output.toString());
    }

    //edge test to see if rover can travel along the edge of plateau
    @Test
    public void edge_test() throws Exception {
        // get system out
        ByteArrayOutputStream console_output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(console_output));

        // setup plateau
        plateau.setSize(5, 5);

        // setup first rover
        rover.setPosition(1, 1, S);
        rover.execute("MLMMM");

        String expected_output = "4 0 E\r\n";

        // assert that the console output matches the expected output
        assertEquals(expected_output, console_output.toString());
    }

    //invalid command check
    @Test
    public void invalid_command_test() throws Exception {
        // get system out
        ByteArrayOutputStream console_output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(console_output));

        // setup plateau
        plateau.setSize(5, 5);

        // setup first rover
        rover.setPosition(2, 2, N);
        rover.execute("I");

        String expected_output = "The Rover's on-board computer crashed due to invalid command. Last seen at: 2 2 N\r\n";

        // assert that the console output matches the expected output
        assertEquals(expected_output, console_output.toString());
    }

    //test the rover drives off of the plateau
    @Test
    public void out_of_bounds_test() throws Exception {
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
    public void invalid_plateau_test() throws Exception {
        // setup invalid plateau where width is 0
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            plateau.setSize(0, 5);
        });
        assertEquals("Invalid plateau.", exception.getMessage());
    }
}