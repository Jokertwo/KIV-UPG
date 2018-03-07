package my.zcu.upg.gui;

import java.awt.Label;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;


public class Ovladani extends JPanel {

    private static final String AXIMUT = "Azimut";
    private static final String RYCHLOST = "Rychlost";
    private static final String ELEVACE = "Elevace";

    private static final String ARG_MIG = "growx,pushx";

    private JTextField aximut = new JTextField();
    private JTextField elevace = new JTextField();
    private JTextField rychlost = new JTextField();
    private JButton shoot = new JButton("Vystrel");
    /**
     * 
     */
    private static final long serialVersionUID = 1L;


    public Ovladani() {
        setLayout(new MigLayout());
        add(createBox(AXIMUT, aximut), ARG_MIG);
        add(createBox(ELEVACE, elevace), ARG_MIG);
        add(createBox(RYCHLOST, rychlost), ARG_MIG);
        add(shoot, ARG_MIG);
    }


    public void setShootAction(ActionListener action) {
        shoot.addActionListener(action);
    }


    private JPanel createBox(String nameJL, JTextField nameTF) {
        JPanel box = new JPanel();
        box.setLayout(new MigLayout());
        box.add(new Label(nameJL));
        box.add(nameTF, ARG_MIG);
        return box;
    }


    public String getRychlost() {
        return rychlost.getText();
    }


    public String getElevace() {
        return elevace.getText();
    }


    public String getAzimut() {
        return aximut.getText();
    }

}
