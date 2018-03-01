package my.zcu.upg;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.TextField;
import java.io.IOException;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class OknoStrelba extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    TextField elevace, azimut, rychlost;
    JButton vystrel, reset;
    JLabel vzdKcili, azi, ele, rych, infoOcerHrac, infoOmodHrac, teren, popis, popis2,
            rychVetru, sipkaVetru, smer, InfoOzasahu;
    private int sirkatextField = 5;
    private Strelba str;
    private JFrame okno;
    private Graphics2D bgGraph;


    public OknoStrelba(String jmeno) throws IOException {
        str = new Strelba(jmeno);
        okno = new JFrame();

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
        // prida do panelu textField kde pro zadani azimutu strely
        azi = new JLabel("Zadej azimut strely");
        panel.add(azi);
        azimut = new TextField(sirkatextField);
        panel.add(azimut);
        // prida do panelu textField kde pro zadani elevace strely
        ele = new JLabel("Zadej elevaci strely");
        panel.add(ele);
        elevace = new TextField(sirkatextField);
        panel.add(elevace);
        // prida do panelu textField kde pro zadani azimutu strely
        rych = new JLabel("Zadej rychlost strely ( v m/s)");
        panel.add(rych);
        rychlost = new TextField(sirkatextField);
        panel.add(rychlost);

        vystrel = new JButton("Vystrel");
        vystrel.addActionListener(event -> {
            str.setRychlo(Double.parseDouble(rychlost.getText()));
            str.setElevace(Double.parseDouble(elevace.getText()));
            str.setAzimut(Double.parseDouble(azimut.getText()));
            try {
                str.spoctiDrahu();
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            bgGraph = (Graphics2D) panelCentr.getGraphics();

            try {
                bgGraph.drawImage(str.strela(), 0, 0, null);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            str.smaz();
            vzdKcili.setText("Vzdalenost k cili od dopadu strely:" + str.vzdaKcili() + " m");
            if (str.zasah()) {
                vzdKcili = new JLabel();
                vzdKcili.setForeground(Color.RED);
                vzdKcili.setFont(new Font("Serif", Font.PLAIN, 20));
                vzdKcili.setText("Cil byl zasazen.");
                panelTop.add(vzdKcili);
            }
            okno.repaint();
        });
        panel.add(vystrel);
        // nastavi barvu dolniho panelu
        panel.setBackground(Color.YELLOW);

        // horni panel
        // vypisuje vzdalenost k cili
        vzdKcili = new JLabel("Vzdalenost k cili :" + str.vzdaHrac() + " m");
        panelTop.add(vzdKcili);
        reset = new JButton("zpet");
        reset.addActionListener(event -> {
            HlavniOkno nov = new HlavniOkno();
            nov.setJmeno(jmeno);
            okno.setVisible(false);
        });

        panelTop.add(reset);

        // levy panel
        infoOcerHrac = new JLabel();
        infoOcerHrac.setText("Nadmorska vyska modreho \n hrace je: " + str.vyskaModr() + " m");
        infoOcerHrac.setForeground(Color.BLUE);
        panelLeft.add(infoOcerHrac);

        infoOmodHrac = new JLabel();
        infoOmodHrac.setText("Nadmorska vyska cervenoho terce je: " + str.vyskaCer() + " m");
        infoOmodHrac.setForeground(Color.RED);
        panelLeft.add(infoOmodHrac);

        String b = "Rychlost vetru je: ";
        rychVetru = new JLabel(b + str.rychVetru());
        panelLeft.add(rychVetru);

        smer = new JLabel("Smer vetru je znazornen sipkou.");
        panelLeft.add(smer);

        sipkaVetru = new JLabel(new ImageIcon(str.sipkaVetru()));
        panelLeft.add(sipkaVetru);

        popis = new JLabel();
        String s = "<html><br><br>Teren je znazornen barevnou skalou ,<br>";
        s += "kde bila je nejvyssi bod a cerna nejnizssi<br><br></html>";
        popis.setText(s);
        panelLeft.add(popis);

        popis2 = new JLabel();
        String d = "<html> Strela je znazornena barevnou skalou,<br>";
        d += "ktera jde od<font color = red> cervene</font> po <font color = yellow>zlutou.</font><br>";
        d += "Cim je draha strely zlutejsi tim je strela vyse.</html>";
        popis2.setText(d);
        panelLeft.add(popis2);
        panelLeft.setBackground(Color.WHITE);
        panelLeft.setAlignmentX(Component.CENTER_ALIGNMENT);

        // stredni panel
        teren = new JLabel(new ImageIcon(str.teren()));
        panelCentr.add(teren);

        okno.getContentPane().add(panel, BorderLayout.SOUTH);
        okno.getContentPane().add(panelTop, BorderLayout.NORTH);
        okno.getContentPane().add(panelLeft, BorderLayout.WEST);
        okno.getContentPane().add(panelCentr, BorderLayout.CENTER);

        // nastavavuje velikost okna
        okno.setSize(800, 600);

        // nastavi okno doprostred obrazovky
        okno.setLocationRelativeTo(null);
        okno.setVisible(true);

        // ukonci program po zavreni okna
        okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

}
