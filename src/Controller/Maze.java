package Controller;

import Model.*;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.Random;

import static java.lang.Math.abs;

public class Maze extends JPanel {

    public String[] letterMap;
    public MazeTile[][] tileArray;

    //should I keep my mouse object here
    //public Mouse player1 = new Mouse(2, 2);  //why does cat one randomly show up and disappear?
    public Cat cat1 = new Cat(6, 2, 1);
    public Cat cat2 = new Cat(7, 20, 2);
    public Cat cat3 = new Cat(7, 15, 3);
    public Cat cat4 = new Cat(2, 23, 4);
    //public Cat cat5 = new Cat(8, 10, 5);

    //tester cat5 and player 1
    public Mouse player1 = new Mouse(1, 6);
    public Cat cat5 = new Cat(2, 6, 5);

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

    public void moveCat(Cat cat, Mouse mouse){ //I don't think I want the maze to move the cats?
        //
        MazeTile oldTile = tileArray[cat.getxC()][cat.getyC()];
        oldTile.setCat1(null);
        oldTile.checkObjects();
        //MazeTile newTile = this.futureTile(oldTile, direction, numbMoves);

        MazeTile futureTile = null;  //make sure that future tile isn't null
        if (cat.numbCat == 1){
            futureTile = cat1MoveSet(cat1, oldTile);
        } else if (cat.numbCat == 2){
            futureTile = cat2MoveSet(cat, oldTile);
        } else if (cat.numbCat == 3){ //cat 3 is done now for some of the more difficult cats, I don't look forward to cat 5
            futureTile = cat3MoveSet(cat, oldTile);
        } else if (cat.numbCat == 4){
            futureTile = cat4MoveSet(cat, oldTile); //cat 4 is done I also need a mouse check method but that shouldn't be to diffy
        } else if (cat.numbCat == 5){
            futureTile = cat5MoveSet(cat, oldTile, mouse);
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

            oldTile.checkObjects();
            oldTile.repaint();
        }






        // old code ^
        //figure something out for all the different cat move sets
    }

    public MazeTile cat1MoveSet(Cat cat1, MazeTile oldTile){ //appears to be working.
        MazeTile futureTile;
        String determineDirection = randomIntersection(cat1, oldTile);
        if(determineDirection.isEmpty()){
            futureTile = futureTile(oldTile, cat1.oppositeD, 1);
            cat1.direction = cat1.oppositeD;
            cat1.oppositeD = this.oppositeCatD(cat1.direction);

        } else {
            futureTile = futureTile(oldTile, determineDirection, 1);
            cat1.setDirection(determineDirection);
            cat1.setOppositeD(this.oppositeCatD(cat1.direction));
        }
        return futureTile;
    }

    public String randomIntersection(Cat cat, MazeTile oldTile){ //should also work when a cat is going in a straight line
        //int numbRoutes;
        LinkedList<String> possibleRoutes = new LinkedList<String>(); //I need to make sure that 0 = up, 1 = down, 2 = left, 3 = down
        if(possibleCatMove(oldTile, "up", 1) && !cat.oppositeD.equalsIgnoreCase("up")){
            possibleRoutes.add("up");
        }
        if(possibleCatMove(oldTile, "down", 1) && !cat.oppositeD.equalsIgnoreCase("down")){
            possibleRoutes.add("down");
        }
        if(possibleCatMove(oldTile, "left", 1) && !cat.oppositeD.equalsIgnoreCase("left")){
            possibleRoutes.add("left");
        }
        if(possibleCatMove(oldTile, "right", 1) && !cat.oppositeD.equalsIgnoreCase("right")){
            possibleRoutes.add("right");
        }
        if(possibleRoutes.size() != 0){
            Random random = new Random();
            int randomInt = random.nextInt(possibleRoutes.size());
            return possibleRoutes.get(randomInt);
        } else {
            System.out.println("There was a difficulty determining cat 1 move. (intersection 149 ");
            return "";
        }


    }


    public MazeTile cat2MoveSet(Cat cat2, MazeTile oldTile){
        String oppositeD = cat2.oppositeD;
        MazeTile futureTile = null;
        if(this.possibleCatMove(oldTile, cat2.direction, 1) && !cat2.direction.equalsIgnoreCase(oppositeD)){ //and the cat isn't going backwards ///can't go opposite
            futureTile = this.futureTile(oldTile, cat2.direction, 1);
            cat2.oppositeD = oppositeCatD(cat2.direction);
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

    public MazeTile cat5MoveSet(Cat cat5, MazeTile oldTile, Mouse mouse){
        MazeTile futureTile = null;
        LinkedList<String> necessaryDirections = determineDirections(cat5, oldTile, mouse);
        int[] optimalMoves = optimalMoves(cat5, mouse); //should give optimal moves x/down/up and y/left/right


        //I have to make sure a non optimal move is possible
        boolean alreadyMoved = false;
        if(necessaryDirections.size() > 1){
            if(possibleCatMove(oldTile, necessaryDirections.get(1), 1)){
                for (int i = 1; i <= 3 ; i++) {
                    if((i + cat5.getxC() < 10) && (cat5.getxC() - i > 0)){
                        if(possibleCatMove(oldTile, necessaryDirections.get(1), i)){
                            futureTile = futureTile(oldTile, necessaryDirections.get(1), i);
                            alreadyMoved = true;
                        }
                    }
                }
            }
        }

        if(necessaryDirections.size() >0){
            if(possibleCatMove(oldTile, necessaryDirections.get(0), 1) && !alreadyMoved){
                for (int i = 1; i <= 3 ; i++) {
                    if(necessaryDirections.get(1).equalsIgnoreCase("left") || necessaryDirections.get(1).equalsIgnoreCase("Right")){

                    }//
                    if((i + cat5.getyC() < 25) && (cat5.getyC() - i > 0)){
                        if(possibleCatMove(oldTile, necessaryDirections.get(0), i)){  //test index bounds
                            futureTile = futureTile(oldTile, necessaryDirections.get(0), i);
                            alreadyMoved = true;
                        }
                    }
                }
            }
        }


        if(!alreadyMoved){
            futureTile = cat1MoveSet(cat5, oldTile);
        }

        return futureTile;
        //if(this.possibleCatMove(oldTile, direction, 3))
    }

    public int[] optimalMoves(Cat cat, Mouse mouse){
        int[] movesXY = new int[2];
        int absX = Math.abs(cat.getxC() - mouse.getxC());
        int absY = Math.abs(cat.getyC() - mouse.getyC());
        movesXY[0] = absY; //corresponds to left or right
        movesXY[1] = absX;
        return movesXY;
    }


    public LinkedList<String> determineDirections(Cat cat, MazeTile oldTile, Mouse mouse){
        int catX = cat.getxC();
        int catY = cat.getyC();
        int mouseX = mouse.getxC();
        int mouseY = mouse.getyC();
        LinkedList<String> necessaryDirection = new LinkedList<String>();

        if(catY <  mouseY){
            necessaryDirection.add("RIGHT");
        } else if(catY > mouseY) {
            necessaryDirection.add("LEFT");
        }

        if(catX < mouseX){
            necessaryDirection.add("DOWN");
        } else if(catY > mouseX){
            necessaryDirection.add("UP");
        }

        return necessaryDirection;
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
