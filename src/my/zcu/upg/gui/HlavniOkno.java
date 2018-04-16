package my.zcu.upg.gui;

import java.awt.Color;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import my.zcu.upg.OknoGrafu;
import net.miginfocom.swing.MigLayout;


public class HlavniOkno extends JFrame {

    private final static String VIZUALIZACE = "<html> Pro zobrazeni grafu stiskni <font color = green>Vizualizace</font></html>";
    private final static String GRAF = "<html>Pro generovani terenu,<br>zobrazeni mapy a strelbu "
            + "stisni <font color = green>Strela</font></html>";

    private static final long serialVersionUID = 1L;

    private JPanel panel = new JPanel();

    private String jmenoSouboru;


    public HlavniOkno(String jmenoSouboru) {
        this.jmenoSouboru = jmenoSouboru;

        panel.setLayout(new MigLayout());
        panel.add(tlStrelba(), "grow,push");
        panel.add(tlVizualizace(), "grow,push");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(600, 200);
        setLayout(new MigLayout());
        add(createPanel(GRAF), "grow,push");
        add(createPanel(VIZUALIZACE), "grow,push,wrap");
        add(panel, "grow,push,span 2");

        setTitle("UPG-Semetralni prace 2016");
        setLocationRelativeTo(null);
        pack();
    }


    private JButton tlStrelba() {
        JButton strelba = new JButton("Strelba");
        strelba.addActionListener(event -> {

            try {
                new OknoStrelba(jmenoSouboru);
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            setVisible(false);
        });
        return strelba;
    }


    private JButton tlVizualizace() {
        JButton vizu = new JButton("Vizualizace");
        vizu.addActionListener(event -> {
            try {
                new OknoGrafu(jmenoSouboru);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            setVisible(false);

        });
        return vizu;
    }


    private JPanel createPanel(String text) {
        JPanel panel = new JPanel();
        panel.setLayout(new MigLayout());
        panel.setBackground(Color.WHITE);
        JLabel label = new JLabel();
        label.setText(text);
        panel.add(label);
        return panel;
    }

}
