package Model;

import java.awt.*;

public class BorderTile extends MazeTile {
    MazeTile.tileType tile = MazeTile.tileType.BUSHTILE;
    MazeTile.tileAccess access = MazeTile.tileAccess.NOMOVE;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setPreferredSize(new Dimension(25, 25));
        this.setBackground(Color.BLACK);
        //g.setColor(Color.GRAY);

    }

    @Override
    void setTileType() {

    }
}
