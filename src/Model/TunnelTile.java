package Model;

import java.awt.*;

public class TunnelTile extends MazeTile {

    tileType tile = tileType.TUNNELTILE;
    tileAccess access = tileAccess.MOUSEMOVE;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setPreferredSize(new Dimension(25, 25));
        this.setBackground(Color.pink);
        //g.setColor(Color.GRAY);

    }


    @Override
    void setTileType() {

    }
}
