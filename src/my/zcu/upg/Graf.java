package my.zcu.upg;

import java.awt.image.BufferedImage;
import java.io.IOException;
import org.jfree.chart.ChartPanel;


public class Graf {

    // nastaveni meritka mapy
    // kazdy pixel je 10 m
    private int meritko = 10;

    // nastaveni velikosti "vybuchu" pro celou hru
    private int velikostDopadu = 12;
    private ZadaniHodnot hod;


    public Graf(String jmeno) throws IOException {
        hod = new ZadaniHodnot(jmeno, meritko);
        // vytvori instanci tridy VytvareniGrafu
        hod.vytvareniGrafu();

    }


    public BufferedImage sipkaVetru() {
        return hod.getSipka();
    }


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


    /**
     * ziska vzdalenost mezi dopadem strely a cilem
     * 
     * @return vraci celouciselny vysledek
     */
    public int vzdaKcili() {
        return hod.vzdaKcili() * meritko;
    }


    public ChartPanel strelaAzeme() {
        ChartPanel drawingArea;
        return drawingArea = new ChartPanel(hod.vytvorgrafStrelaAzeme());
    }


    public ChartPanel dostrel() {
        ChartPanel drawingArea2;
        return drawingArea2 = new ChartPanel(hod.vytvorgrafDostrelu());
    }


    public void smaz() {
        hod.smaz();
        hod.smazDostrel();
    }

}

/**
 * frame.getContentPane().add(drawingArea, BorderLayout.WEST);; frame.getContentPane().add(drawingArea2,
 * BorderLayout.EAST); frame.pack();
 * 
 * frame.setTitle("JFreeCharts"); frame.setLocationRelativeTo(null);
 * frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); frame.setVisible(true); hod.smaz(); hod.smazDostrel();
 * 
 */
