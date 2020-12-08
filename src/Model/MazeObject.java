package Model;

import javax.swing.*;

public abstract class MazeObject {

    public enum objectType {MOUSETYPE, CATTYPE, CHEESETYPE }


    public ImageIcon objectImage;
    public int xC;
    public int yC;
    public objectType type;

    public ImageIcon getObjectImage() {
        return objectImage;
    }

    public void setObjectImage(ImageIcon image) {
        this.objectImage = image;
    }

    public int getxC() {
        return xC;
    }

    public void setxC(int xC) {
        this.xC = xC;
    }

    public int getyC() {
        return yC;
    }

    public void setyC(int yC) {
        this.yC = yC;
    }

    public objectType getType() {
        return type;
    }

    public void setType(objectType type) {
        this.type = type;
    }

    //public abstract void move();



}
