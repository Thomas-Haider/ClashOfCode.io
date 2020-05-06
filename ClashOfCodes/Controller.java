package webClasses;

public class Controller {
    private static final Client client = new Client("http://localhost:5000/api");

    public static Byte[][] getGameField(){
        return client.getGameField();
    }
    public static boolean isStarted(){
        return client.isStarted;
    }

    public static void setStarted(boolean isStarted){
        client.isStarted = isStarted;
    }



    public static String getWinner(){
        return client.winner;
    }

    public static void startGame(){
        client.startGame();
    }

    public static void  makeMove(byte column){
        client.postColumn(column);
    }


}
