package Model;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class ImageTutorial extends JFrame {
    private ImageIcon image1;
    private JLabel label1;

    private ImageIcon image2;

    ImageTutorial() {
        setLayout(new FlowLayout());

        //image1 = new ImageIcon(getClass().getResource("pikachu-crop.png")); //apprenently pictures need to be in same package.
        //ImageIcon image2 = new ImageIcon("src/Model/cartoon-unknown-cat.png");
        Cheese cheeesee = new Cheese(4, 4);
        Cat chatCat = new Cat(4, 4, 4);
        //label1 = new JLabel(image1);
        //label1 = new JLabel(cat1.getObjectImage());
        label1 = new JLabel(cheeesee.getObjectImage());
        add(label1);
    }

    public static void main(String[] args) throws IOException {
        ImageTutorial gui = new ImageTutorial();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setVisible(true);
        PathTile myPath = new PathTile(2, 2);
        gui.add(myPath);
        gui.pack();
    }

}
