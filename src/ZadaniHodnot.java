import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import org.jfree.chart.JFreeChart;

public class ZadaniHodnot {

	
	private MaticeTerenu nove = new MaticeTerenu();;
	private VytvareniGrafu graf;
	private Trajektorie tra;
	private KresleniPlochy obr;
	private Teren ter = new Teren();
	private Random r = new Random();
	private Bod vz = new Bod();
	private double VvitrX,VvitrY;
	private int velikostDopadu = 12;
	private int meritko ;
	private int silaVetru;
	private JFreeChart chart; 
	private JFreeChart chart2;
	private String jmeno;
	
	public void jmeno(String jmeno){
		this.jmeno = jmeno;
	}
	public void matice(MaticeTerenu nove){
		this.nove = nove;
	}
	
	
	/**
	 * vytvori teren podle matice 
	 * a ulozi ho do pameti
	 */
	public void vytvorTer(){
		ter.setMatice(nove);
		try {
			ter.teren();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 * spocte maximum a minimum pole z0Pole ve tride 
	 * trajektorie
	 */
	public void maxMin(){
		tra.maxPoleZ0();
		tra.minPoleZ0();
	}
	
	/**
	 * nastavi meritko(mapy) ve tride Teren 
	 */
	public void nastavMeritko(){
		ter.setMeritko(meritko);
	}
	
	public BufferedImage getTeren(){
		return ter.getTeren();
	}
	/**
	 * ziska nacteny obrazek v pameti
	 * @return obrazek z pameti
	 */
	public BufferedImage getSipka(){
		return obr.sipka();
	}
	public BufferedImage strela(){
		return obr.kresleniStrely();
	}
	/**
	 * da metode kresleniStrely ve tride KresleniPlochy obrazek do ktereho nakresli
	 * drahu strely 
	 * @return vraci nacteny obrazek v pameti
	 */
	public void nakresli() throws IOException {
		obr.setImage(ter.getTeren());
		
	}
	public void trajektorie(){
		tra = new Trajektorie(nove,meritko,velikostDopadu);
		tra.setVvitrX(VvitrX);
		tra.setVvitrY(VvitrY);
		tra.setBod(vz);
	}
	
	public void kresleniPlochy(){
		obr = new KresleniPlochy(meritko,velikostDopadu);
	
		
	}
	public void vytvareniGrafu(){
		graf = new VytvareniGrafu(tra,nove,meritko);
	}
	/**
	 * vytvori graf strely jak leti nad zemi plus relief
	 * zeme pod strelou se zapoctenim vlivu vetru
	 * @return vraci vytvoreny graf
	 */
	public JFreeChart vytvorgrafStrelaAzeme(){
		chart = graf.strelaAZeme();
		return chart;
	}
	public JFreeChart vytvorgrafDostrelu(){
		chart2 = graf.pomerElevaceARychlosti();
		return chart2;
	}
	/**
	 * preda informace z tridy Trajektorie do Kresleni plochy 
	 */
	public void setTrajektDoKresleni(){
		obr.setTrajekt(Trajektorie());
	}
	/**
	 * nastavi smer vetru sipky
	 * ve tride KresleniPlochy
	 */
	public void setVitr(){
		obr.setVitr(VvitrX, VvitrY);
	}
	/**
	 * zavola metodu ktera maze pole se souradnicemi
	 */
	public void smaz(){
		tra.smazTrasu();
	}
	public void smazDostrel(){
		tra.smazDostrel();
	}
	/**
	 * vytiskne informaci o sile vetru do konzole
	 */
	public int infoVitr(){
		return silaVetru;
	}
	public KresleniPlochy KresleniPlochy(){
		return obr;
	}
	public Trajektorie Trajektorie(){
		return tra;
	}
	
	/**
	 * vytvori meritko pro zmenseni terce
	 */
	
	
	
	/**
	 * nastaveju azimut strely
	 * @param uhel
	 */
	public void setUhel(double uhel){
		tra.uhel(uhel);
	}
		/**
		 * spocita drahu strely
		 * a ulozi souradnice do dvou poli
		 */
	public void spoctiDrahu(){
		tra.drahaStrely();
	}
	public int vzdaKcili(){
		vz.setX1(nove.getTargetX());
		vz.setY1(nove.getTargetY());
		vz.setX2(tra.getX0());
		vz.setY2(tra.getY0());
		return (int)vz.vzdalenost();
	}
	
	/**
	 * Nastavuje elevaci strely
	 * @param elevace
	 */
	public void setElevace(double elevace){
		
	 		tra.elevace(elevace);
	 	
	}
	
	
	/**
	 * nastavuje rychlost strely
	 * @param rychlost
	 */
	public void setRychlost(double rychlost){
		tra.rychlost(rychlost);
	}
	
	
	/**
	 * najde v matici nejvetsi a nejmensi prvek a ulozi je do promenych
	 */
	public void minAmaxPrvek(){
		nove.nejvetsiPr();
		nove.nejmensiPr();
	}
	
	/**
	 * nastavuje meritko mapy
	 * @param meritko hodnota meritka
	 */
	public void setMeritko(int meritko){
		this.meritko = meritko;
	}
	
	
	/**
	 * tiskne do konzole
	 * @param a bere String ktery ma vytisknout
	 */
	public void tisk(String a){
		System.out.println(a);
	}
	
	
	/**
	 * spocita vzdalenost dvou hracu
	 */
	public void vzdHracu(){
		vz.nastavVse(nove.getCanonX(),nove.getCanonY(),nove.getTargetX(),nove.getTargetY());
	}
	
	
	/**
	 * vraci vzdalenost mezi hraci spocitanou metodou vzdHracu
	 * 
	 * @return vraci int zaokrohlene na cela cisla
	 */
	public int VzHracu(){
		return (int)Math.round( vz.vzdalenost()) ;
	}
	
	
	/**
	 * predava informaci o vysce cervenoho cile
	 * @return vraci int cislo s nadmorskou vyskou
	 */
	public int vysCerv(){
		return nove.vyskaCerv();
	}
	/**
	 * pradava informaci o nadmorske vysce modraho strelce
	 * @return vraci int cislo s nadmorskou vyskou
	 */
	public int vysModr(){
		return nove.vyskaModry();
	}
	
	
	/**nahodne generuje vitr
	pritom maximalni sila vetru je 32 m/s
	vypnuti vlivu vetru staci zadat siluVetru 0
	vitr se generuje ve stupnici  od 0 (bezvetri)
	az do 32 (sila orkanu)
	 */
	public void generujVitr(){
		int vitr = r.nextInt(360);
		silaVetru = r.nextInt(32);
		
		VvitrX = silaVetru * Math.cos(Math.toRadians(vitr));
		VvitrY = silaVetru * Math.sin(Math.toRadians(vitr));
		
	}
	
	
	/**
	 * nastavi jmeno souboru ze ktereho se ma cist
	 * @param jmeno jmeno souboru ze ktereho ma cist
	 * @throws IOException
	 */
	public void jmenoSouboru(String jmeno) throws IOException{
		nove.matice(jmeno);
	}
	
	
	/**
	 * nastavi velikost dopadu
	 * @param velikostDopadu 
	 */
	public void setVelikostDopadu(int velikostDopadu){
		this.velikostDopadu = velikostDopadu;
	}
	public BufferedImage bila(){
		BufferedImage bila = new BufferedImage(nove.getSirka(),nove.getVyska(),BufferedImage.TYPE_INT_BGR);
		Graphics2D g2 = bila.createGraphics();
		g2.setColor(Color.BLACK);
		g2.fillRect(0, 0, bila.getWidth(), bila.getHeight());
		return bila;
	}
	/**
	 * kontroluje zasah cile
	 * @return vraci true pokud byl cil zasazen
	 */
	public boolean zasah(){
		if(tra.vzOdDopadu()){
				return true;
				}
		return false;
	}
	
}
