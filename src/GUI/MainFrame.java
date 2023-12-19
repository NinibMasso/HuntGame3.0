package GUI;

import javax.swing.*;
import java.awt.*;

public class MainFrame {

    private final JFrame frame;

    public MainFrame() {
        frame = new JFrame("HuntGame");
        frame.setSize(600, 615);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLayout(new BorderLayout());
        frame.setFocusable(true);
        showMainMenu();
    }

    private void clear() {
        frame.getContentPane().removeAll();
        frame.repaint();
        frame.revalidate();
    }

    public void showMainMenu() {
        SwingUtilities.invokeLater(() -> {
            clear();
            MainMenuView mainMenuView = new MainMenuView(this);
            frame.setSize(615, 442);
            frame.setLocationRelativeTo(null);
            frame.getContentPane().add(mainMenuView);
            frame.revalidate();
            frame.repaint();
        });
    }

    public void showGameView() {
        SwingUtilities.invokeLater(() -> {
            clear();
            GameView gameView = new GameView(this);
            frame.setSize(615, 685);
            frame.setLocationRelativeTo(null);
            frame.getContentPane().add(gameView);
            frame.revalidate();
            frame.repaint();
            gameView.requestFocus();
        });
    }

    public JButton buttonDesigner(JButton button) {
        button.setPreferredSize(new Dimension(200, 50));
        button.setBackground(Color.black);
        button.setForeground(Color.white);
        button.setFont(new Font("Chiller", Font.PLAIN, 42));
        return button;
    }
}