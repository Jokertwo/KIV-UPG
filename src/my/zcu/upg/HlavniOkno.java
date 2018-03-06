package my.zcu.upg;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class HlavniOkno extends JFrame {

    private final static String VIZUALIZACE = "<html> Pro zobrazeni grafu stiskni <font color = green>Vizualizace</font></html>";
    private final static String GRAF = "<html>Pro generovani terenu,zobrazeni mapy<br> a strelbu "
            + "stisni <font color = green>Strela</font></html>";

    private static final long serialVersionUID = 1L;

    private JPanel panel = new JPanel();

    private String jmenoSouboru;


    public JButton tlStrelba() {
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


    public JButton tlVizu() {
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
        panel.setBackground(Color.WHITE);
        JLabel label = new JLabel();
        label.setText(text);
        panel.add(label);
        return panel;
    }


    public HlavniOkno(String jmenoSouboru) {
        this.jmenoSouboru = jmenoSouboru;

        panel.add(tlStrelba());
        panel.add(tlVizu());
        panel.setBackground(Color.RED);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(600, 200);
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(createPanel(GRAF), BorderLayout.WEST);
        getContentPane().add(createPanel(VIZUALIZACE), BorderLayout.EAST);
        setTitle("UPG-Semetralni prace 2016");
        setLocationRelativeTo(null);
        pack();
    }
}
