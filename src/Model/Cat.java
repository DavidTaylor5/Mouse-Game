package Model;

import javax.swing.*;

public class Cat extends MazeObject{

    public ImageIcon catIcon1 = new ImageIcon("src/Model/cartoon-unknown-cat.png");
    public ImageIcon catIcon2 = new ImageIcon("src/Model/cat2-25.png");
    public ImageIcon catIcon3 = new ImageIcon("src/Model/cat3-25.png");
    public ImageIcon catIcon4 = new ImageIcon("src/Model/cat4-25.png");
    public ImageIcon catIcon5 = new ImageIcon("src/Model/cat5-25.png");

    public int amountMoves;
    public int typeCat;

    public Cat(int x, int y, int typeCat){
        super.setxC(x);
        super.setyC(y);
        this.setTypeCat(typeCat);
        determineIcon();
    }

    public void determineIcon(){
        if (this.typeCat == 1){
            super.setObjectImage(catIcon1);
        } else if (this.typeCat == 2){
            super.setObjectImage(catIcon2);
        } else if (this.typeCat == 3){
            super.setObjectImage(catIcon3);
        } else if (this.typeCat == 4){
            super.setObjectImage(catIcon4);
        } else if (this.typeCat == 5){
            super.setObjectImage(catIcon5);
        } else {
            System.out.println("Issue choosing cat icon.");
        }
    }

    public void moveStript(){

    }

    public int getTypeCat() {
        return typeCat;
    }

    public void setTypeCat(int typeCat) {
        this.typeCat = typeCat;
    }
}
