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

        if(this.cat1 != null){
            ImageIcon givenImage = this.getCat1().getObjectImage();
            g.drawImage(givenImage.getImage(), 0, 0, null );
        } else if(this.cat2 != null){
            ImageIcon givenImage = this.getCat2().getObjectImage();
            g.drawImage(givenImage.getImage(), 0, 0, null );
        } else if(this.mouse != null){
            ImageIcon givenImage = this.getMouse().getObjectImage();
            g.drawImage(givenImage.getImage(), 0, 0, null );
        } else if(this.cheese != null){
            ImageIcon givenImage = this.getCheese().getObjectImage();
            g.drawImage(givenImage.getImage(), 0, 0, null );
        } else {
            this.setBackground(Color.GRAY);
            this.setForeground(Color.GRAY);
        }

    }

//    public void checkObjects(){
//        if(this.cheese == null && this.cat1 == null && this.mouse == null && this.cat2 == null){
//            this.holdObject = true;
//        } else {
//            this.holdObject = false;
//        }
//
//    } should be in a parent class


    @Override
    void setTileType() {  //possible and probably don't need this function

    }

}
