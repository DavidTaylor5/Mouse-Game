package Model;

import javax.swing.*;

public class Mouse extends MazeObject {

    public int mouseLives = 3;


    ImageIcon mouseImage = new ImageIcon(getClass().getResource("pikachu-crop.png"));

    public Mouse(int x, int y){
        this.setxC(x);
        this.setyC(y);
        this.setObjectImage(mouseImage);
    }

    public int getMouseLives() {
        return mouseLives;
    }

    public void setMouseLives(int mouseLives) {
        this.mouseLives = mouseLives;
    }

}
