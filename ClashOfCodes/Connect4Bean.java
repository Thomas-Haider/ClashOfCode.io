package webClasses;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@ManagedBean(name = "Connect4Bean")
@RequestScoped
public class Connect4Bean {

    private Byte[][] gameField;

    private String winnerText = "";


    @PostConstruct
    public void init() {
        if(Controller.isStarted() == false){
            Controller.startGame();
            Controller.setStarted(true);
        }
        gameField = Controller.getGameField();
    }

    public Byte[][] getGameField() {
        return gameField;
    }

    public void makeMove(int i) {
        Controller.makeMove((byte) i);
        gameField = Controller.getGameField();
        if (Controller.getWinner().equals("1")) {
            this.winnerText = "Player 1 won!";
        } else if (Controller.getWinner().equals("2")) {
            this.winnerText = "Player 2 won!";
        }
    }

    public void startGame() {
        Controller.startGame();
        gameField = Controller.getGameField();
        winnerText = "";
    }

    public List<List<Byte>> twoDArrayToList() {
        List<List<Byte>> list = Arrays.stream(getGameField())
                .map(Arrays::asList)
                .collect(Collectors.toList());

        return list;
    }

    public String getWinnerText() {
        return winnerText;
    }

    public String startAndRedirect() {
        startGame();
        return "/connect4.xhtml";
    }
    public String backToHomeAjax() {
        return "/index.xhtml";
    }
}
