package GUI;

import Game.GameController;

import javax.swing.*;
import java.awt.*;

public class GameView extends JPanel {
    private final ImageIcon backgroundImage = new ImageIcon("src/IconImages/map2.jpg");
    private  JPanel gamePanel;
    private  JPanel topPanel;
    private final JLabel backgroundLabel = new JLabel();
    private final JLabel messageLabel = new JLabel();
    private final JLabel winLabel = new JLabel("Wins: 0");
    private final JLabel lossLabel = new JLabel("Losses: 0");
    private  MainFrame mainFrame;
    private final GameController controller;


    public GameView(MainFrame mainFrame) {
        this.controller = new GameController(this);
        this.mainFrame = mainFrame;

        setSize(615, 685);
        setVisible(true);
        setLayout(new BorderLayout());
        this.setFocusable(true);

        gamePanel = new JPanel();
        backgroundLabel.setIcon(backgroundImage);
        backgroundLabel.setLayout(new GridLayout(10, 10));
        gamePanel.add(backgroundLabel);
        topPanel = new JPanel(new BorderLayout());

        JPanel scorePanel = new JPanel(new FlowLayout());
        scorePanel.add(winLabel);
        scorePanel.add(lossLabel);
        topPanel.add(scorePanel, BorderLayout.NORTH);
        topPanel.add(messageLabel, BorderLayout.CENTER);

        add(topPanel, BorderLayout.NORTH);
        add(gamePanel, BorderLayout.CENTER);
    }

    public GameView(){
        this.controller = new GameController();
    }

    public void updateView(String[][] gameBoard, GameController controller) {
        backgroundLabel.removeAll();
        backgroundLabel.setLayout(new GridLayout(10, 10));
        String onIndex;

        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard.length; j++) {
                onIndex = gameBoard[i][j];
                JButton button = new JButton();
                if (onIndex.equals(controller.getModel().getHunter().getCharMark())) {
                    button.setIcon(controller.getModel().getHunter().getIcon());
                } else if (onIndex.equals(controller.getModel().getTarget().getCharMark())) {
                    button.setIcon(controller.getModel().getTarget().getIcon());
                } else {
                    button.setVisible(false);
                }
                button.setOpaque(true);
                button.setBorderPainted(true);
                button.setFocusPainted(false);
                button.setContentAreaFilled(true);
                button.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
                backgroundLabel.add(button);
            }
        }
        winLabel.setText("Wins: " + controller.getModel().getWins());
        lossLabel.setText("Losses: " + controller.getModel().getLosses());
        messageLabel.setText(controller.getMessage().getText());
        this.revalidate();
        this.repaint();
    }

    public void showMainMenu() {
        mainFrame.showMainMenu();
    }

    public GameController getController() {
        return controller;
    }
}