package Game;

import Factory.GridComponent;
import Factory.GridComponentFactory;
import Factory.GridComponentTypes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class GameModel {
    private GameBoard gameBoard;
    private GridComponent hunter;
    private GridComponent target;
    private Player player;
    private GameController controller;

    private String highScoreText;
    private int wins = 0;
    private int losses = 0;
    public GameModel(){
        this.highScoreText = highScoreText();
    }
    public GameModel(GameController controller){
        this.controller = controller;
        this.highScoreText = highScoreText();
        this.player = new Player();
        initializeGame();
    }
    public void initializeGame() {
        GridComponentFactory gridComponentFactory = new GridComponentFactory();
        hunter = gridComponentFactory.createGridComponent(GridComponentTypes.HUNTER);
        target = gridComponentFactory.createGridComponent(GridComponentTypes.TARGET);
        gameBoard = new GameBoard(hunter.getCharMark(), target.getCharMark());
        gameBoard.setMarkerX(4, 0, hunter.getCharMark());
        gameBoard.setTargetIT(4, 9, target.getCharMark());
    }

    public Player getPlayer() {
        return player;
    }

    public GameMessage getMessage() {
        return gameBoard.getMessage();
    }

    public void moveHunter(String direction) throws IOException {
        gameBoard.moveMarker(direction);
    }

    public void moveTarget(){
        gameBoard.moveTarget();
    }

    public boolean checkWin(){
        if (gameBoard.locationOfMarkerX().equals(gameBoard.getTargetLocation())) {
            wins++;
            return true;
        }else{
            return false;
        }
    }
    public boolean checkLose(){
        if (gameBoard.locationOfTarget().equals(gameBoard.getMarkerLocation())){
            losses++;
            return true;
        }else{
            return false;
        }

    }

    public String[][] getGameBoard(){
        return gameBoard.getGameBoard();
    }

    public GridComponent getHunter() {
        return hunter;
    }

    public GridComponent getTarget() {
        return target;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public String highScoreText(){
        try (BufferedReader reader = new BufferedReader(new FileReader("src/TextFiles/LogFile"))) {
            StringBuilder highscoreTextBuilder = new StringBuilder();
            highscoreTextBuilder.append("Top 3 Players:\n");
            LinkedList<Player> playerStatsList = new LinkedList<>();


            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");
                if (tokens.length == 3) {
                    String name = tokens[0];
                    int playerId = Integer.parseInt(tokens[1].trim());
                    String[] splitTokens = tokens[2].split(" ");
                    int wins = Integer.parseInt(splitTokens[1]);
                    int losses = Integer.parseInt(splitTokens[3]);
                    playerStatsList.add(new Player(name, playerId, wins, losses));
                }
            }
            playerStatsList.sort((p1, p2) -> {
                int result = Integer.compare(p2.getWins() - p2.getLosses(), (p1.getWins() - p1.getLosses()));
                if (result == 0) {
                    result = Integer.compare(p2.getLosses(), p1.getLosses());
                }
                return result;
            });

            int count = 0;
            for (Player player : playerStatsList) {
                highscoreTextBuilder.append(player.getName())
                        .append(", ")
                        .append(player.getPlayerId())
                        .append(", ")
                        .append(": Wins: ").append(player.getWins())
                        .append(", Losses: ").append(player.getLosses())
                        .append("\n");

                count++;
                if (count >= 3) {
                    break;
                }

            }
            this.highScoreText = highscoreTextBuilder.toString();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return highScoreText;
    }
}