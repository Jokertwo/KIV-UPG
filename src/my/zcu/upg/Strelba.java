package my.zcu.upg;

import java.awt.image.BufferedImage;
import java.io.IOException;


public class Strelba {
    private ZadaniHodnot hod;
    int meritko = 10;


    public Strelba(String jmeno) throws IOException {
        hod = new ZadaniHodnot(jmeno, meritko);
        // vytvori bitmapu terenu a ulozi ji do pameti
        hod.vytvorTer();
    }


    /**
     * kontroluje zasah cile
     * 
     * @return vraci true pokud byl cil zasazen
     */
    public boolean zasah() {
        if (hod.zasah()) {
            return true;
        }
        return false;
    }


    public BufferedImage bila() {
        return hod.bila();
    }


    /**
     * ziska ze tridy teren nacteny obrazek terenu
     * 
     */
    public BufferedImage strela() {
        return hod.strela();
    }


    /**
     * ziska vzdalenost mezi dopadem strely a cilem
     * 
     * @return vraci celouciselny vysledek
     */
    public int vzdaKcili() {
        return hod.vzdaKcili() * meritko;
    }


    /**
     * ziska buffredImage sipky vetru ze tridy KresleniPlochy
     * 
     * @return
     */
    public BufferedImage sipkaVetru() {
        return hod.getSipka();
    }


    /**
     * //preda tride KresleniPolochy teren nacteny v pameti public void nakresli() throws IOException{ hod.nakresli(); }
     */
    public int rychVetru() {
        return hod.infoVitr();
    }


    public int vzdaHrac() {
        return hod.VzHracu() * meritko;
    }


    public int vyskaCer() {
        return hod.vysCerv();
    }


    public int vyskaModr() {
        return hod.vysModr();
    }


    public BufferedImage teren() {
        return hod.getTeren();
    }


    /**
     * nastavuje rychlost strely
     * 
     * @param rychlost
     */
    public void setRychlo(double rychlost) {
        hod.setRychlost(rychlost);
    }


    /**
     * nastavuje elevaci strely
     * 
     * @param elevace
     */
    public void setElevace(double elevace) {
        hod.setElevace(elevace);
    }


    /**
     * nastavavuje azimut strely
     * 
     * @param azimut
     */
    public void setAzimut(double azimut) {
        hod.setUhel(azimut);
    }


    public void spoctiDrahu() throws IOException {
        // spocita drahu letu
        hod.spoctiDrahu();
        // najde v poli souradnic nejmensi a nejvetsi prvek
        hod.maxMin();
        // preda vsechnz tyto informace tride KresleniPlochy
        hod.setTrajektDoKresleni();

    }


    /**
     * vymaze vsechny ArrayListy
     */
    public void smaz() {
        hod.smaz();
        hod.smazDostrel();
    }

}
