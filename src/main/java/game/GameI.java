package game;
import galgeleg.GalgeI;

import java.util.ArrayList;

public interface GameI extends java.rmi.Remote {
  GalgeI findGame(String name) throws java.rmi.RemoteException;
  ArrayList<String> getAllPlayers() throws java.rmi.RemoteException;
  void registerPlayer(String name) throws java.rmi.RemoteException;
}
