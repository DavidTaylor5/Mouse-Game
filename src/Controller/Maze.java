package Controller;

import javax.swing.*;
import java.awt.*;

public class Maze extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setLayout(new GridLayout());
    }
}
