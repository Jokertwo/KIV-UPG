package my.zcu.upg.gui;

import java.awt.Graphics2D;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import my.zcu.upg.StrelbaBalistic;
import net.miginfocom.swing.MigLayout;


public class OknoStrelba extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    TextField elevace, azimut, rychlost;
    JButton vystrel, reset;

    private StrelbaBalistic str;
    private Graphics2D bgGraph;
    private Ovladani ovladani;
    private InfoPanel infoPanel;
    private JPanel panelCentr = new JPanel();


    public OknoStrelba(String jmenoSouboru) throws IOException {
        setLayout(new MigLayout());
        str = new StrelbaBalistic(jmenoSouboru);
        ovladani = new Ovladani();
        infoPanel = new InfoPanel();
        infoPanel.setNewDistance(str.vzdaKcili());

        reset = new JButton("zpet");
        reset.addActionListener(e -> {
            new HlavniOkno(jmenoSouboru);
            setVisible(false);
        });

        // stredni panel
        JPanel teren = new JPanel();
        teren.add(new JLabel(new ImageIcon(str.teren())));
        panelCentr.add(teren);

        add(new GameInfo(str));
        add(panelCentr, "wrap,growx,pushx");
        add(ovladani, "span , growx,pushx");

        // nastavavuje velikost okna
        pack();

        // nastavi okno doprostred obrazovky
        setLocationRelativeTo(null);
        setVisible(true);

        // ukonci program po zavreni okna
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ovladani.setShootAction(new ActionShoot());
    }

    private class ActionShoot implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            str.setRychlo(Double.parseDouble(ovladani.getRychlost()));
            str.setElevace(Double.parseDouble(ovladani.getElevace()));
            str.setAzimut(Double.parseDouble(ovladani.getAzimut()));
            try {
                str.spoctiDrahu();
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            bgGraph = (Graphics2D) panelCentr.getGraphics();
            bgGraph.drawImage(str.strela(), 0, 0, null);

            str.smaz();

            if (str.zasah()) {
                infoPanel.showZasah();
            }
            repaint();

        }

    }

}
