import java.io.*;
import java.util.*;
import static java.lang.Double.parseDouble;

public class World {
    ArrayList<Aeroport> list = new ArrayList<Aeroport>();

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        World w1 = new World("./data/airport-codes_no_comma.csv");

        System.out.println(w1.findByCode("CDG"));
        System.out.println(w1.findNearest(2.1,48.34));
    }

    public World (String fileName){
        try{
            BufferedReader buf = new BufferedReader(new FileReader(fileName));
            String s = buf.readLine();
            while(s!=null){
                s=s.replaceAll("\"","");//Enleve les guillemets qui s´eparent les champs de donn´ees GPS.
                String fields[]=s.split(",");// Une bonne idee : placer un point d'arret ici pour du debuggage.
                if (fields[1].equals("large_airport")){
                    list.add(new Aeroport(fields[9], fields[2], fields[5], parseDouble(fields[12]), parseDouble(fields[11])));
                }
                s = buf.readLine();
            }
        }
        catch (Exception e){
            System.out.println("Maybe the file isn't there ?");
            //	System.out.println(list.get(list.size()-1));
            e.printStackTrace();
        }
    }
    public ArrayList<Aeroport> getList(){
        return list;
    }

    public Aeroport findByCode (String code) {
        for (Aeroport a : list) {
            if (a.getIATA().equals(code)) return a;
        }
        return null;

    }

    public Aeroport findNearest(double longitude, double latitude)
    {
        Aeroport minimumAeroport=list.get(0);
        double minimum=list.get(0).distance(longitude,latitude);
        for(Aeroport a : list){
            double minATester = a.distance(longitude,latitude);
            if (minATester<minimum){
                minimum=minATester;
                minimumAeroport=a;
            }
        }
        return minimumAeroport;
    }
}