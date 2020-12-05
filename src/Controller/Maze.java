package Controller;

import Model.*;

import javax.swing.*;
import java.awt.*;

public class Maze extends JPanel {

    public String[] letterMap;
    public MazeTile[][] tileArray;

    //should I keep my mouse object here
    public Mouse player1 = new Mouse(2, 2);

    public Maze(String[] givenLetterMap){
        letterMap = givenLetterMap;
        System.out.println("This is the map, reporting back that I now have the letter map. " + letterMap[3]); //I now have an array of letters to create/decide which tiles to place where.

        this.setLayout(new GridLayout(0, 25));
        this.setBackground(Color.YELLOW);
        this.repaint();


        //to make my maze 10 by 25
        //create all the different tile based on letter map
        tileArray = new MazeTile[10][25];
        //MazeTile[][] tileArray = new MazeTile[10][25]; //this is my new solution
        for(int i = 0; i < 10; i++){
            String LetterLine = letterMap[i];
            char[] letters = LetterLine.toCharArray();
            for (int j = 0; j < 25; j++) {
                tileArray[i][j] = determineTile(letters[j], i, j);
                this.add(tileArray[i][j]);
            }
        }

        startingPositions();

    }

    private MazeTile determineTile(char c, int i, int j) {
        if(c == 'B'){
            return new BorderTile(i, j);
        } else if(c == 'P'){
            return new PathTile(i, j);
        } else if(c == 'G'){
            return new BushTile(i, j);
        } else if(c == 'H'){
            return new TunnelTile(i, j);
        } else { return null;}
    }

    public void startingPositions(){
        //tileArray[2][2]
    }

    public void moveObject(MazeObject object, String direction, int numbMoves){
        MazeTile oldTile = tileArray[object.getxC()][object.getyC()];
        oldTile.setCurrentObj(null);
        oldTile.setHoldObject(false);
        MazeTile newTile = this.futureTile(oldTile, direction, numbMoves);
        newTile.setCurrentObj(object);
        newTile.setHoldObject(true);
        newTile.setCurrentObj(object); //now I just need to update the player so that it also knows to modify coordinates.
        //repaint
        oldTile.repaint();
        newTile.repaint();
        //MazeTile newPos = tileArray
    }

    public MazeTile futureTile(MazeTile currentTile, String direction, int numbMoves){
        MazeTile lookTile;
        int currentX = currentTile.getxTilePos();
        int currentY = currentTile.getyTilePos();

        if(direction.equalsIgnoreCase("right")){
            return lookTile = tileArray[currentX][currentY + numbMoves];
        } else if(direction.equalsIgnoreCase("left")){
            return lookTile = tileArray[currentX][currentY - numbMoves];
        } else if(direction.equalsIgnoreCase("up")) {
            return lookTile = tileArray[currentX - numbMoves][currentY];
        } else if(direction.equalsIgnoreCase("down")) {
            return lookTile = tileArray[currentX + numbMoves][currentY];
        } else {
            System.out.println("Problem with determining right movement tile");
            return null;
        }

    }




    public boolean possibleMove(MazeTile currentTile, String direction, int numbMoves){
        //right tile
        MazeTile lookTile = this.futureTile(currentTile, direction, numbMoves);

        if (lookTile.getAccess() == MazeTile.tileAccess.NOMOVE){
            return false;
        } else if(lookTile.getAccess() == MazeTile.tileAccess.ALLMOVE){
            return true;
        } else if(lookTile.getAccess() == MazeTile.tileAccess.MOUSEMOVE){
            return true;
        } else {
            System.out.println("Problem with determining right movement tile");
            return false;
        }
    }

//    public void mouseMove(String direction){
//        if(direction.equalsIgnoreCase("right")){
//            //I need to repaint my current location
//            //I need to repaint where I go.
//            lookTile = tileArray[x][y+1];
//        } else if(direction.equalsIgnoreCase("left")){
//            lookTile = tileArray[x][y-1];
//        } else if(direction.equalsIgnoreCase("up")) {
//            lookTile = tileArray[x-1][y];
//        } else if(direction.equalsIgnoreCase("down")) {
//            lookTile = tileArray[x+1][y];
//        } else {
//            System.out.println("Problem with determining right movement tile");
//            return false;
//        }
//    }



//    public boolean catPossibleRight(int x, int y){  //workshop cat movement later
//
//        MazeTile rightTile = tileArray[x][y+1];
//
//        if (rightTile.getAccess() == MazeTile.tileAccess.NOMOVE){
//            return false;
//        } else if(rightTile.getAccess() == MazeTile.tileAccess.ALLMOVE){
//            return true;
//        } else if(rightTile.getAccess() == MazeTile.tileAccess.MOUSEMOVE){
//            return true;
//        } else {
//            System.out.println("Problem with determining right movement tile");
//            return false;
//        }
//    }

//    @Override
//    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        //this.setLayout(new GridLayout(10, 25));
//        this.setBackground(Color.CYAN);
//        this.setPreferredSize(new Dimension(500, 400));
//        this.setLayout(new GridLayout());
//        this.add(new PathTile());
//        for(int i = 0; i < 10; i++){
//            this.add(new PathTile());
//        }
    }
