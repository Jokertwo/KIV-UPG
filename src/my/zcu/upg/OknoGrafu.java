package my.zcu.upg;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.TextField;
import java.io.IOException;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import my.zcu.upg.gui.HlavniOkno;


public class OknoGrafu {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    TextField elevace, azimut, rychlost;
    JButton vystrel, reset;
    JLabel vzdKcili, azi, ele, rych, infoOcerHrac, infoOmodHrac,
            rychVetru, sipkaVetru;
    private String jmeno;
    private int sirkatextField = 5;


    public OknoGrafu(String jmeno) throws IOException {
        this.jmeno = jmeno;

        JFrame okno = new JFrame();
        Graf gra = new Graf(jmeno);

        JPanel panel = new JPanel();
        JPanel panelTop = new JPanel();
        JPanel panelLeft = new JPanel();
        JPanel panelCentr = new JPanel();

        FlowLayout botton = new FlowLayout();
        FlowLayout top = new FlowLayout();
        BoxLayout left = new BoxLayout(panelLeft, BoxLayout.Y_AXIS);

        panelTop.setLayout(top);
        panel.setLayout(botton);
        panelLeft.setLayout(left);

        // dolni panel

        // prida do panelu textField pro zadani azimutu strely
        azi = new JLabel("Zadej azimut strely");
        panel.add(azi);
        azimut = new TextField(sirkatextField);
        panel.add(azimut);

        // prida do panelu textField pro zadani elevace strely
        ele = new JLabel("Zadej elevaci strely");
        panel.add(ele);
        elevace = new TextField(sirkatextField);
        panel.add(elevace);

        // prida do panelu textField pro zadani rychlosti strely
        rych = new JLabel("Zadej rychlost strely ( v m/s)");
        panel.add(rych);
        rychlost = new TextField(sirkatextField);
        panel.add(rychlost);

        vystrel = new JButton("Vystrel");
        vystrel.addActionListener(event -> {
            panelCentr.repaint();
            gra.setRychlo(Integer.parseInt(rychlost.getText()));
            gra.setElevace(Integer.parseInt(elevace.getText()));
            gra.setAzimut(Integer.parseInt(azimut.getText()));

            panelCentr.add(gra.strelaAzeme());
            panelCentr.add(gra.dostrel());

            okno.getContentPane().add(panelCentr, BorderLayout.CENTER);
            okno.getContentPane().add(panelCentr, BorderLayout.CENTER);

            vzdKcili.setText("Vzdalenost k cili od dopadu strely:" + gra.vzdaKcili() + " m");

            gra.smaz();

            okno.repaint();
            okno.pack();
        });
        panel.add(vystrel);
        // nastavi barvu dolniho panelu
        panel.setBackground(Color.YELLOW);

        // horni panel
        // vypisuje vzdalenost k cili
        vzdKcili = new JLabel("vzdalenost k cili :" + gra.vzdaHrac());
        panelTop.add(vzdKcili);
        // pridava tlacitko pro vyresetovani hry a noveho spusteni
        reset = new JButton("zpet");
        reset.addActionListener(event -> {
            new HlavniOkno(jmeno);
            okno.setVisible(false);

        });

        panelTop.add(reset);

        // levy panel
        infoOcerHrac = new JLabel();
        infoOcerHrac.setText("Nadmorska vyska modreho \n hrace je: " + gra.vyskaModr() + " m");
        infoOcerHrac.setForeground(Color.BLUE);
        panelLeft.add(infoOcerHrac);
        infoOmodHrac = new JLabel();
        infoOmodHrac.setText("Nadmorska vyska cervenoho terce je: " + gra.vyskaCer() + " m");
        infoOmodHrac.setForeground(Color.RED);
        panelLeft.add(infoOmodHrac);
        rychVetru = new JLabel("Rychlost vetru je: " + gra.rychVetru() + " m/s");
        panelLeft.add(rychVetru);
        sipkaVetru = new JLabel(new ImageIcon(gra.sipkaVetru()));
        panelLeft.add(sipkaVetru);

        okno.getContentPane().add(panel, BorderLayout.SOUTH);
        okno.getContentPane().add(panelTop, BorderLayout.NORTH);
        okno.getContentPane().add(panelLeft, BorderLayout.WEST);

        // nastavavuje velikost okna
        okno.setSize(800, 600);

        // nastavi okno doprostred obrazovky
        okno.setLocationRelativeTo(null);
        okno.setVisible(true);
        okno.pack();

        // ukonci program po zavreni okna
        okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

}
