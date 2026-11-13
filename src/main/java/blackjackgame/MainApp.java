package blackjackgame;
import blackjack.ui.UI;
import  blackjack.model.Deck;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        UI ui = new UI(new Scanner(System.in), System.out);
        try {
            new BlackjackGame(new Deck(), ui).playRound();
        } catch (NoSuchElementException inputClosed) {
            ui.showInputClosed();
        }
    }
}