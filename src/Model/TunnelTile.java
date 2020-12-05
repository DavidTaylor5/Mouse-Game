package Model;

import javax.swing.*;
import java.awt.*;

public class TunnelTile extends MazeTile {

    public TunnelTile(int xPos, int yPos){
        super.setTile(tileType.TUNNELTILE);
        super.setAccess(tileAccess.MOUSEMOVE);
        super.setxTilePos(xPos);
        super.setyTilePos(yPos);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setPreferredSize(new Dimension(25, 25));

        if(holdObject){
            ImageIcon givenImage = this.getCurrentObj().getObjectImage();
            g.drawImage(givenImage.getImage(), 0, 0, null );
        } else {
            this.setBackground(Color.pink);
            this.setForeground(Color.pink);
        }
        //g.setColor(Color.GRAY);

    }


    @Override
    void setTileType() {

    }

}
