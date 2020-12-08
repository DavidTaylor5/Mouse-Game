package Model;

import javax.swing.*;

public abstract class MazeTile extends JPanel {

    public enum tileType {BUSHTILE, TUNNELTILE, PATHTILE, BORDERTILE}

    public enum tileAccess {ALLMOVE, MOUSEMOVE, NOMOVE}

    //fields of all Tiles
    public tileAccess access;
    public tileType tile;
    public MazeObject currentObj;
    boolean holdObject = false;

    //potentially a path could hold up to 4 objects I guess.

    public int xTilePos;
    public int yTilePos;

    @Override
    public String toString() {
        return "MazeTile{" +
                "access=" + access +
                ", tile=" + tile +
                ", currentObj=" + currentObj +
                ", holdObject=" + holdObject +
                ", xTilePos=" + xTilePos +
                ", yTilePos=" + yTilePos +
                '}';
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

    public MazeObject getCurrentObj() {
        return currentObj;
    }

    public void setCurrentObj(MazeObject currentObj) {
        this.currentObj = currentObj;
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
}
