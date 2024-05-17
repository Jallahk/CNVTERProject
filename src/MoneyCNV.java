import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.io.FileWriter;

public class MoneyCNV {
 //assigning conversion rates
   private static final double us_euro = .93;
    private static final double us_cad = 1.37;
    private static final double us_peso = 16.81;
    private static final double euro_peso = 18.11;
    private static final double euro_cad = 1.47;
    private static final double peso_cad = .081;

//create mapping for the currency conversion
    private static final Map<Integer, Map<Integer, Double>> ConvRates = new HashMap<>();
    static{
     Map<Integer,Double> UsRates = new HashMap<>();
     UsRates.put(4, us_euro);
     UsRates.put(2, us_cad);
     UsRates.put(3, us_peso);

     Map<Integer, Double>EuroRates = new HashMap<>();
     EuroRates.put(1,1/us_euro);
     EuroRates.put(2, euro_cad);
     EuroRates.put(3, euro_peso);

     Map<Integer,Double> PesoRates = new HashMap<>();
     PesoRates.put(1, 1/us_peso);
     PesoRates.put(4, 1/euro_peso);
     PesoRates.put(2, peso_cad);

     Map<Integer,Double> CadRates = new HashMap<>();
     CadRates.put(1, 1/us_cad);
     CadRates.put(4, 1/euro_cad);
     CadRates.put(3, 1/peso_cad);

     ConvRates.put(1,UsRates);
     ConvRates.put(4, EuroRates);
     ConvRates.put(3,PesoRates);
     ConvRates.put(2,CadRates);

    }
    public double convert(double amount, int Convfrom, int ConvTo){
     //checks to see if the input are keys within the mapping
     if (!ConvRates.containsKey(Convfrom) || !ConvRates.get(Convfrom).containsKey(ConvTo)) {
      throw new IllegalArgumentException("Conversion not supported");
     }

//calculates the conversion
     return amount * ConvRates.get(Convfrom).get(ConvTo);

    }

//this function will be able to write the conversion to a file
    public void WriteToFile(double amount, int FromConv, int toConv){
        try(FileWriter writer = new FileWriter("Conversion.txt")){
            double ConversionAns = convert(amount,FromConv,toConv);
            String ConversionFIle = String.format("%.2f %s = %.2f %s%n", amount, FromConv, ConversionAns, toConv);
            writer.write(ConversionFIle);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }



}
