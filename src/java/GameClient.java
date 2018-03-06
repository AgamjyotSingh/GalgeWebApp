

import brugerautorisation.data.Bruger;
import brugerautorisation.data.Diverse;
import brugerautorisation.transport.rmi.Brugeradmin;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;
/**
 *
 * @author Khurram Saeed Malik
 */
public class GameClient {

    private static final String REMOTEURL = "rmi://localhost/rmicalls";
    private static GalgeI gameCalls;

    public static void main(String[] args) throws NotBoundException, MalformedURLException, RemoteException {
        gameCalls = (GalgeI) Naming.lookup(REMOTEURL);
        Scanner sc = new Scanner(System.in);

        System.out.println("Indtast brugernavn: \n");
        String userName = sc.nextLine();
        System.out.println("Indtast adgangskoden: \n");
        String password = sc.nextLine();
        Brugeradmin ba = (Brugeradmin) Naming.lookup("rmi://javabog.dk/brugeradmin");

        //ba.sendGlemtAdgangskodeEmail("s123456", "Dette er en test, husk at skifte kode");
        //ba.ændrAdgangskode(studienr, "kodeoscieq", koden);
        Bruger b = ba.hentBruger(userName, password);
        System.out.println("Fik bruger = " + b);
        System.out.println("Data: " + Diverse.toString(b));
        
        System.out.println("**********************************************");
        System.out.println("**                                          **");
        System.out.println("**              Spil Startede               **");
        System.out.println("**                                          **");
        System.out.println("**********************************************\n");

        while (true) {
            gameStatus(gameCalls, sc);
        }

    }
    
    public static void gameStatus(GalgeI gameCalls, Scanner sc) throws RemoteException {
        System.out.println("Gæt følgende ord: " + gameCalls.getVisibleWords());
            System.out.println("Du har nu brugt: " + gameCalls.getUserWords());
            System.out.println("Forkert gæt: " + gameCalls.getTotalWrongGuess());

            int temp = gameCalls.getTotalWrongGuess();
            if (temp <= 6) {
                // todo
                // print hangman out here instead of pictures
                // hangman state depends on temp
                System.err.println("Hangmanstate: " + temp +"\n");
            }

            if (gameCalls.isGameWon()) {
                System.out.println("**********************************************");
                System.out.println("**                                          **");
                System.out.println("**              Spil Afsluttede             **");
                System.out.println("**           Du har vundet spillet          **");
                System.out.println("**                                          **");
                System.out.println("**********************************************\n");
                gameCalls.resetGame();
                return;
            } else if (!gameCalls.isGameLost()) {
                System.out.println("Skriv dit gæt: \n");
                String guess = sc.nextLine();
                gameCalls.guessWord(guess);
            } else {
                gameCalls.resetGame();
                System.out.println("**********************************************");
                System.out.println("**                                          **");
                System.out.println("**              Spil Afsluttede             **");
                System.out.println("**            Du har tabt spillet           **");
                System.out.println("**                                          **");
                System.out.println("**********************************************\n");
                return;
            }
    }

}
