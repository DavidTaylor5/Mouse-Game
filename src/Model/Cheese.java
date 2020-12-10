package Model;

import javax.swing.*;

public class Cheese extends MazeObject{

    ImageIcon cheeseIcon = new ImageIcon("src/Model/cheese-25.png");

    public Cheese(int x, int y){
        super.setxC(x);
        super.setyC(y);
        super.setType(objectType.CHEESETYPE);
        this.setObjectImage(cheeseIcon);
    }


}
