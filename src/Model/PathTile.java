package Model;

import java.awt.*;

public class PathTile extends MazeTile { //maybe I'll leave the background always the same and paint on the mouse and cheese?

    tileType tile = tileType.PATHTILE;
    tileAccess access = tileAccess.ALLMOVE;

//    public PathTile(){
//        setBackground(Color.RED);
//    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setPreferredSize(new Dimension(25, 25));
        this.setBackground(Color.GRAY);
        //g.setColor(Color.GRAY);

    }

    @Override
    void setTileType() {

    }
}
