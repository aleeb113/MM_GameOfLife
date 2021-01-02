package mm.game.of.life;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class GameOfLifeCreator implements ActionListener {

    private final JButton playButton;
    private final JButton resetButton;
    private final JButton saveButton;
    private GameOfLifeFrame frame;
    private Cell[][] cells;
    private Rectangle2D.Double[][] recs;

    public GameOfLifeCreator(GameOfLifeFrame frame) {
        this.frame = frame;
        this.cells = new Cell[GameOfLifeConfig.BOARD_DIMENSION.width][GameOfLifeConfig.BOARD_DIMENSION.height];
        this.recs = new Rectangle2D.Double[GameOfLifeConfig.BOARD_DIMENSION.width][GameOfLifeConfig.BOARD_DIMENSION.height];
        resetCells();
        playButton = new JButton("Play");
        resetButton = new JButton("Reset");
        saveButton = new JButton("Save");
    }

    private void resetCells() {
        for (int x = 0; x < GameOfLifeConfig.BOARD_DIMENSION.width; x++)
            for (int y = 0; y < GameOfLifeConfig.BOARD_DIMENSION.height; y++)
                cells[x][y] = new Cell();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        frame.getInfoPanel().add(playButton);
        frame.getInfoPanel().add(resetButton);
        frame.getInfoPanel().add(saveButton);

        playButton.addActionListener(event -> {
            frame.getMainPanel().removeAll();
            frame.getMainPanel().add(new GameOfLifePrintComponent(new GameOfLife(cells)));
            frame.getInfoPanel().remove(playButton);
            frame.getInfoPanel().remove(resetButton);
            frame.getInfoPanel().remove(saveButton);
            frame.pack();
            frame.repaint();
        });

        resetButton.addActionListener(event -> {
            resetCells();
            frame.pack();
            frame.repaint();

        });

        saveButton.addActionListener(event -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(new File("."));
            if ((chooser.showSaveDialog(frame.getParent())) == JFileChooser.APPROVE_OPTION) {
                String fileName = chooser.getSelectedFile().getName();
                try {
                    ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
                    out.writeObject(new GameOfLife(cells));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        JComponent creator = new JComponent() {

            @Override
            public Dimension getPreferredSize() {
                return GameOfLifeConfig.GAME_OF_LIFE_COMPONENT_SIZE;
            }

            @Override
            protected void paintComponent(Graphics g) {

                Graphics2D g2D = (Graphics2D) g;
                int c = GameOfLifeConfig.CELL_SIZE;
                for (int x = 0; x < GameOfLifeConfig.BOARD_DIMENSION.width; x++)
                    for (int y = 0; y < GameOfLifeConfig.BOARD_DIMENSION.height; y++) {
                        g2D.setColor(Color.GRAY);
                        Rectangle2D.Double rec = new Rectangle2D.Double(x * c, y * c, c, c);
                        recs[x][y] = rec;
                        if (!cells[x][y].isAlive()) g2D.fill(rec);
                        g2D.setColor(Color.DARK_GRAY);
                        g2D.draw(rec);
                    }
            }

        };

        frame.getMainPanel().removeAll();
        frame.getMainPanel().add(creator);
        frame.pack();
        frame.repaint();

        creator.addMouseListener(new GameOfLifeCreatorMouseListener());
    }

    private class GameOfLifeCreatorMouseListener extends MouseAdapter {
        public GameOfLifeCreatorMouseListener() {
        }

        @Override
        public void mousePressed(MouseEvent mouseEvent) {
            Point p = mouseEvent.getPoint();
            for (int x = 0; x < GameOfLifeConfig.BOARD_DIMENSION.width; x++)
                for (int y = 0; y < GameOfLifeConfig.BOARD_DIMENSION.height; y++) {
                    if (recs[x][y].contains(p)) {
                        if (cells[x][y].isAlive()) cells[x][y].setAlive(false);
                        else cells[x][y].setAlive(true);
                    }
                }
            frame.repaint();
        }
    }
}
