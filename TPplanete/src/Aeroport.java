public class Aeroport {

    private final String IATA;
    private final String Name;
    private final String country;
    private final double latitude;
    private final double longitude;

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Aeroport a1 = new Aeroport("CY", "Cergy", "France", 10.0, 11.0);

        System.out.println(a1);
    }


    public Aeroport(String iATA, String name, String country, double latitude, double longitude) {
        this.IATA = iATA;
        this.Name = name;
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getIATA() {
        return IATA;
    }

    public double getLatitude() { return latitude; }

    public double getLongitude() {
        return longitude;
    }

    @Override
    public String toString() {
        return "Aeroport [IATA=" + IATA + ", Name=" + Name + ", country=" + country + ", latitude=" + latitude
                + ", longitude=" + longitude + "]";
    }


    public double distance(double longitude, double latitude) {
        // TODO Auto-generated method stub
        double norme = Math.pow((latitude - this.latitude),2)+Math.pow((longitude - this.longitude)*Math.cos(Math.toRadians((latitude + this.latitude)/2)),2);

        return norme;
    }
}
