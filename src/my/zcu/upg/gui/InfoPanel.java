package my.zcu.upg.gui;

import javax.swing.JLabel;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;


public class InfoPanel extends JPanel {

    /**
     * 
     */
    private static final long serialVersionUID = -2395975329734080669L;

    private JLabel label = new JLabel();
    private JLabel zasah = new JLabel("Cíl byl zasažen.");
    private static final String vzdalenost = "Vzdálenost k cíli: ";


    public InfoPanel() {
        setLayout(new MigLayout("align 50% 50%"));
        label.setText(vzdalenost);
        add(label,"align center,wrap");
        add(zasah,"align center");
        zasah.setVisible(false);
    }


    public void setNewDistance(int distance) {
        label.setText(vzdalenost + distance);
    }
    
    
    public void showZasah(){
        zasah.setVisible(true);
    }

}
