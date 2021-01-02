package mm.game.of.life;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class GameOfLifeFrame extends JFrame {

   private JPanel mainPanel;
   private JPanel infoPanel;

    public GameOfLifeFrame() throws HeadlessException {

        GameOfLife game = new GameOfLife();
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(GameOfLifeConfig.starter));
            game = (GameOfLife) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        mainPanel = new JPanel();
        mainPanel.add(new GameOfLifePrintComponent(game));

        infoPanel = new JPanel();
        JButton openButton = new JButton("Open");
        JButton createButton = new JButton("Create");
        createButton.addActionListener(new GameOfLifeCreator(this));

        openButton.addActionListener(actionEvent -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(new File("."));
            if (chooser.showOpenDialog(getParent()) == JFileChooser.APPROVE_OPTION) {
                try {
                    ObjectInputStream in = new ObjectInputStream(new FileInputStream(chooser.getSelectedFile().getName()));
                    mainPanel.removeAll();
                    mainPanel.add(new GameOfLifePrintComponent(((GameOfLife) in.readObject())));
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                infoPanel.removeAll();
                infoPanel.add(createButton);
                infoPanel.add(openButton);
                pack();
                repaint();
            }
        });

        infoPanel.add(createButton);
        infoPanel.add(openButton);
        add(mainPanel, BorderLayout.NORTH);
        add(infoPanel, BorderLayout.SOUTH);
        pack();
        repaint();
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void setMainPanel(JPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public JPanel getInfoPanel() {
        return infoPanel;
    }

    public void setInfoPanel(JPanel infoPanel) {
        this.infoPanel = infoPanel;
    }
}
