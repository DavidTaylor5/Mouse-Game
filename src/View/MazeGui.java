package View;

import Controller.Maze;
import Model.Cat;
import Model.MazeObject;
import Model.MazeTile;
import Model.Mouse;

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
    JLabel mouseIcon = new JLabel();
    JLabel cheeseIcon = new JLabel();
    JLabel amountLives = new JLabel();
    JLabel amountCheese = new JLabel();

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

        TimerClass gameTime = new TimerClass();
        gameTime.startGame();

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
        //cat5 start ^
//        MazeTile tile7 = myMaze.tileArray[2][2];
//        tile1.setMouse(myMaze.player1);
//        tile1.checkObjects();
//        tile1.repaint();
//        //cheese start ^
//        MazeTile tile8 = myMaze.tileArray[2][2];
//        tile1.setMouse(myMaze.player1);
//        tile1.checkObjects();
//        tile1.repaint();
//        //cheese start ^


    }
    public class TimerClass {


        public int secondsPassed = 0;


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
                //MazeGui.this.secondsPassed = secondsPassed;
            }
        };

        public void startGame(){
            myTimer.scheduleAtFixedRate(task, 1000, 1000);
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
        }
    }


}
