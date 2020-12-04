package View;

import java.awt.*;

public class GameStart {

    public static void main(String[] args) {
        EventQueue.invokeLater( () ->
        {
           MazeGui myMazeGui = new MazeGui(args);
        });
    }
}
