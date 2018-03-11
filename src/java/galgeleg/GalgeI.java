package galgeleg;

import java.util.ArrayList;

/**
 *
 * @author Khurram Saeed Malik
 */
public interface GalgeI extends java.rmi.Remote {
    //Methods that i want my server to implement and client to call
    ArrayList getUserWords() throws java.rmi.RemoteException;
    String getVisibleWords() throws java.rmi.RemoteException;
    String getWord() throws java.rmi.RemoteException;
    int getTotalWrongGuess() throws java.rmi.RemoteException;
    boolean isLastGuessCorrect() throws java.rmi.RemoteException;
    boolean isGameWon() throws java.rmi.RemoteException;
    boolean isGameLost() throws java.rmi.RemoteException;
    boolean isGameOver() throws java.rmi.RemoteException;
    void guessWord(String guess) throws java.rmi.RemoteException;
    void resetGame() throws java.rmi.RemoteException;
    void getWordsFromDR() throws java.rmi.RemoteException;
   
}