package my.zcu.upg;

public class Bod {

    private double x1;
    private double y1;
    private double x2;
    private double y2;
    private double vysledek;


    public void nastavVse(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }


    public void setX1(double x1) {
        this.x1 = x1;
    }


    public void setY1(double y1) {
        this.y1 = y1;
    }


    public void setX2(double x2) {
        this.x2 = x2;
    }


    public void setY2(double y2) {
        this.y2 = y2;
    }


    public double getX1() {
        return x1;
    }


    public double getY1() {
        return y1;
    }


    public double getX2() {
        return x2;
    }


    public double getY2() {
        return y2;
    }


    /**
     * spocita vzdusnou vzdalenost dvou bodu
     * 
     * @return vraci vysledek jako hodnotu double
     */
    public double vzdalenost() {

        vysledek = Math.sqrt(((getX2() - getX1()) * (getX2() - getX1())) +
                ((getY2() - getY1()) * (getY2() - getY1())));
        return (vysledek);
    }


    /**
     * vypise do konzole vzdalenost strelce a cile
     */
    public void vysledek() {

        System.out.println("Vzdalenost dvou strelcu je (zaokrouhlene): " + Math.round(vzdalenost()) + " m");

    }

}
