package my.zcu.upg;

import javax.swing.JFrame;


/**
 * @author Petr Lastovka
 * @author A15B0055K
 * 
 *         Semestralni prace z UPG druhe odevzdani prototyp
 */
public class Spust extends JFrame {
    private static String jmeno = null;

    private static final long serialVersionUID = 1L;


    public static void main(String[] args) {
        HlavniOkno menu = new HlavniOkno();

        // pokud argument neni spusti program s prilozenym souborem
        if (args.length == 0) {
            jmeno = "ter/terrain512x512_300_600.ter";
            menu.setJmeno(jmeno);
        }

        // nastavi argument jako jmeno souboru ze ktereho se ma cist
        else {
            String[] argument = args;
            jmeno = argument[0];
            menu.setJmeno(jmeno);
        }

    }

}
