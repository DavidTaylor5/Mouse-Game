package Controller;

import Model.*;

import javax.swing.*;
import java.awt.*;

public class Maze extends JPanel {

    public String[] letterMap;
    public MazeTile[][] tileArray;

    //should I keep my mouse object here
    public Mouse player1 = new Mouse(2, 2);  //why does cat one randomly show up and disappear?
    public Cat cat1 = new Cat(6, 2, 1);
    public Cat cat2 = new Cat(7, 20, 2);
    public Cat cat3 = new Cat(7, 15, 3);
    public Cat cat4 = new Cat(2, 23, 4);
    public Cat cat5 = new Cat(8, 10, 5);

    //tester cat 2  //doesn't work for some reason
    //public Cat cat2 = new Cat(4, 22, 2);

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

    public void moveMouse(Mouse givenMouse, String direction, int numbMoves){
        MazeTile oldTile = tileArray[givenMouse.getxC()][givenMouse.getyC()];
        oldTile.setMouse(null);
        oldTile.checkObjects();
        MazeTile newTile = this.futureTile(oldTile, direction, numbMoves);
        // //checkInteraction() // mouse should only worry about interacting with cheese //Cat should worry about getting x y and interacting with mouse
        //I should auto generate cheese.
        newTile.checkObjects();
        newTile.setMouse(givenMouse); //now I just need to update the player so that it also knows to modify coordinates.
        //repaint
        oldTile.repaint();
        newTile.repaint();
        //MazeTile newPos = tileArray
        givenMouse.setxC(newTile.xTilePos);
        givenMouse.setyC(newTile.yTilePos);

    }

    public void moveCat(Cat cat){ //I don't think I want the maze to move the cats?
        //
        MazeTile oldTile = tileArray[cat.getxC()][cat.getyC()];
        oldTile.setCat1(null);
        oldTile.checkObjects();
        //MazeTile newTile = this.futureTile(oldTile, direction, numbMoves);

        MazeTile futureTile = null;  //make sure that future tile isn't null
        if (cat.numbCat == 1){

        } else if (cat.numbCat == 2){
            futureTile = cat2MoveSet(cat, oldTile);
        } else if (cat.numbCat == 3){ //cat 3 is done now for some of the more difficult cats, I don't look forward to cat 5
            futureTile = cat3MoveSet(cat, oldTile);
        } else if (cat.numbCat == 4){
            futureTile = cat4MoveSet(cat, oldTile); //cat 4 is done I also need a mouse check method but that shouldn't be to diffy
        } else if (cat.numbCat == 5){

        } else {
            System.out.println("Issue choosing cat icon.");
        }


        //System.out.println(cat.direction);
        //put this into if statment, I need to keep future tile null if it is not changing, don't change unless I'm moving
        if (futureTile != null) {
            if(futureTile.getCat1() == null){
                futureTile.setCat1(cat);
            } else {
                futureTile.setCat2(cat);
            }
            futureTile.checkObjects(); ///really I should just check for a mouse
            futureTile.repaint();
            cat.xC = futureTile.xTilePos;
            cat.yC = futureTile.yTilePos;
            oldTile.repaint();
        }






        // old code ^
        //figure something out for all the different cat move sets
    }


    public MazeTile cat2MoveSet(Cat cat2, MazeTile oldTile){
        String oppositeD = "";
        MazeTile futureTile = null;
        if(this.possibleCatMove(oldTile, cat2.direction, 1) && !cat2.direction.equalsIgnoreCase(oppositeD)){ //and the cat isn't going backwards ///can't go opposite
            futureTile = this.futureTile(oldTile, cat2.direction, 1);
            oppositeD = oppositeCatD(cat2.direction);
            System.out.println(oppositeD);
        } else {
            //futureTile = oldTile;  //issue with this code 133
            if(cat2.direction.equalsIgnoreCase("down")){
                cat2.setDirection("right");
            } else if(cat2.direction.equalsIgnoreCase("right")){
                cat2.setDirection("up");
            } else if(cat2.direction.equalsIgnoreCase("up")){
                cat2.setDirection("left");
            }
            else {
                cat2.setDirection("down");
            }
        }
        return futureTile;
    }

    public String oppositeCatD(String direction){
        if(direction.equalsIgnoreCase("up")){
            return "down";
        } else if(direction.equalsIgnoreCase("down")){
            return "up";
        } else if(direction.equalsIgnoreCase("left")){
            return "right";
        } else if(direction.equalsIgnoreCase("right")){
            return "left";
        } else {
            System.out.println("Problem with opposite direction method.");
            return "null";
        }
    }

    public MazeTile cat3MoveSet(Cat cat3, MazeTile oldTile){
        MazeTile futureTile;
        if(this.possibleCatMove(oldTile, cat3.direction, 1)){
            futureTile = this.futureTile(oldTile, cat3.direction, 1);
        } else {
            futureTile = oldTile;
            if(cat3.direction.equalsIgnoreCase("up")){
                cat3.setDirection("down");
            } else {cat3.setDirection("up");}
        } //also change direction

        return futureTile;
    }

    public MazeTile cat4MoveSet(Cat cat4, MazeTile oldTile){
        MazeTile futureTile;
        if(this.possibleCatMove(oldTile, cat4.direction, 1)){
            futureTile = this.futureTile(oldTile, cat4.direction, 1);
        } else {
            futureTile = oldTile;
            if(cat4.direction.equalsIgnoreCase("left")){
                cat4.setDirection("right");
            } else {cat4.setDirection("left");}
        } //also change direction

        return futureTile;
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

    public boolean possibleCatMove(MazeTile currentTile, String direction, int numbMoves){
        //right tile
        MazeTile lookTile = this.futureTile(currentTile, direction, numbMoves);

        if (lookTile.getAccess() == MazeTile.tileAccess.NOMOVE){
            return false;
        } else if(lookTile.getAccess() == MazeTile.tileAccess.ALLMOVE){
            return true;
        } else {
            System.out.println("Problem with cat determining right movement tile");
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
