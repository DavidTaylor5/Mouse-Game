package Model;

import java.awt.*;

public class BorderTile extends MazeTile {

    public BorderTile(int xPos, int yPos){
        super.setAccess(tileAccess.NOMOVE);
        super.setTile(tileType.PATHTILE);
        super.setxTilePos(xPos);
        super.setyTilePos(yPos);
    }

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
