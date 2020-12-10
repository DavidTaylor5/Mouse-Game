package View;

import Controller.Maze;
import Model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class MazeGui extends JFrame { //cat move should check for a mouse and mouse move should check for a cat


    Maze myMaze;
    JPanel northPanel = new JPanel();
    JPanel southPanel = new JPanel();

    JButton down = new JButton("DOWN ");
    JButton up = new JButton(" UP  ");
    JButton left = new JButton("Left ");
    JButton right = new JButton("RIGHT ");
    ButtonGroup btg = new ButtonGroup();

    JButton start = new JButton("Start");
    JButton stop = new JButton("Stop");
    JButton reset = new JButton("Reset");

    JLabel timePassed = new JLabel("0");
    JLabel mouseIcon = new JLabel(new Mouse(0, 0).getObjectImage());
    JLabel cheeseIcon = new JLabel(new Cheese(0, 0).getObjectImage());
    JLabel amountLives = new JLabel("LIVES 3");
    JLabel amountCheese = new JLabel("CHEESE SCORE 0");

    public int secondsPassed = 0;
    public int scoreBoard = 0;
    public int mouseLives = 3;

    public TimerClass gameTime = new TimerClass();
    public InteractionTimer catAndMouse = new InteractionTimer();

    //public int secondsPassed;

//    public class TimerClass {
//
//
//        public int secondsPassed = 0;
//
//
//        Timer myTimer = new Timer();
//        TimerTask task = new TimerTask() {
//            public void run() {
//                secondsPassed++;
//                System.out.println(secondsPassed);
//                MazeGui.this.timePassed.setText("Time Passed : " + secondsPassed);
//                //make the cats move
//
//                //MazeGui.this.secondsPassed = secondsPassed;
//            }
//        };
//
//        public void startGame(){
//            myTimer.scheduleAtFixedRate(task, 1000, 1000);
//        }
//
//    }



    public MazeGui(String[] argumentTxt){


        String[] letterMap = new String[10];

        //get the txt map and put it into some sort of array or something
        if(argumentTxt.length == 1) {
            String givenTxtFileName = argumentTxt[0];
            File fileInput = new File("src/" + givenTxtFileName);
            String line;
            int counter = 0;
            try {
                Scanner fileScanner = new Scanner(fileInput);
                while (fileScanner.hasNextLine()) {
                    line = fileScanner.nextLine();
                    letterMap[counter] = line;
                    counter++;
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        System.out.println(letterMap[9]);

        this.setTitle("Mouse Maze Game");
        this.setLayout(new BorderLayout());
        this.setVisible(true);
        this.setSize(new Dimension(650, 400));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        myMaze = new Maze(letterMap);
        this.add(myMaze, BorderLayout.CENTER);
        //////////////////////////
        //this proves I can paint pikachu on JPanel
        //

        this.startingObjectLocations(myMaze);
        /// method to set up all of the mazes

        //looks like I am able to look around the map at possible mouse moves.
//        System.out.println("Is left possible? " + myMaze.possibleMove(2, 2, "left"));
//        System.out.println("Is right possible? " + myMaze.possibleMove(2, 2, "right"));
//        System.out.println("Is up possible? " + myMaze.possibleMove(2, 2, "up"));
//        System.out.println("Is down possible? " + myMaze.possibleMove(2, 2, "down"));
        //if(tile)



        //score panel
        this.add(northPanel, BorderLayout.NORTH);
        northPanel.setLayout(new FlowLayout());
        northPanel.add(timePassed);
        northPanel.add(start);
        northPanel.add(stop);
        northPanel.add(reset);
        northPanel.add(mouseIcon);
        northPanel.add(cheeseIcon);
        northPanel.add(amountLives);
        northPanel.add(amountCheese);

        //menu actionhandlers
        MenuHandler menuHandler = new MenuHandler();
        this.start.addActionListener(menuHandler);
        this.stop.addActionListener(menuHandler);
        this.reset.addActionListener(menuHandler);

        //action panel
        this.add(southPanel, BorderLayout.SOUTH);
        southPanel.setLayout(new FlowLayout());
        southPanel.add(up);
        southPanel.add(down);
        southPanel.add(left);
        southPanel.add(right);

        //add ActionListeners to the mouse keys
        MoveHandler moveHandler = new MoveHandler();
        up.addActionListener(moveHandler);
        down.addActionListener(moveHandler);
        left.addActionListener(moveHandler);
        right.addActionListener(moveHandler);

        //TimerClass gameTime = new TimerClass();
        gameTime.startGame();

        //InteractionTimer catAndMouse = new InteractionTimer();
        catAndMouse.checkInteraction();

    }


    public int getPlayerX(){
        return MazeGui.this.myMaze.player1.getxC();
    }

    public int getPlayerY(){
        return MazeGui.this.myMaze.player1.getyC();
    }

    public void startingObjectLocations(Maze myMaze){

        MazeTile tile1 = myMaze.tileArray[myMaze.player1.xC][myMaze.player1.yC];
        tile1.setMouse(myMaze.player1);
        tile1.checkObjects();
        tile1.repaint();
        //mouse start ^
        MazeTile tile2 = myMaze.tileArray[myMaze.cat1.xC][myMaze.cat1.yC];
        tile2.setCat1(myMaze.cat1);
        tile2.checkObjects();
        tile2.repaint();
        //cat1 start ^
        MazeTile tile3 = myMaze.tileArray[myMaze.cat2.xC][myMaze.cat2.yC];
        tile3.setCat1(myMaze.cat2);
        tile3.checkObjects();
        tile3.repaint();
        //cat2 start ^
        MazeTile tile4 = myMaze.tileArray[myMaze.cat3.xC][myMaze.cat3.yC];
        tile4.setCat1(myMaze.cat3);
        tile4.checkObjects();
        tile4.repaint();
        //cat3 start ^
        MazeTile tile5 = myMaze.tileArray[myMaze.cat4.xC][myMaze.cat4.yC];
        tile5.setCat1(myMaze.cat4);
        tile5.checkObjects();
        tile5.repaint();
        //cat4 start ^
        MazeTile tile6 = myMaze.tileArray[myMaze.cat5.xC][myMaze.cat5.yC];
        tile6.setCat1(myMaze.cat5);
        tile6.checkObjects();
        tile6.repaint();
        //extra for cat 5
         this.myMaze.catOldTile = this.myMaze.tileArray[myMaze.cat5.getxC()][myMaze.cat5.getyC()];
        //
        //cat5 start ^
        MazeTile tile7 = myMaze.tileArray[myMaze.cheese1.xC][myMaze.cheese1.yC];
        tile7.setCheese(myMaze.cheese1);
        tile7.checkObjects();
        tile7.repaint();
//        //cheese start ^
        MazeTile tile8 = myMaze.tileArray[myMaze.cheese2.xC][myMaze.cheese2.yC];
        tile8.setCheese(myMaze.cheese2);
        tile8.checkObjects();
        tile8.repaint();
//        //cheese start ^
        MazeTile tile9 = myMaze.tileArray[myMaze.cheese3.xC][myMaze.cheese3.yC];
        tile9.setCheese(myMaze.cheese3);
        tile9.checkObjects();
        tile9.repaint();
//        //cheese start ^
        MazeTile tile10 = myMaze.tileArray[myMaze.cheese4.xC][myMaze.cheese4.yC];
        tile10.setCheese(myMaze.cheese4);
        tile10.checkObjects();
        tile10.repaint();
//        //cheese start ^


    }
    public class TimerClass {

        Timer myTimer = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
                secondsPassed++;
                //System.out.println(secondsPassed); prints to the console each second passed
                MazeGui.this.timePassed.setText("Time Passed : " + secondsPassed);
                //make the cats move
                MazeGui.this.myMaze.moveCat(myMaze.cat1, myMaze.player1);
                MazeGui.this.myMaze.moveCat(myMaze.cat2, myMaze.player1);
                MazeGui.this.myMaze.moveCat(myMaze.cat3, myMaze.player1);
                MazeGui.this.myMaze.moveCat(myMaze.cat4, myMaze.player1);
                MazeGui.this.myMaze.moveCat(myMaze.cat5, myMaze.player1);

//                boolean conflict = MazeGui.this.myMaze.allCatsCheck(myMaze.cat1, myMaze.cat2, myMaze.cat3, myMaze.cat4, myMaze.cat5);  //this code might not be optimal
//                if(conflict){
//                    MazeGui.this.myMaze.player1.respawn();
//                    MazeTile respawnTile = MazeGui.this.myMaze.tileArray[myMaze.player1.getxC()][myMaze.player1.getyC()];
//                    respawnTile.setMouse(myMaze.player1);
//                    respawnTile.repaint(); //maybe I should Also make it the responsiblity of the mouse  //maybe I should make it the resposibility of the mouse, Maybe I just need a fast timer
//                    MazeGui.this.mouseLives -= 1;
//                    MazeGui.this.amountLives.setText("LIVES " + MazeGui.this.mouseLives);
//
//                    //mouse.respawn
//                    //lives go down
//                    //make sure lives aren't zero or end game.
//                }
                //MazeGui.this.secondsPassed = secondsPassed;
            }
        };


        public void pause(){
            myTimer.cancel();
        }

        public void resume() {
            myTimer = new Timer();
            myTimer.schedule(task, 0, 1000 );
        }

        public void startGame(){
            myTimer.scheduleAtFixedRate(task, 1000, 1000);
        }

    }

    public class InteractionTimer {  //the second time is not a gimmick. I wonder how many threads it would take to actually crash or slow down this pc.


        Timer myTimer = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
                //System.out.println(secondsPassed); prints to the console each second passed
                //make the cats move

                boolean conflict = MazeGui.this.myMaze.allCatsCheck(myMaze.cat1, myMaze.cat2, myMaze.cat3, myMaze.cat4, myMaze.cat5);  //this code might not be optimal
                if(conflict){
                    MazeGui.this.myMaze.player1.respawn();
                    MazeTile respawnTile = MazeGui.this.myMaze.tileArray[myMaze.player1.getxC()][myMaze.player1.getyC()];
                    respawnTile.setMouse(myMaze.player1);
                    respawnTile.repaint(); //maybe I should Also make it the responsiblity of the mouse  //maybe I should make it the resposibility of the mouse, Maybe I just need a fast timer
                    MazeGui.this.mouseLives -= 1;
                    MazeGui.this.amountLives.setText("LIVES " + MazeGui.this.mouseLives);

                    //mouse.respawn
                    //lives go down
                    //make sure lives aren't zero or end game.
                }
                //MazeGui.this.secondsPassed = secondsPassed;  //pausing the game would mean stopping the timers and inactivating the buttons. //play would mean the opposite.
            }


        };

        public void pause(){
            myTimer.cancel();
        }

        public void resume() {
            myTimer = new Timer();
            myTimer.schedule( task, 0, 1000 );
        }

        public void checkInteraction(){
            myTimer.scheduleAtFixedRate(task, 1000, 100);
        }

    }


    private class MenuHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent event) {
            if(event.getSource() == MazeGui.this.start){
//                MazeGui.this.gameTime.resume();
//                MazeGui.this.catAndMouse.resume();
                MazeGui.this.gameTime = new TimerClass();
                gameTime.startGame();
            } else if(event.getSource() == MazeGui.this.stop){
                MazeGui.this.gameTime.pause();
                //MazeGui.this.catAndMouse.pause();
            } else if(event.getSource() == MazeGui.this.reset){
                MazeGui.this.gameTime.pause();

                Maze nextMaze = new Maze(myMaze.letterMap);
                MazeGui.this.myMaze = nextMaze;
                MazeGui.this.startingObjectLocations(nextMaze);
                MazeGui.this.add(nextMaze, BorderLayout.CENTER);
                MazeGui.this.repaint();

                MazeGui.this.gameTime = new TimerClass();
                gameTime.startGame();
            }

        }
    }



    private class MoveHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent event) {
            //first I get the x and y position of maze array of mouse
            int xPos = getPlayerX();
            int yPos = getPlayerY();
            MazeTile currentTile = MazeGui.this.myMaze.tileArray[xPos][yPos];
            System.out.println(currentTile); //prints the previous tile
            System.out.println("can the tile move down? " + MazeGui.this.myMaze.possibleMove(currentTile, "down", 1));
            //there is an issue determining if the tile can move down.  //I need to give my mazeTile coordinates on instantiation


            if(event.getSource() == MazeGui.this.down){

                if(MazeGui.this.myMaze.possibleMove(currentTile, "down", 1)){
                    MazeGui.this.myMaze.moveMouse(myMaze.player1, "down", 1);
                    System.out.println("Move Down!"); //don't forget to add actionListeners
                    //update where the mouse is, the x and y of the mouse, repaint the old tile, repaint the new tile
                }

            } else if(event.getSource() == MazeGui.this.up){

                if(MazeGui.this.myMaze.possibleMove(currentTile, "up", 1)){
                    MazeGui.this.myMaze.moveMouse(myMaze.player1, "up", 1);
                    System.out.println("Move up!"); //don't forget to add actionListeners
                    //update where the mouse is, the x and y of the mouse, repaint the old tile, repaint the new tile
                }

            } else if(event.getSource() == MazeGui.this.left){

                if(MazeGui.this.myMaze.possibleMove(currentTile, "left", 1)){
                    MazeGui.this.myMaze.moveMouse(myMaze.player1, "left", 1);
                    System.out.println("Move Left!"); //don't forget to add actionListeners
                    //update where the mouse is, the x and y of the mouse, repaint the old tile, repaint the new tile
                }

            } else if(event.getSource() == MazeGui.this.right){

                if(MazeGui.this.myMaze.possibleMove(currentTile, "right", 1)){
                    MazeGui.this.myMaze.moveMouse(myMaze.player1, "right", 1);
                    System.out.println("Move Right!"); //don't forget to add actionListeners
                    //update where the mouse is, the x and y of the mouse, repaint the old tile, repaint the new tile
                }

            }
            //check for cheese tile
            boolean scoreUp = MazeGui.this.myMaze.tileArray[MazeGui.this.myMaze.player1.getxC()][MazeGui.this.myMaze.player1.getyC()].cheeseInteraction();
            if(scoreUp){
                scoreBoard += 100;
                MazeGui.this.amountCheese.setText("CHEESE SCORE " + scoreBoard);
            }
        }
    }


}
