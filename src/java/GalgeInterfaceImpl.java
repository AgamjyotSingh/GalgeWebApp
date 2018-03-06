


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 *
 * @author Khurram Saeed Malik
 */
public class GalgeInterfaceImpl extends UnicastRemoteObject implements GalgeI {
    private Galgelogik logic;

    public GalgeInterfaceImpl() throws RemoteException {
        logic = new Galgelogik();
    }

    @Override
    public ArrayList getUserWords() throws RemoteException {
        return logic.getBrugteBogstaver();
    }

    @Override
    public String getVisibleWords() throws RemoteException {
        return logic.getSynligtOrd();
    }

    @Override
    public String getWord() throws RemoteException {
        return logic.getOrdet();
    }

    @Override
    public int getTotalWrongGuess() throws RemoteException {
        return logic.getAntalForkerteBogstaver();
    }

    @Override
    public boolean isLastGuessCorrect() throws RemoteException {
        return logic.erSidsteBogstavKorrekt();
    }

    @Override
    public boolean isGameWon() throws RemoteException {
        return logic.erSpilletVundet();
    }

    @Override
    public boolean isGameLost() throws RemoteException {
        return logic.erSpilletTabt();
    }

    @Override
    public boolean isGameOver() throws RemoteException {
        return logic.erSpilletSlut();
    }

    @Override
    public void guessWord(String guess) throws RemoteException {
        
        logic.gætBogstav(guess);
        logic.logStatus();
    }

    @Override
    public void resetGame() throws RemoteException {
        logic.nulstil();
    }
    
}
