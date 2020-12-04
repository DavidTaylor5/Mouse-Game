package View;

import Model.PathTile;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MazeGui extends JFrame {

    //JPanel maze;  //create the map with grid in another class then instantiate here
    JPanel northPanel = new JPanel();
    JPanel southPanel = new JPanel();

    JButton down = new JButton();
    JButton up = new JButton();
    JButton left = new JButton();
    JButton right = new JButton();
    ButtonGroup btg = new ButtonGroup();

    JButton start = new JButton("Start");
    JButton stop = new JButton("Stop");
    JButton reset = new JButton("Reset");

    JLabel mouseIcon = new JLabel();
    JLabel cheeseIcon = new JLabel();
    JLabel amountLives = new JLabel();
    JLabel amountCheese = new JLabel();

    ImageIcon mouse = new ImageIcon("mickey-mouse.png"); ///????


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


        this.add(new Maze(letterMap), BorderLayout.CENTER);

//        JPanel maze23 = new JPanel();
//        maze23.setBackground(Color.BLUE);
//        this.add(maze23, BorderLayout.CENTER);

//        //score panel
//        this.add(northPanel, BorderLayout.NORTH);
//        northPanel.setLayout(new FlowLayout());
//        northPanel.add(start);
//        northPanel.add(stop);
//        northPanel.add(reset);
//        northPanel.add(mouseIcon);
//        northPanel.add(cheeseIcon);
//        northPanel.add(amountLives);
//        northPanel.add(amountCheese);
//
//        //action panel
//        this.add(southPanel, BorderLayout.SOUTH);
//        southPanel.setLayout(new FlowLayout());
//        southPanel.add(up);
//        southPanel.add(down);
//        southPanel.add(left);
//        southPanel.add(right);



    }
}
