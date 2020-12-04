package Model;

import javax.swing.*;

public abstract class MazeTile extends JPanel {

    public enum tileType {BUSHTILE, TUNNELTILE, PATHTILE, BORDERTILE}

    public enum tileAccess {ALLMOVE, MOUSEMOVE, NOMOVE}

    abstract void setTileType();



}
