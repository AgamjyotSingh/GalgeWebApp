

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 *
 * @author Khurram Saeed Malik
 */
public class GameServer {
    private static final String REMOTEURL = "rmi://localhost/rmicalls";
    
    public static void main(String[] args) throws RemoteException, MalformedURLException {
        try {
            LocateRegistry.createRegistry(1099); 
            System.out.println("java RMI registry created.");
        } catch (RemoteException e) {
            //do nothing, error means registry already exists
            System.out.println("java RMI registry already exists.");
        }
        
        GalgeI calls = new GalgeInterfaceImpl();
        Naming.rebind(REMOTEURL, calls);
        System.out.println("RMI server started");
        
    }
    
}

