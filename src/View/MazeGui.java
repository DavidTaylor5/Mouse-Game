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

public class MazeGui extends JFrame {


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

    public class TimerClass {


        public int secondsPassed = 0;


        Timer myTimer = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
                secondsPassed++;
                System.out.println(secondsPassed);
                MazeGui.this.timePassed.setText("Time Passed : " + secondsPassed);
                //make the cats move
                //MazeGui.this.secondsPassed = secondsPassed;
            }
        };

        public void startGame(){
            myTimer.scheduleAtFixedRate(task, 1000, 1000);
        }

    }



    public MazeGui(String[] argumentTxt){

//        while(secondsPassed <= 10){
//            myTimer.scheduleAtFixedRate(task, 1000, 1000);
//        }




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
        MazeTile tile = myMaze.tileArray[2][2]; //so I can interact with my map using my gui this is good
        tile.setHoldObject(true);
        tile.setCurrentObj(myMaze.player1);

        //
        MazeTile tileC = myMaze.tileArray[4][4];
        tileC.setHoldObject(true);
        tileC.setCurrentObj(new Cat(4, 4, 1));
        //

        tile.repaint();

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




    private class MoveHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent event) {
            //first I get the x and y position of maze array of mouse
            int xPos = getPlayerX();
            int yPos = getPlayerY();
            MazeTile currentTile = MazeGui.this.myMaze.tileArray[xPos][yPos];
            System.out.println(currentTile);
            System.out.println("can the tile move down? " + MazeGui.this.myMaze.possibleMove(currentTile, "down", 1));
            //there is an issue determining if the tile can move down.  //I need to give my mazeTile coordinates on instantiation


            if(event.getSource() == MazeGui.this.down){

                if(MazeGui.this.myMaze.possibleMove(currentTile, "down", 1)){
                    MazeGui.this.myMaze.moveObject(myMaze.player1, "down", 1);
                    System.out.println("Move Down!"); //don't forget to add actionListeners
                    //update where the mouse is, the x and y of the mouse, repaint the old tile, repaint the new tile
                }

            } else if(event.getSource() == MazeGui.this.up){

                if(MazeGui.this.myMaze.possibleMove(currentTile, "up", 1)){
                    MazeGui.this.myMaze.moveObject(myMaze.player1, "up", 1);
                    System.out.println("Move up!"); //don't forget to add actionListeners
                    //update where the mouse is, the x and y of the mouse, repaint the old tile, repaint the new tile
                }

            } else if(event.getSource() == MazeGui.this.left){

                if(MazeGui.this.myMaze.possibleMove(currentTile, "left", 1)){
                    MazeGui.this.myMaze.moveObject(myMaze.player1, "left", 1);
                    System.out.println("Move Left!"); //don't forget to add actionListeners
                    //update where the mouse is, the x and y of the mouse, repaint the old tile, repaint the new tile
                }

            } else if(event.getSource() == MazeGui.this.right){

                if(MazeGui.this.myMaze.possibleMove(currentTile, "right", 1)){
                    MazeGui.this.myMaze.moveObject(myMaze.player1, "right", 1);
                    System.out.println("Move Right!"); //don't forget to add actionListeners
                    //update where the mouse is, the x and y of the mouse, repaint the old tile, repaint the new tile
                }

            }
        }
    }


}
