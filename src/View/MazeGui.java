package View;

import javax.swing.*;
import java.awt.*;

public class MazeGui extends JFrame {

    JPanel maze;  //create the map with grid in another class then instantiate here
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


    public MazeGui(){
        this.setTitle("Mouse Maze Game");
        this.setLayout(new BorderLayout());
        this.setVisible(true);
        this.setSize(new Dimension(650, 400));

        //score panel
        this.add(northPanel, BorderLayout.NORTH);
        northPanel.setLayout(new FlowLayout());
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

        this.add(new JButton(), BorderLayout.CENTER); //I can't get anything to show...


    }
}
