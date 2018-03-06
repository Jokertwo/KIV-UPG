package my.zcu.upg;

import java.util.ArrayList;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


public class VytvareniGrafu {
    public Trajektorie tra;
    public MaticeTerenu nove;
    public Bod bod = new Bod();
    private int meritko;
    private ArrayList<Double> vzdalenostvMetrech = new ArrayList<Double>();


    public VytvareniGrafu(Trajektorie tra, MaticeTerenu nove, int meritko) {
        this.nove = nove;
        this.tra = tra;
        this.meritko = meritko;
    }


    /**
     * prepocitava vzdalenost kterou strela uletela vytvari pole naplnene hodnotami hodnody predstavuji vzdalenost od
     * prvniho bodu
     * 
     * @return vraci pole naplnene hodnotamy
     */
    public ArrayList<Double> prepocetVzdalenosti() {
        bod.setX1(nove.getCanonX());
        bod.setY1(nove.getCanonY());
        for (int i = 0; i < tra.velikostPole(); i++) {
            bod.setX2(tra.getx0PolePrvek(i));
            bod.setY2(tra.gety0PolePrvek(i));
            double pom = bod.vzdalenost() * meritko;
            vzdalenostvMetrech.add(pom);
        }
        return vzdalenostvMetrech;
    }


    /**
     * ziskava hodnotu vzdalenosti z pole
     * 
     * @param a
     *            index prvku ktery chci ziskat
     * @return vraci prvek na zadanem indexu
     */
    public double ziskejVzdalenost(int a) {
        double pom = (double) vzdalenostvMetrech.toArray()[a];
        return pom;

    }


    /**
     * vytvari xy souradnice grafu nacita osu Z (vysku v metrech) a tu uklada jako 'Y' souradnici 'X' souradnice je
     * vzdalenost kterou strela uletela
     * 
     * @return vraci pole naplnene souradnicemi grafu
     */
    public XYSeries strelaGraf() {
        XYSeries data = new XYSeries("Strela");
        for (int i = 0; i < tra.velikostPole(); i++) {
            data.add(ziskejVzdalenost(i), tra.getz0PolePrvek(i));

        }
        return data;
    }


    /**
     * vytvari pole naplnene souradnicemi pro graf vyjadrujici zavislost mezi pocatecni rychlosti na elevaci vyjadrujici
     * vzdalenost kam strela doleti
     * 
     * @return vraci pole(XYSeries) naplnene (x,y) souradnicemi
     */
    public XYSeries vzdalenosti() {
        // generuje dve pole generujici x a y souradnici dopadu
        tra.dostrel();
        XYSeries data = new XYSeries("Strela2");
        // nastavi pocatek do (0,0)
        bod.setX1(0);
        bod.setY1(0);
        for (int i = 0; i < tra.getVelikostPoleDostrel(); i++) {

            // nastavi x2 souradnici jako dopadovou souradnici x z pole
            bod.setX2((int) tra.getPrvekDostrelx(i));

            // nastavi x2 souradnici jako dopadovou souradnici y z pole
            bod.setY2((int) tra.getPrvekDostrely(i));

            // spocita vzdalenost mezi dopadem a pocatkem
            double pom = bod.vzdalenost();

            // ulozi vysledek do pole
            data.add(i, pom);

        }
        return data;
    }


    /**
     * vytvari souradnice grafu nacita ze vstupniho souboru vysku terenu tu uklada jako 'Y' souradnici 'X' souradnice je
     * vzdalenost v metrech
     * 
     * @return vraci pole s X a Y souradnicemi
     */
    public XYSeries zemeGraf() {
        XYSeries data2 = new XYSeries("Zeme");
        for (int i = 0; i < tra.velikostPole() - 1; i++) {

            int pom1 = (int) nove.getPrvek((int) tra.getx0PolePrvek(i), (int) tra.gety0PolePrvek(i));
            data2.add(ziskejVzdalenost(i), pom1);

        }
        return data2;
    }


    /**
     * vytvari lineGraf podle souradnic kresli graf kde prvni je strela a druhy je zeme pod ni(vyska zeme)
     * 
     * @return vraci graf
     */

    public JFreeChart strelaAZeme() {
        // spocita drahu strely
        tra.drahaStrely();
        // spocita 'uletenou' vzdalenost
        prepocetVzdalenosti();
        // vygenerujeme datovou radu
        XYSeries zeme = zemeGraf();
        XYSeries strela = strelaGraf();
        XYSeriesCollection ds = new XYSeriesCollection();
        ds.addSeries(zeme);
        ds.addSeries(strela);
        JFreeChart chart = ChartFactory.createXYLineChart(
            "Vykresleni drahy letu strely nad zemi se zapoctenim vlivu vetru ",
            "Vzdalenost v metrech", "Vyska v metrech nad zemi",

            ds, PlotOrientation.VERTICAL,
            true, true, false); // legends, tooltips, urls

        return chart;
    }


    public JFreeChart pomerElevaceARychlosti() {

        // vygeneruje datovou radu
        XYSeries data = vzdalenosti();
        XYSeriesCollection ds = new XYSeriesCollection();

        ds.addSeries(data);
        JFreeChart chart = ChartFactory.createXYLineChart("Zavislost dostrelu na pocatecni rychlosti.",
            "Pocatecni rychlost", "Vzdalenost",

            ds, PlotOrientation.VERTICAL,
            true, true, false);
        chart.getAntiAlias();
        return chart;
    }

}
