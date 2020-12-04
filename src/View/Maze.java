package View;

import Model.*;

import javax.swing.*;
import java.awt.*;

public class Maze extends JPanel {

    public String[] letterMap;

    public Maze(String[] givenLetterMap){
        letterMap = givenLetterMap;
        System.out.println("This is the map, reporting back that I now have the letter map. " + letterMap[3]); //I now have an array of letters to create/decide which tiles to place where.

        this.setLayout(new GridLayout(0, 25));
        this.setBackground(Color.YELLOW);
        this.repaint();

//        this.add(new JButton());
//        this.add(new PathTile());
//        this.add(new JButton());
//        this.add(new JButton());
//        this.add(new JButton());
//
//        this.add(new JButton());
//        this.add(new JButton());
//        this.add(new JButton());
//        this.add(new JButton());
//        this.add(new JButton());

        //this demonstrates that I can create 250 buttons in the correct spots of the maze
//        for(int i = 0; i < 250; i++){ //Now I need to focus on getting the txtline input from the commandline textfile and create a simple map
//            //this.determineTile();
//            this.add(new JButton());
//        }

        MazeTile[][] tileArray = new MazeTile[10][25]; //this is my new solution
        for(int i = 0; i < 10; i++){
            String LetterLine = letterMap[i];
            char[] letters = LetterLine.toCharArray();
            for (int j = 0; j < 25; j++) {
                tileArray[i][j] = determineTile(letters[j]);
                this.add(tileArray[i][j]);
            }
        }

        //to make my maze 10 by 25
        //create all the different tile based on letter map
    }

    private MazeTile determineTile(char c) {
        if(c == 'B'){
            return new BorderTile();
        } else if(c == 'P'){
            return new PathTile();
        } else if(c == 'G'){
            return new BushTile();
        } else if(c == 'H'){
            return new TunnelTile();
        } else { return null;}
    }

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
