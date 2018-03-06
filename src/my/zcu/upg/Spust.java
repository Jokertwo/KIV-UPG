package my.zcu.upg;

/**
 * @author Petr Lastovka
 * @author A15B0055K
 * 
 *         Semestralni prace z UPG druhe odevzdani prototyp
 */
public class Spust {
    private static String jmenoSouboru = null;


    public static void main(String[] args) {

        jmenoSouboru = args.length == 0 ? "ter/terrain512x512_300_600.ter" : args[0];
        new HlavniOkno(jmenoSouboru);

    }

}
