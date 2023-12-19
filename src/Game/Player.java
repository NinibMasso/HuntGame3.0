package Game;

import javax.swing.*;

public class Player {

    private String name;
    private int playerId = (int) (Math.random() * 90000) + 10000;

    private int wins;
    private int losses;

    public Player(){
        this.name = JOptionPane.showInputDialog(null,"Enter your name");
        this.playerId = getPlayerId();
    }
    public Player(String name, int playerId, int wins, int losses){
        this.name = name;
        this.playerId = playerId;
        this.wins = wins;
        this.losses = losses;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public String getName() {
        return name;
    }

    public int getPlayerId(){
        return playerId;
    }
}
