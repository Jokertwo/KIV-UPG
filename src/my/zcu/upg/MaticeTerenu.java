package my.zcu.upg;



import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;


public class MaticeTerenu {
	
	DataInputStream in2;
	
	private int sirka;
	private int vyska;
	private int canonX;
	private int canonY;
	private int targetX;
	private int targetY;
	private int teren[][];
	private int minimum;
	private int maximun;
	
	
	
	
	
	
	
	
		public int getSirka(){
			return sirka;
		}
		public int getVyska(){
			return vyska;
		}
		public int getCanonX(){
			return canonX;
		}
		public int getCanonY(){
			return canonY;
		}
		public int getTargetX(){
			return targetX;
		}
		public int getTargetY(){
			return targetY;
		}
		public int[][] getTeren(){
			return teren;
		}
		public int getMaximun(){
			return maximun;
		}
		public int getMinimum(){
			return minimum;
		}
		/**
		 * vrati prvek na pozici
		 * 
		 * @param x radek
		 * @param y sloupec
		 * @return vraci hodnotu double na zadanem indexu
		 */
		public double getPrvek(int x,int y){
			double prvek = getTeren()[x][y];
			return prvek;
		}
		
		
		       	/**nacita data z binarniho souboru
		       	 *   jako parametr bere vstupni argumet
		       	 * 	   vraci nacteny soubor ulozeny do jednotlivych promenych
		       	 * @param jmeno jmeno(string) souboru ze ktereho se ma nacist 
		       	 * @throws IOException
		       	 */
		       	
				
		public void matice(String jmeno) throws IOException{
			
			in2 = new DataInputStream( new FileInputStream(jmeno));
			
			sirka = in2.readInt();
			vyska = in2.readInt();
			canonX = in2.readInt();
			canonY = in2.readInt();
			targetX = in2.readInt();
			targetY = in2.readInt();
			teren = new int[sirka][vyska];
			for (int i = 0; i < vyska;++i){
				for(int j = 0; j < sirka;++j){
					teren[j][i] = in2.readInt(); 
				}
			}
			
			
		}
		
		
		
		
		
	/**
	 * najde nejmensi prvek z matice
	 */
	public void nejmensiPr(){
		
		int min = Integer.MAX_VALUE;
		for(int i = 0; i < getTeren().length;++i){
			for (int j = 0 ; j < getTeren()[i].length; ++j){
				if(min > getTeren()[i][j]){
					min = getTeren()[i][j];
					minimum = min;
				}
			}
		}
		
		
	}
	
	
				/**
				 * najde nejvets9 prvvek z matice
				 */
		public void nejvetsiPr(){
			int max = Integer.MIN_VALUE;
			for(int i = 0; i < getTeren().length;++i){
				for (int j = 0 ; j < getTeren()[i].length; ++j){
					if(max < getTeren()[i][j]){
						max = getTeren()[i][j];
						maximun = max;
					}
				}
			}
			
			
		}
		
		
					/**kazdy prvek pole prepocita do rozsahu 0 - 1
					 * * 		jako parametr bere pozici prvku v poli
					 * 				vraci hodnotu float 0 - 1
					 *
					 * @param i sloupec
					 * @param j radek
					 * @return prvek
					 */
					 
		public float jedenBod(int i,int j){
			float prvek = teren[i][j];
			float pom1 =  prvek - minimum;
			float pom2 = 	maximun - minimum;
			prvek = pom1 / pom2;
			return prvek;
		}
		
		
			
	
				/**
				 * vypisuje informaci o nadmorske vysce strelce
				 */
	public int vyskaModry(){
		
		int modryStrelec = teren[getCanonX()][getCanonY()];
		return modryStrelec;
	}
	
	public int vyskaCerv(){
		int cervenyStrelec = teren[getTargetX()][getTargetY()];
		return cervenyStrelec;
	}
	
	
}
	
	
	
	
	
	
	
	
	