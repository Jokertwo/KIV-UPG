package my.zcu.upg;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HlavniOkno extends JFrame {
	
	
	
	public void setJmeno(String jmeno){
		this.jmeno = jmeno;
	}
	
	
	private static final long serialVersionUID = 1L;

	JFrame frame = new JFrame();
	JPanel panel = new JPanel();
	JPanel pravy = new JPanel();
	JPanel levy  = new JPanel();
	JLabel pravyt = new JLabel();
	JLabel levyt = new JLabel();
	
	public String jmeno;
	
	public JButton tlStrelba(){
		JButton strelba = new JButton("Strelba");
		strelba.addActionListener(event ->{
			
										try {
												OknoStrelba ap = new OknoStrelba(jmeno);
											} catch (IOException e1) {
												// TODO Auto-generated catch block
												e1.printStackTrace();
											}
										VisibleOff();
								});
			return strelba;
	}
	public JButton tlVizu(){
	JButton vizu = new JButton("Vizualizace");
		vizu.addActionListener(event -> {try {
										OknoGrafu ok = new OknoGrafu(jmeno);
									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
										
										VisibleOff();
												
				
			});
	return vizu;
	}
	
	
	
	public HlavniOkno(){
		FlowLayout cent = new FlowLayout();
		FlowLayout pr = new FlowLayout();
		FlowLayout lv = new FlowLayout();
		
		pravy.setLayout(pr);
		pravyt.setText("<html>Pro generovani terenu,zobrazeni mapy<br> a strelbu "
				+ "stisni <font color = green>Strela</font></html>");
		pravy.add(pravyt);
		pravy.setBackground(Color.WHITE);
		
		levy.setLayout(lv);
		levyt.setText("<html> Pro zobrazeni grafu stiskni <font color = green>Vizualizace</font></html>");
		levy.add(levyt);
		levy.setBackground(Color.WHITE);
		
		panel.setLayout(cent);
		panel.add(tlStrelba());
		panel.add(tlVizu());
		panel.setBackground(Color.RED);
		
		
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		frame.getContentPane().add(pravy,BorderLayout.WEST);
		frame.getContentPane().add(levy,BorderLayout.EAST);
		frame.pack();
		frame.setLocationRelativeTo(null);
		
		
		
		
		
	
		
	}
	public void VisibleOff(){
		frame.setVisible(false);
	}
	public void VisibleON(){
		frame.setVisible(true);
	}
	
	
}
