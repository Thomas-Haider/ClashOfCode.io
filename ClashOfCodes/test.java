package webClasses;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class test {
    private static Byte[][] gameField;
    public static void main(String[] args) {
        Controller.startGame();
        gameField = Controller.getGameField();
            Controller.makeMove(new Integer(1).byteValue());
            Controller.makeMove(new Integer(2).byteValue());
            Controller.makeMove(new Integer(1).byteValue());
            Controller.makeMove(new Integer(2).byteValue());
            Controller.makeMove(new Integer(1).byteValue());
            Controller.makeMove(new Integer(2).byteValue());
            Controller.makeMove(new Integer(1).byteValue());
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        gameField = Controller.getGameField();
        List<List<Byte>> listen = Arrays.stream(gameField)
                .map(Arrays::asList)
                .collect(Collectors.toList());
        for(List<Byte> list1 : listen){
            for(Byte list2 : list1){
                System.out.print(list2);
            }
            System.out.println();
        }
        System.out.println(Controller.getWinner() + " sdasd");
    }
}
