package Game;

import GUI.GameView;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;

public class GameController {
    private final GameModel model;
    private  GameView view;
    private LocalDateTime currentDateTime = LocalDateTime.now();
    private String formattedDateTime = currentDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);

    public GameController(GameView view) {
        this.view = view;
        this.model = new GameModel(this);
        addActionListeners();
        view.updateView(this.model.getGameBoard(), this);
        formattedDateTime = formattedDateTime.substring(0, formattedDateTime.lastIndexOf(':'));
    }

    public GameController(){
        this.model = new GameModel();
    }

    public GameModel getModel() {
        return model;
    }

    private void addActionListeners() {
        view.addKeyListener(new KeyController());
    }

    public JLabel getMessage() {
        return model.getMessage().getCurrentMessage();
    }

    private class KeyController extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            char keyChar = e.getKeyChar();

            switch (Character.toLowerCase(keyChar)) {
                case 'w':
                case 'a':
                case 's':
                case 'd':
                    try {
                        model.moveHunter(Character.toString(keyChar));
                        if (model.checkWin()) {
                            model.getMessage().winner();
                            view.updateView(model.getGameBoard(), GameController.this);
                            int option = JOptionPane.showConfirmDialog(null,
                                    "Congratulations, you won!\nDo you want to play again?",
                                    "GAME OVER",
                                    JOptionPane.YES_NO_OPTION);
                            if (option == JOptionPane.YES_OPTION) {
                                model.initializeGame();
                            } else {
                                try (FileWriter fileWriter = new FileWriter("src/TextFiles/LogFile", true);
                                     BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
                                    bufferedWriter.append(model.getPlayer().getName()).append(", ").
                                            append(String.valueOf(model.getPlayer().getPlayerId())).append(", ").
                                            append(String.valueOf(getModel().getWins())).append(" wins " +
                                                    getModel().getLosses() + " losses " + formattedDateTime + "\n");
                                }
                                view.showMainMenu();
                            }
                        } else {
                            model.moveTarget();
                            model.getMessage().howTo();
                            view.updateView(model.getGameBoard(), GameController.this);
                        }
                        if (model.checkLose()) {
                            model.getMessage().loser();
                            view.updateView(model.getGameBoard(), GameController.this);
                            int option = JOptionPane.showConfirmDialog(null, "Sorry, you lost!\n" +
                                            "Do you want to play again?",
                                    "GAME OVER",
                                    JOptionPane.YES_NO_OPTION);
                            if (option == JOptionPane.YES_OPTION) {
                                model.initializeGame();
                            } else {
                                try (FileWriter fileWriter = new FileWriter("src/TextFiles/LogFile", true);
                                     BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
                                    bufferedWriter.append(model.getPlayer().getName()).append(", ").
                                            append(String.valueOf(model.getPlayer().getPlayerId())).append(", ").
                                            append(String.valueOf(getModel().getWins())).append(" wins " +
                                                    getModel().getLosses() + " losses " + formattedDateTime + "\n");
                                }
                                view.showMainMenu();
                            }
                        }
                        view.updateView(model.getGameBoard(), GameController.this);
                    } catch (InputMismatchException | IOException ex) {
                        model.getMessage().moveOutsideBoard();
                        view.updateView(model.getGameBoard(), GameController.this);
                    }
                    return;
                default:
                    model.getMessage().tryAgain();

            }
        }
    }

    public String getHighscoreText() {
        return model.highScoreText();
    }
}