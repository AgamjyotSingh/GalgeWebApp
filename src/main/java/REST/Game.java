/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package REST;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import galgeleg.GalgeI;
import game.GameI;
import java.io.IOException;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.HashMap;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
/**
 *
 * @author Khurram Saeed Malik
 */
@Path("game")
public class Game {
    String REMOTEURL = "rmi://130.225.170.246/gameCalls";
    GameI gameCalls;
    GalgeI galgeI;
    static String welcomeMessage = "";
    static String userName;
    
    
    public Game() throws RemoteException, NotBoundException, NotBoundException, MalformedURLException {
        userName(userName);
    }
    
    
    public void userName(String userName) throws RemoteException, NotBoundException, MalformedURLException {
        this.gameCalls = (GameI) Naming.lookup(REMOTEURL);
        if(this.gameCalls.findGame(userName) != null) {
            welcomeMessage = "Welcome back: " + userName;
            this.galgeI = gameCalls.findGame(userName);
        } else {
            this.gameCalls.registerPlayer(userName);
            welcomeMessage = "New game started, with user: " + userName;
            this.galgeI = gameCalls.findGame(userName);
            
        }
    }

    @GET
    public String play() throws IOException, NotBoundException {
        
        //Initialize Mustache renderer
        MustacheFactory mf = new DefaultMustacheFactory();
        Mustache m = mf.compile("game.mustache");
        //Set some data
        HashMap<String, Object> mustacheData = new HashMap<String, Object>();
        //render template with data
        mustacheData.put("visibleWord", galgeI.getVisibleWords());
        mustacheData.put("usedLetters", galgeI.getUserWords());
        mustacheData.put("wrongLetters", galgeI.getTotalWrongGuess());
        mustacheData.put("hangmanstate", galgeI.getTotalWrongGuess());
        mustacheData.put("welcomeMessage", welcomeMessage);
        StringWriter writer = new StringWriter();
        m.execute(writer, mustacheData).flush();
        return writer.toString();
    }
    
    @POST
    public String guessWord(@FormParam("guess") String guess) throws IOException, NotBoundException, RemoteException, InterruptedException {
        if(guess!=null) {
             gameStatus(galgeI, guess);
              if (galgeI.isGameWon()) { 
               welcomeMessage = "You guessed the word: " + galgeI.getWord() + ", congragulations";
               return play();
              }
        }
        return play();
    }
    
    public String gameStatus(GalgeI galgeI, String guess) throws RemoteException, InterruptedException, IOException, NotBoundException, NotBoundException {
    while (true) {
            int temp = galgeI.getTotalWrongGuess();
            if (temp <= 6) {
                // todo
                // print hangman out here instead of pictures
                // hangman state depends on temp
                System.err.println("Hangmanstate: " + temp +"\n");
            }

            if (galgeI.isGameWon()) {
                return "Game won!";
                
            } else if (!galgeI.isGameLost()) {
                galgeI.guessWord(guess);
                return "guess";
            } else {
                // Ønsker du at starte nyt eller luk?
                galgeI.resetGame();
                
                return "Ordet var "+ galgeI.getWord();
            }
    }  }

}
