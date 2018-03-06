package my.zcu.upg;

import java.util.ArrayList;
import java.util.Scanner;


public class Trajektorie {

    private int velikostDopadu;
    private double x, y, z;
    private double Vx, Vy, Vz;
    private double x0, y0, z0;
    private double odporVzduchu = 0.05;
    private double deltaT = 0.01;
    private double gravitacniZrychleni = 10;
    private int meritko;
    private double min, max;
    private int krok = 1;
    private double VvitrX, VvitrY;
    private double VvitrZ = 0;
    private double rychlost, uhel, elevace;
    private MaticeTerenu matice;
    private Bod bod;
    private ArrayList<Double> x0Pole = new ArrayList<Double>();
    private ArrayList<Double> y0Pole = new ArrayList<Double>();
    private ArrayList<Double> z0Pole = new ArrayList<Double>();
    private ArrayList<Double> dostrelx = new ArrayList<Double>();
    private ArrayList<Double> dostrely = new ArrayList<Double>();

    public Scanner sc = new Scanner(System.in);


    public Trajektorie(MaticeTerenu matice, int meritko, int velikostDopadu) {
        this.velikostDopadu = velikostDopadu / 2;
        this.meritko = meritko;
        this.matice = matice;
    }


    public void setBod(Bod bod) {
        this.bod = bod;
    }


    public void setVvitrX(double VvitrX) {
        this.VvitrX = VvitrX;
    }


    public void setVvitrY(double VvitrY) {
        this.VvitrY = VvitrY;
    }


    public int getX0() {
        return (int) x0;
    }


    public int getY0() {
        return (int) y0;
    }


    public int getZ0() {
        return (int) z0;
    }


    /**
     * navraci hodnotu krok jez se pouziva u pocitani a vytvareni pole pro spocteni zavylosti rzchlosti na urazene
     * vzdalenosti
     * 
     * @return vraci hodnotu na kterou je nastavena
     * 
     */
    public int getKrok() {
        return krok;
    }


    public void setX0(int x0) {
        this.x0 = x0;
    }


    public void setY0(int y0) {
        this.y0 = y0;

    }


    public double getMaxPoleZ0() {
        return max;
    }


    public double getMinPoleZ0() {
        return min;
    }


    /**
     * najde maximun PoleZ0 a nastavi
     * 
     */
    public void maxPoleZ0() {
        max = z0Pole.stream()
            .mapToDouble(z0Pole -> z0Pole)
            .max()
            .getAsDouble();

    }


    /**
     * najde minimum PoleZ0 a nastavi
     */
    public void minPoleZ0() {
        min = z0Pole.stream()
            .mapToDouble(z0Pole -> z0Pole)
            .min()
            .getAsDouble();

    }


    public void vypisPole() {
        z0Pole.stream()
            .forEach(z0Pole -> System.out.println(z0Pole));

    }


    /**
     * vymaze pole dostrelx a dostrely kde jsou ulozeny informace o dopadu strely pri ruzne rychlosti
     */
    public void smazDostrel() {
        dostrelx.clear();
        dostrely.clear();
    }


    /**
     * vymaze vsechna pole se souradnicemi
     */
    public void smazTrasu() {
        x0Pole.clear();
        y0Pole.clear();
        z0Pole.clear();
    }


    /**
     * kontroluje a nastavuje zadanou rychlost
     * 
     * @param rychlost
     *            jako parametr bere rychlost strely
     * @throws Chyba
     *             kontroluje jestli je rzchlost vetsi nez 0
     */
    public void rychlost(double rychlost) {

        this.rychlost = rychlost / meritko;

    }


    /**
     * kontroluje a nastavuje zadany azimut strely
     * 
     * @param uhel
     *            jako parametr bere azimut strely
     * @throws Chyba
     *             kontroluje jestli je azimut v rozsahu -180 az 180
     */
    public void uhel(double uhel) {

        if (uhel <= 0) {
            this.uhel = Math.abs(uhel);
        } else
            this.uhel = 360 - uhel;

    }


    /**
     * nastavuje a kontroluje zadanou elevaci
     * 
     * @param elevace
     *            jako parametr bere elevaci strely
     * @throws Chyba
     *             kontroluje jestli je elevace mezi 0 az 90
     */
    public void elevace(double elevace) {

        this.elevace = elevace;

    }


    /**
     * pocita a vytvari pole 2 pole, jedno pro x souradnice druhe pro y souradnice neni zde zapocitan vliv vetru
     * 
     */
    public void dostrel() {
        // ulozi pocatek do pole
        dostrelx.add(0.0);
        dostrely.add(0.0);

        for (int i = 0; i < 510; i += krok) {
            z = Math.sin(Math.toRadians(elevace));
            x = Math.cos(Math.toRadians(uhel));
            y = Math.sin(Math.toRadians(uhel));

            Vx = x * (1 - z) * i;
            Vy = y * (1 - z) * i;
            Vz = z * i;

            x0 = 0;
            y0 = 0;
            z0 = 0;
            do {

                x0 = x0 + Vx * deltaT;
                y0 = y0 + Vy * deltaT;
                z0 = z0 + Vz * deltaT;

                Vx = Vx + (0 * (gravitacniZrychleni / meritko) * deltaT) + (0 - Vx) * odporVzduchu * deltaT;
                Vy = Vy + (0 * (gravitacniZrychleni / meritko) * deltaT) + (0 - Vy) * odporVzduchu * deltaT;
                Vz = Vz + (-1 * (gravitacniZrychleni / meritko) * deltaT) + (0 - Vz) * odporVzduchu * deltaT;

            }
            // podmika pro ukonceni vypoctu letu strely
            // nez je z0 mensi nez nula neboli dopadne na zem
            while (z0 >= 0);
            dostrelx.add(x0);
            dostrely.add(y0);

        }

    }


    /**
     * vypocteni drahy strely se zapoctentm vlivu vetru naplnuji se zde 3 pole a to x a y souradnicemi a pak pole se z
     * souradnici ktera predstavuje vysku
     */
    public void drahaStrely() {
        z = Math.sin(Math.toRadians(elevace));
        x = Math.cos(Math.toRadians(uhel));
        y = Math.sin(Math.toRadians(uhel));

        Vx = x * (1 - z) * rychlost;
        Vy = y * (1 - z) * rychlost;
        Vz = z * rychlost;

        x0 = matice.getCanonX();
        y0 = matice.getCanonY();
        z0 = matice.getPrvek((int) x0, (int) y0);

        // ulozeni do pole prvni pozici strely
        x0Pole.add(x0);
        y0Pole.add(y0);
        z0Pole.add(z0);

        do {

            x0 = x0 + Vx * deltaT;
            y0 = y0 + Vy * deltaT;
            z0 = z0 + Vz * deltaT;

            Vx = Vx + (0 * (gravitacniZrychleni / meritko) * deltaT)
                    + ((VvitrX / meritko) - Vx) * odporVzduchu * deltaT;
            Vy = Vy + (0 * (gravitacniZrychleni / meritko) * deltaT)
                    + ((VvitrY / meritko) - Vy) * odporVzduchu * deltaT;
            Vz = Vz + (-1 * (gravitacniZrychleni / meritko) * deltaT)
                    + ((VvitrZ / meritko) - Vz) * odporVzduchu * deltaT;

            x0Pole.add(x0);
            y0Pole.add(y0);
            z0Pole.add(z0);

        }

        // podmika pro ukonceni vypoctu letu strely
        while (kontrolaDopaduVMape() && nadZemi(x0, y0));
    }


    /**
     * kontroluje jestli je strela jeste stale nad zemi
     * 
     * @return pokud se dostane pod uroven terenu vrati false jinak vraci true
     * @param a
     *            predstavuje sloupec matice
     * @param b
     *            predstavuje radek matice
     */
    public boolean nadZemi(double a, double b) {
        if (matice.getPrvek((int) a, (int) b) <= getZ0()) {
            return true;
        }
        return false;
    }


    /**
     * vraci prvek z ArrayListu
     * 
     * @param a
     *            index prvku
     * @return prvek na indexu
     */
    public double getx0PolePrvek(int a) {
        double pom = (double) x0Pole.toArray()[a];
        return pom;
    }


    /**
     * vraci prvek z ArrayListu
     * 
     * @param a
     *            index prvku
     * @return prvek na indexu
     */
    public double gety0PolePrvek(int a) {
        double pom = (double) y0Pole.toArray()[a];
        return pom;
    }


    /**
     * vraci prvek z pole
     * 
     * @param a
     *            index na kterem chci ziskat prvek
     * @return prvek na induxu predanem jako parametr
     */
    public double getPrvekDostrelx(int a) {
        double pom = (double) dostrelx.toArray()[a];
        return pom;
    }


    public double getPrvekDostrely(int a) {
        double pom = (double) dostrely.toArray()[a];
        return pom;
    }


    /**
     * zjistuje velikost pole ve kterem jsou uchovavana data s hodnotami dostrelu pri zadane elevaci a rychlosti
     * 
     * @return vraci velikost pole
     */
    public int getVelikostPoleDostrel() {
        return dostrelx.size();
    }


    /**
     * vraci prvek z ArrayListu
     * 
     * @param a
     *            index prvku
     * @return prvek na indexu
     */
    public double getz0PolePrvek(int a) {
        double pom = (double) z0Pole.toArray()[a];
        return pom;
    }


    /**
     * metoda zjistujici velikost pole x0Pole
     * 
     * @return vraci velikost pole
     */
    public int velikostPole() {
        return y0Pole.size();
    }


    /**
     * kontroluje jestli strela dopadla v mape nebo mimo
     * 
     * @return vraci true pokud dopadla v mape
     */

    public boolean kontrolaDopaduVMape() {

        // zjisteni jestli strela opustila mapu nebo ne
        if (getX0() > 0 &&
                getY0() > 0 &&
                getX0() < matice.getSirka() &&
                getY0() < matice.getVyska()) {
            return true;
        }

        return false;

    }


    /**
     * metoda kontroluje zasah na polomeru zasahu
     * 
     * @return vraci false pokud nezasahl jinak true
     */

    public boolean vzOdDopadu() {
        bod.nastavVse(matice.getTargetX(), matice.getTargetY(), getX0(), getY0());

        if (bod.vzdalenost() < velikostDopadu) {
            return true;
        }
        return false;
    }


    public void tiskPole() {
        x0Pole.stream()
            .forEach(x0Pole -> System.out.println(x0Pole));
    }

}
