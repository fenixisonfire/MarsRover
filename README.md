# MarsRover

Application written with Java 9. Tests written with Junit 5.

Get started: 
1. Run RoverTest. (root/tests/com/rover/RoverTest)
2. Run Main if you wish to test manually. (root/src/com/rover/Main)

Design:
* Rover
    * Initialises the robot with the plateau it's on, position and direction.
    * Define the functions turnLeft(), turnRight() and move().
    * Execute the string of commands. 

* Plateau
    * Defines bottom left corner and top right corner.
    * Checks if a position is outside of plateau.

* Tests
    * Tested the suppied test case.
    * Tested move, turn left, turn right and multi-command commands.
    * Tested error catchings.
    * More information on in RoverTest.

Assumptions:
* If the rover encounters an invalid command, the rover will execute all the valid commands
before it and stop at the invalid one.
* If the rover drives off of the plateau area, a message will indicate that the rover has
left the plateau area and give the first position of where the rover left the plateau.