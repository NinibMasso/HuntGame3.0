package Game;

import javax.swing.*;

public class GameMessage {
    private final JLabel currentMessage;
    public GameMessage(JLabel label){
        this.currentMessage = label;
    }
    public JLabel getCurrentMessage() {
        return currentMessage;
    }
    private void printIt(String message) {
        currentMessage.setText(message);
        currentMessage.revalidate();
        currentMessage.repaint();
    }
    public void welcome() {
        String welcome = "Welcome to HuntGame! You are the hunter. Catch the target! Don't let the target catch you!";
        printIt(welcome);

    }
    public void howTo() {
        String howTo = "Press A,S,D or W to move left(A),right(D),up(W) or down(S).";
        printIt(howTo);
    }

    public void tryAgain() {
        String tryAgain = "Try again! Use A,S,D or W.";
        printIt(tryAgain);
    }

    public void moveOutsideBoard() {
        String moveOutside = "You are trying to move outside the board or didn't press A,S,D or W. Try again!";
        printIt(moveOutside);
    }

    public void winner() {
        String winner = "You caught the target! Yay!";
        printIt(winner);
    }

    public void loser() {
        String loser = "The target caught YOU! Oh no!";
        printIt(loser);
    }
}
