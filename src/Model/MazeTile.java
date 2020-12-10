package Model;

import javax.swing.*;

public abstract class MazeTile extends JPanel {

    public enum tileType {BUSHTILE, TUNNELTILE, PATHTILE, BORDERTILE}

    public enum tileAccess {ALLMOVE, MOUSEMOVE, NOMOVE}

    //fields of all Tiles
    public tileAccess access;
    public tileType tile;
    public Cheese cheese;  //turn this to cheese.
    boolean holdObject = false;

    public Cat cat1;
    public Cat cat2;
    public Mouse mouse;

    //potentially a path could hold up to 4 objects I guess.

    public int xTilePos;
    public int yTilePos;

    @Override
    public String toString() {
        return "MazeTile{" +
                "access=" + access +
                ", tile=" + tile +
                ", cheese=" + cheese +
                ", holdObject=" + holdObject +
                ", cat1=" + cat1 +
                ", cat2=" + cat2 +
                ", mouse=" + mouse +
                ", xTilePos=" + xTilePos +
                ", yTilePos=" + yTilePos +
                '}';
    }

//    public void setObject(MazeObject object){
//        String objType = object.getType().toString();
//        if(objType.equalsIgnoreCase("MOUSETYPE")){
//            this.setMouse(object);
//        } else if(objType.equalsIgnoreCase("CATTYPE")){
//
//        } else if(objType.equalsIgnoreCase("CHEESETYPE")){
//
//        } else {
//            System.out.println("set object failure.");
//        }
//    } //how do I set all of the different types?

    public void checkObjects(){
        if(this.cheese == null && this.cat1 == null && this.mouse == null && this.cat2 == null){
            this.holdObject = true;
        } else {
            this.holdObject = false;
        }

    }

    public boolean cheeseInteraction(){
        if(this.cheese != null && this.mouse !=null){
            this.setCheese(null);
            return true;
        }
        return false;
    }

    public boolean catMouseInteraction(){
        if((this.cat1 != null || this.cat2 != null) && this.mouse !=null){
            this.setMouse(null); ///////////wroking on this
            return true;
        }
        return false;
    }

    abstract void setTileType();

    public tileAccess getAccess() {
        return access;
    }

    public void setAccess(tileAccess access) {
        this.access = access;
    }

    public tileType getTile() {
        return tile;
    }

    public void setTile(tileType tile) {
        this.tile = tile;
    }

    public Cheese getCheese() {
        return cheese;
    }

    public void setCheese(Cheese cheese) {
        this.cheese = cheese;
    }

    public boolean isHoldObject() {
        return holdObject;
    }

    public void setHoldObject(boolean holdObject) {
        this.holdObject = holdObject;
    }

    public int getxTilePos() {
        return xTilePos;
    }

    public void setxTilePos(int xTilePos) {
        this.xTilePos = xTilePos;
    }

    public int getyTilePos() {
        return yTilePos;
    }

    public void setyTilePos(int yTilePos) {
        this.yTilePos = yTilePos;
    }

    public Cat getCat1() {
        return cat1;
    }

    public void setCat1(Cat cat1) {
        this.cat1 = cat1;
    }

    public Cat getCat2() {
        return cat2;
    }

    public void setCat2(Cat cat2) {
        this.cat2 = cat2;
    }

    public Mouse getMouse() {
        return mouse;
    }

    public void setMouse(Mouse mouse) {
        this.mouse = mouse;
    }
}
