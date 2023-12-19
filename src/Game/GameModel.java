package Game;

import Factory.GridComponent;
import Factory.GridComponentFactory;
import Factory.GridComponentTypes;

import java.io.IOException;

public class GameModel {
    private GameBoard gameBoard;
    private GridComponent hunter;
    private GridComponent target;
    private Player player;
    private int wins = 0;
    private int losses = 0;
    public GameModel(){
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
}