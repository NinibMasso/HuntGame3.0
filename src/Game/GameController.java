    package Game;

    import GUI.GameView;

    import javax.swing.*;
    import java.awt.event.KeyAdapter;
    import java.awt.event.KeyEvent;
    import java.io.IOException;
    import java.util.InputMismatchException;

    public class GameController {
        private final GameModel model;
        private final GameView view;

        public GameController(GameView view) {
            this.model = new GameModel();
            this.view = view;
            addActionListeners();
            view.updateView(this.model.getGameBoard(), this);
        }
        public GameModel getModel() {
            return model;
        }

        private void addActionListeners() {
            view.addKeyListener(new KeyController());
        }

        public JLabel getMessage(){
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
                            if (model.checkWin()){
                                model.getMessage().winner();
                                view.updateView(model.getGameBoard(), GameController.this);
                                int option = JOptionPane.showConfirmDialog(null,
                                        "Congratulations, you won!\nDo you want to play again?",
                                        "GAME OVER",
                                        JOptionPane.YES_NO_OPTION);
                                if (option == JOptionPane.YES_OPTION) {
                                    model.initializeGame();
                                }else
                                    view.showMainMenu();
                            } else {
                                model.moveTarget();
                                model.getMessage().howTo();
                                view.updateView(model.getGameBoard(), GameController.this);
                            } if (model.checkLose()) {
                                model.getMessage().loser();
                                view.updateView(model.getGameBoard(), GameController.this);
                                int option = JOptionPane.showConfirmDialog(null, "Sorry, you lost!\nDo you want to play again?",
                                        "GAME OVER",
                                        JOptionPane.YES_NO_OPTION);
                                if (option == JOptionPane.YES_OPTION){
                                    model.initializeGame();
                                }else
                                    view.showMainMenu();
                            }
                            view.updateView(model.getGameBoard(), GameController.this);
                        } catch (InputMismatchException | IOException ex) {
                            model.getMessage().moveOutsideBoard();
                            view.updateView(model.getGameBoard(), GameController.this);
                        }
                        return;
                    default: model.getMessage().tryAgain();

                }
            }
        }
    }