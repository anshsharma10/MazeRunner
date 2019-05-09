import java.util.*;
import java.io.File;

/*
 * The MazeRunner class. Attempts to run through a given 5x5 maze and find the exit.
 * 
 * @author Ansh Sharma
 * @version 1 04.15.2019
 *
 */
public class MazeRunner {
  /*
   * The class constructor. Does not take any parameters nor do anything, as the class's purpose is for its static method runMaze.
   */
  public MazeRunner() {
    
  }
  /*
   * Searches through the entire maze for an exit, and outputs true if an exit is found. Recursively tests each possible path.
   * There are five cases:
   * 
   * The first case is if the method has reached a potential end of the maze where x or y equal 4 and are greater than 0. If so,
   * the method will test to see if that location within the array is an exit and return it.
   * 
   * The second case is if the method has been called for the first time, and it will call itself once for each possible entrance 
   * location to check if it is an entrance. If any of these locations return true, then a path has been found and true will be
   * returned. If they all return false, then that means there is no available path within the input.
   * 
   * The third case is if the method has been called on an entrance location. If the location is on the y axis, the method will
   * be called once more, but one spot down on the array. Likewise, if the entrance is on the x axis, the method will be called
   * one spot to the right on the array. If true is returned, then a path has been found and true will be returned once more. If
   * false is returned, then there is no exit on that entrance location.
   * 
   * The fourth and final case is if the coordinates of the array are inside the maze on a viable path. The method will check in
   * each cardinal direction, and call itself again in that direction. If any of those directions return true, then that means
   * that a path has been found and the method will return true. If they return false, then that area is a dead end and false
   * will be returned.
   * 
   * Otherwise, false will be returned. This is the base case, and typically occurs when a wall is found.
   * 
   * @param array The inputted array to be searched through.
   * @param x The x coordinate within the array that must be searched.
   * @param y The y coordinate within the array that must be searched.
   * @param dir The direction that the search has just moved in. Possible values are u, d, l, and r, for up, down, left, and right respectively.
   *            They are used to make sure that the method does not get stuck in a loop by attempting to traverse through the same coordinates
   *            repeatedly. There is also a value of 'A' used to start the method in base case 2.
   */
  public static boolean runMaze(boolean[][] array, int x, int y, char dir) {
    if ((x == 4 && y > 0)||(y == 4 && x > 0)){
      return array[x][y];}
    else if (x == 0 && y == 0 && dir == 'A') {
      return (runMaze(array, 0, 1, 'A')||runMaze(array, 0, 2, 'A')||runMaze(array, 0, 3, 'A')||runMaze(array, 0, 4, 'A')||runMaze(array, 1, 0, 'A')||runMaze(array, 2, 0, 'A')||runMaze(array, 3, 0, 'A')||runMaze(array, 4, 0, 'A'));
    }
    else if (x == 0 && array[x][y] == true && dir == 'A'){
      return (runMaze(array, 1, y, 'u'));}
    else if (y == 0 && array[x][y] == true && dir == 'A'){
      return (runMaze(array, x, 1, 'l'));}
    else if (x > 0 && y > 0 && array[x][y] == true) {
      boolean checkLeft = false, checkRight = false, checkUp = false, checkDown = false;
      if (y < 4 && dir != 'r'){
        checkRight = runMaze(array, x, y + 1, 'l');
        if (checkRight == true)
          return true;
      }
      if (x < 4 && dir != 'd') {
        checkDown = runMaze(array, x + 1, y, 'u');
        if (checkDown == true)
          return true;}
      
      if (y > 1 && dir != 'l') {
        checkLeft = runMaze(array, x, y-1, 'r');
        if (checkLeft == true)
          return true;
      }
      if (x > 1 && dir != 'u') {
        checkUp = runMaze(array, x - 1, y, 'd');
        if (checkUp == true)
          return true;
      }
      return false;
    }
    else
      return false;
  }
  
  /*
   * The main method where input is received, and the runMaze method is called. Takes the maze from maze.txt, and turns it into a maze array.
   * Afterwards, it calls runMaze on the maze array with default values of x = 0, y = 0, and dir = 'A'.
   * @param args The arguments of the method.
   */
  public static void main (String[] args) {
    boolean[][] array = new boolean[5][5];
    try {
      Scanner scan = new Scanner(new File("maze.txt"));
      for (int i = 0; i < 5; i++) {
        String input = scan.nextLine();
        for (int j = 0; j < 5; j++) {
          if (input.charAt(j) == '1')
            array[i][j] = false;
          else
            array[i][j] = true;
        }
      }
    } catch (Exception e) {}
    
    System.out.println("\n" + MazeRunner.runMaze(array, 0, 0, 'A'));
  }
}