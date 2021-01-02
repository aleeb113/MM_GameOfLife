package mm.game.of.life;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GameOfLifePrintComponent extends JComponent {

    private GameOfLife game;

    public GameOfLifePrintComponent(GameOfLife game) {
       final ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();
       ses.scheduleAtFixedRate(() -> repaintMe(), 1000, 200, TimeUnit.MILLISECONDS);
        this.game = game;
    }


    @Override
    public Dimension getPreferredSize() {
        return GameOfLifeConfig.GAME_OF_LIFE_COMPONENT_SIZE;
    }

    @Override
    protected void paintComponent(Graphics g) {
        int cellSize = GameOfLifeConfig.CELL_SIZE;
        Graphics2D g2D = (Graphics2D) g;

        for (int x = 0; x < GameOfLifeConfig.BOARD_DIMENSION.width; x++)
            for (int y = 0; y < GameOfLifeConfig.BOARD_DIMENSION.height; y++) {
                if (game.cells[x][y].isAlive()) {
                    g2D.drawImage(GameOfLifeConfig.white, x * cellSize, y * cellSize, cellSize, cellSize, null);
                } else {
                    g2D.drawImage(GameOfLifeConfig.black, x * cellSize, y * cellSize, cellSize, cellSize, null);
                }
            }
       game.updateCellsStatus();

    }


    public void repaintMe() {
        this.repaint();
    }
}
