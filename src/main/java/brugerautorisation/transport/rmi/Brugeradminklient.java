package brugerautorisation.transport.rmi;

import brugerautorisation.data.Diverse;
import brugerautorisation.data.Bruger;
import java.rmi.Naming;

public class Brugeradminklient {
    private static final String studienr = "s165162";
    private static final String koden = "ksm123";

    public static void main(String[] arg) throws Exception {
        //Brugeradmin ba =(Brugeradmin) Naming.lookup("rmi://localhost/brugeradmin");
        Brugeradmin ba = (Brugeradmin) Naming.lookup("rmi://javabog.dk/brugeradmin");

        //ba.sendGlemtAdgangskodeEmail("s123456", "Dette er en test, husk at skifte kode");
        //ba.ændrAdgangskode(studienr, "kodeoscieq", koden);
        Bruger b = ba.hentBruger(studienr, koden);
        System.out.println("Fik bruger = " + b);
        System.out.println("Data: " + Diverse.toString(b));
        // ba.sendEmail("jacno", "xxx", "Hurra det virker!", "Jeg er så glad");

        //Object ekstraFelt = ba.getEkstraFelt("s123456", "kode1xyz", "hobby");
        //System.out.println("Brugerens hobby er: " + ekstraFelt);

        //ba.setEkstraFelt(studienr, koden, "hobby", "Programmering"); // Skriv noget andet her

        String hobby = (String) ba.getEkstraFelt(studienr, koden, "hobby");
        System.out.println("Brugerens hobby er: " + hobby);
        
    }
}
