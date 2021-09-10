# Minewalker
* Author: Joel Koehler
* Class: CS121 Section 2
* Semester: Spring 2018

### Overview

MineWalker is a GUI-based game with a simple goal: starting from the lower-left corner on a grid, attempt to "walk" to the 
upper-right corner without stepping on hidden mines.


### Usage

To compile: 
$ javac *.java
To run: 
$ java Minewalker

Color-key for tiles:
* Green: There are zero mines in the four adjacent tiles
* Yellow: There is one mine in the four adjacent tiles
* Orange: There are two mines in the four adjacent tiles
* Red: There are three mines in the four adjacent tiles

### Testing

To test this project, I had a friend play it for a few minutes and try to break it. He found several
issues that I was able to fix. My program has a few minor issues with it still that I was unable to 
fix in the alloted time. These issues are buttons not being disabled when I win or lose. They do 
disable when I give up, and I'm using the exact same lines of code to do the disabling, it just doesn't 
work when it's placed in the GridListener.



