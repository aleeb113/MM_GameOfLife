package mm.game.of.life;

import javax.swing.*;
import java.awt.*;

class GameOfLifeRunner {

    public static void main(String[] args) {

        EventQueue.invokeLater(()->{
            JFrame frame = new GameOfLifeFrame();
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });

    }

}
