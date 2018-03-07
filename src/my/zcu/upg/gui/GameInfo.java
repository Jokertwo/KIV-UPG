package my.zcu.upg.gui;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import my.zcu.upg.StrelbaBalistic;
import net.miginfocom.swing.MigLayout;


public class GameInfo extends JPanel {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private StrelbaBalistic strelba;
    private static final String HRAC = "Nadmorska vyska modreho \n hrace je: ";
    private static final String CIL = "Nadmorska vyska cervenoho terce je: ";
    private static final String VITR = "Rychlost vetru je: ";
    private static final String SMER_VETRU = "Smer vetru je znazornen sipkou.";
    private static final String POPIS = "<html> Strela je znazornena barevnou skalou,<br> ktera jde od<font color = red>"
            + " cervene</font> po <font color = yellow>zlutou.</font> Cim je<br> draha"
            + " strely zlutejsi tim je strela vyse.</html>";


    public GameInfo(StrelbaBalistic strelba) {
        this.strelba = strelba;
        setLayout(new MigLayout());
        init();
    }


    private void init() {
        add(new JLabel(HRAC + strelba.vyskaModr()), "wrap");
        add(new JLabel(CIL + strelba.vyskaModr()), "wrap");
        add(new JLabel(VITR + strelba.rychVetru()),"wrap");
        add(new JLabel(SMER_VETRU),"wrap");
        add(new JLabel(new ImageIcon(strelba.sipkaVetru())),"wrap");
        add(new JLabel(POPIS));
    }

}
