package Model;

import javax.swing.*;

public class Mouse extends MazeObject {

    public int mouseLives = 3;


    ImageIcon mouseImage = new ImageIcon(getClass().getResource("pikachu-crop.png"));

    public Mouse(int x, int y){
        this.setxC(x);
        this.setyC(y);
        this.setObjectImage(mouseImage);
        super.setType(objectType.MOUSETYPE);
    }

    public void respawn(){
        this.setxC(2);
        this.setyC(2); //how do I update the tile though?
    }

    public int getMouseLives() {
        return mouseLives;
    }

    public void setMouseLives(int mouseLives) {
        this.mouseLives = mouseLives;
    }

}
