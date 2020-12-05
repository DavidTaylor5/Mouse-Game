package Model;

import java.awt.*;

public class BushTile extends MazeTile {

    public BushTile(int xPos, int yPos){
        super.setTile(tileType.BUSHTILE);
        super.setAccess(tileAccess.NOMOVE);
        super.setxTilePos(xPos);
        super.setyTilePos(yPos);
    }

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
