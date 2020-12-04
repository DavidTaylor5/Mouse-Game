package Model;

import java.awt.*;

public class BushTile extends MazeTile {

    tileType tile = tileType.BUSHTILE;
    tileAccess access = tileAccess.NOMOVE;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setPreferredSize(new Dimension(25, 25));
        this.setBackground(Color.GREEN);
        //g.setColor(Color.GRAY);

    }

    @Override
    void setTileType() {

    }
}
