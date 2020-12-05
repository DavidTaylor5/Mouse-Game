package Model;

import javax.swing.*;
import java.awt.*;

public class PathTile extends MazeTile { //maybe I'll leave the background always the same and paint on the mouse and cheese?

    public PathTile(int xPos, int yPos){
        super.setAccess(tileAccess.ALLMOVE);
        super.setTile(tileType.PATHTILE);
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
            this.setBackground(Color.GRAY);
            this.setForeground(Color.GRAY);
        }

        //g.setColor(Color.GRAY);

    }

    @Override
    void setTileType() {  //possible and probably don't need this function

    }

}
