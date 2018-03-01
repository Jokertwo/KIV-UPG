package my.zcu.upg;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;


public class KresleniPlochy extends JPanel {
    private static final long serialVersionUID = 1L;

    private Trajektorie trajekt;
    private float redStrela = 1.0f;
    private float blueStrela = 0.1f;
    private double VvitrX;
    private double VvitrY;
    private int velikostDopadu;
    private BufferedImage image;

    private BufferedImage sipka = new BufferedImage(195, 195, BufferedImage.TYPE_3BYTE_BGR);


    public KresleniPlochy(int velikostDopadu, BufferedImage image) {
        this.velikostDopadu = velikostDopadu;
        this.image = image;
    }


    public void setVitr(double VvitrX, double VvitrY) {
        this.VvitrX = VvitrX;
        this.VvitrY = VvitrY;

    }


    public void setTrajekt(Trajektorie trajekt) {
        this.trajekt = trajekt;
    }


    /**
     * prevadi cisla na hodnotu mezi 0 az 1
     * 
     * @param a
     *            jako parametr bere hodnotu double kterou prevede
     * @return vraci hodnotu float v rozsahu 0 az 1
     * 
     */
    private float barvaStrely(double a) {
        float vysledek = (float) a;
        float pom1 = vysledek - (float) trajekt.getMinPoleZ0();
        float pom2 = (float) trajekt.getMaxPoleZ0() - (float) trajekt.getMinPoleZ0();
        vysledek = pom1 / pom2;
        return vysledek;
    }


    /**
     * kresli drahu letu strely
     * 
     * @param g2
     *            Graphics2D
     * 
     */
    public BufferedImage vykresliStrelu() {
        Graphics2D g2 = image.createGraphics();
        for (int i = 0; i < trajekt.velikostPole() - 1; i++) {

            float green = barvaStrely(trajekt.getz0PolePrvek(i));

            // nastavuje barvu strely podle vysky
            Color barva = new Color(redStrela, green, blueStrela);

            g2.setStroke(new BasicStroke(2));
            g2.setColor(barva);
            g2.drawLine((int) trajekt.getx0PolePrvek(i),
                (int) trajekt.gety0PolePrvek(i),
                (int) trajekt.getx0PolePrvek(i + 1),
                (int) trajekt.gety0PolePrvek(i + 1));

            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        }
        dopad(image);

        return image;

    }


    /**
     * pokud strela dopadla v mape nakresli dopad jinak vypise info o tom ze strela vyletela z mapy
     * 
     * @param g2
     *            Graphics2D
     */
    public void dopad(BufferedImage image) {

        if (trajekt.kontrolaDopaduVMape() == true) {
            Graphics2D g2 = image.createGraphics();
            g2.setColor(Color.ORANGE);

            g2.fillOval(trajekt.getX0() - (velikostDopadu / 2), trajekt.getY0() - (velikostDopadu / 2), velikostDopadu,
                velikostDopadu);
        }

    }


    /**
     * vykresleni smeru vetru sipkou ktera se meni podle velikosti vetru a ulozeni do pameti jako BufferedImage
     * 
     * @param g2
     *            Graphics2D
     */
    public BufferedImage sipka() {
        double x1 = sipka.getHeight() / 2;
        double x2 = (VvitrX * 2) + x1;
        double y1 = sipka.getWidth() / 2;
        double y2 = (VvitrY * 2) + x2;
        double lineThickness = 4;
        double arrowScale = 2;

        Double sx, sy, dv, kx, ky;

        Graphics2D g2 = sipka.createGraphics();

        // nastavy silu pera sipky
        g2.setStroke(new BasicStroke((float) lineThickness));

        g2.setColor(Color.BLUE);

        // napred nakresli pouze caru
        g2.draw(new Line2D.Double(x1, y1, x2, y2));

        // smerovy vektor dlouhe cary
        sx = x2 - x1;
        sy = y2 - y1;

        // delka smeroveho vektoru
        dv = Math.sqrt(sx * sx + sy * sy);

        // normalizace smeroveho vektoru
        sx /= dv;
        sy /= dv;

        // vektor kolmy ke smerovemu
        kx = -sy;
        ky = sx;

        // upravit delky vektoru podle tlouskty cary a meritka sipky
        kx *= lineThickness * arrowScale;
        ky *= lineThickness * arrowScale;
        sx *= lineThickness * arrowScale;
        sy *= lineThickness * arrowScale;

        // vykresli dve male carky
        g2.draw(new Line2D.Double(x2 - sx + kx, y2 - sy + ky, x2, y2));
        g2.draw(new Line2D.Double(x2 - sx - kx, y2 - sy - ky, x2, y2));

        return sipka;

    }

}
