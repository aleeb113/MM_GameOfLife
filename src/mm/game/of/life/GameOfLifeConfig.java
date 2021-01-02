package mm.game.of.life;

import javax.swing.*;
import java.awt.*;

public class GameOfLifeConfig {

    static final Dimension BOARD_DIMENSION = new Dimension(80, 35);
    static final int CELL_SIZE = 15;
    static final Dimension GAME_OF_LIFE_COMPONENT_SIZE = new Dimension(BOARD_DIMENSION.width * CELL_SIZE + 10, BOARD_DIMENSION.height * CELL_SIZE + 10);

    public static Image black = new ImageIcon("src/mm/game/of/life/black.png").getImage();
    public static Image white = new ImageIcon("src/mm/game/of/life/white.jpeg").getImage();

    public static String starter = "gosperGliderGun.gol";

}
