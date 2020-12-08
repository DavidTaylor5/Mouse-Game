package Model;

import javax.swing.*;

public class Cat extends MazeObject{

    public ImageIcon catIcon1 = new ImageIcon("src/Model/cartoon-unknown-cat.png");
    public ImageIcon catIcon2 = new ImageIcon("src/Model/cat2-25.png");
    public ImageIcon catIcon3 = new ImageIcon("src/Model/cat3-25.png");
    public ImageIcon catIcon4 = new ImageIcon("src/Model/cat4-25.png");
    public ImageIcon catIcon5 = new ImageIcon("src/Model/cat5-25.png");

    public int amountMoves;
    public int numbCat;
    public String direction;
    public String oppositeD;

    //public string current Direction // need to implement

    public Cat(int x, int y, int typeCat){
        super.setxC(x);
        super.setyC(y);
        this.setTypeCat(typeCat);
        super.setType(objectType.CATTYPE);
        determineIcon();
    }

    public void determineIcon(){
        if (this.numbCat == 1){
            super.setObjectImage(catIcon1);
            this.setDirection("left");
            this.setOppositeD("Right");
        } else if (this.numbCat == 2){
            super.setObjectImage(catIcon2);
            //this.setDirection("Down");
            //tester
            this.setDirection("Left");
        } else if (this.numbCat == 3){
            super.setObjectImage(catIcon3);
            this.setDirection("Up");
        } else if (this.numbCat == 4){
            super.setObjectImage(catIcon4);
            this.setDirection("Left");
        } else if (this.numbCat == 5){
            super.setObjectImage(catIcon5);
            this.setDirection("Up");
            this.setOppositeD("down");
        } else {
            System.out.println("Issue choosing cat icon.");
        }
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }


    public int getTypeCat() {
        return numbCat;
    }

    public void setTypeCat(int typeCat) {
        this.numbCat = typeCat;
    }

    public String getOppositeD() {
        return oppositeD;
    }

    public void setOppositeD(String oppositeD) {
        this.oppositeD = oppositeD;
    }
}
