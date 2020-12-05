package Model;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class ImageTutorial extends JFrame {
    private ImageIcon image1;
    private JLabel label1;

    ImageTutorial() throws IOException {
        setLayout(new FlowLayout());
//
//        image1 = new ImageIcon(getClass().getResource("pikachu-crop.png")); //apprenently pictures need to be in same package.
//
//        label1 = new JLabel(image1);
//        add(label1);
    }

    public static void main(String[] args) throws IOException {
        ImageTutorial gui = new ImageTutorial();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setVisible(true);
        //PathTile myPath = new PathTile();
        //gui.add(myPath);
        //gui.pack();
    }

}
