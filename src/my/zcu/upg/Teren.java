package my.zcu.upg;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Teren {

	
	private BufferedImage image,modryHrac,cervenyHrac;
	private MaticeTerenu matice;
	private int meritko;
	private AffineTransformOp op;

	public AffineTransformOp getOp(){
		return op;
	}
	public void setMeritko(int merikto){
		this.meritko = merikto;
	}
	public void setMatice(MaticeTerenu matice){
		this.matice = matice;
	}
	public BufferedImage getTeren(){
		return image;
	}
	public BufferedImage getmodHrac(){
		return modryHrac;
	}
	public BufferedImage getcerHrac(){
		return cervenyHrac;
	}
	
	/**
	 * nacte obrazek terce
	 * 
	 * @return vraci bufferedImage ulozeny v pameti(obrazek strelce)
	 * @throws IOException vyhodi vyjjimku v pripade ze nenajde soubor 
	*/
	public BufferedImage cervenyHrac() throws IOException{
		try{
		cervenyHrac = ImageIO.read(new File("cil.png"));
		}
		catch(IOException e){
			System.err.println("Soubor nenalezen.");	
			}
		
		
	return cervenyHrac;
	}
	public 	AffineTransformOp velikostTercu(BufferedImage hrac){
		AffineTransform at = AffineTransform.getScaleInstance(
				image.getWidth() / (double)hrac.getWidth()* 0.1, 
				image.getHeight()/ (double)hrac.getHeight()* 0.1
				);
		
	 AffineTransformOp op = new AffineTransformOp(at, AffineTransformOp.TYPE_BICUBIC);
	 
	 return op;
	}
	/**
	 * nacte obrazek strelce
	 * @return vraci bufferedImage ulozeny v pameti(obrazek cile)
	 * @throws IOException vyhodi vyjjimku v pripade ze nenajde soubor
	*/
	public BufferedImage modryHrac() throws IOException{
		
		try{
		modryHrac = ImageIO.read(new File("kanon.png"));
		}
		catch(IOException e){
		System.err.println("Soubor nenalezen.");	
		}
		
		return modryHrac;
	}
	
	
		/**
	 * nacte teren do pameti BufferedImage
	 * @return vraci nacteny teren v pameti BufferedImage
		 * @throws IOException 
	 */
	
public BufferedImage teren() throws IOException{
		
		image = new BufferedImage(matice.getSirka(),matice.getVyska(), BufferedImage.TYPE_INT_BGR);
		image.createGraphics();
		for (int i = 0; i < matice.getSirka(); i++) {
			 for (int j = 0; j < matice.getVyska(); j++) {
				 
				 if (matice.getMaximun() != matice.getMinimum()){
					 	Color barva = new Color(matice.jedenBod(i, j),matice.jedenBod(i, j),matice.jedenBod(i, j));
					 	image.setRGB(i, j, barva.getRGB());
				 		}
				 
				 else {
					 	Color barva = new Color(127,127,127);
					 	image.setRGB(i, j, barva.getRGB());
				 }
			 }
			 }
		Graphics2D g2 = image.createGraphics();
		g2.setStroke(new BasicStroke(10));
		g2.setColor(Color.RED);
		//nakresli modreho strelce
		g2.drawLine(matice.getTargetX()-meritko, matice.getTargetY(),
					matice.getTargetX()+meritko, matice.getTargetY());
		g2.drawLine (matice.getTargetX(), matice.getTargetY()+meritko,
					matice.getTargetX(), matice.getTargetY()-meritko);

		//nakresli cerveny cil
		g2.setColor(Color.BLUE);
		g2.drawLine(matice.getCanonX(), matice.getCanonY(),
					matice.getCanonX(), matice.getCanonY());
		g2.drawLine(matice.getCanonX(), matice.getCanonY(),
					matice.getCanonX(), matice.getCanonY());
			


		return image;
	}
}
