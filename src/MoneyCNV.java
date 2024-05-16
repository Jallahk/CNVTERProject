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
    private static final Map<String, Map<String, Double>> ConvRates = new HashMap<>();
    static{
     Map<String,Double> UsRates = new HashMap<>();
     UsRates.put("euro", us_euro);
     UsRates.put("cad", us_cad);
     UsRates.put("peso", us_peso);

     Map<String, Double>EuroRates = new HashMap<>();
     EuroRates.put("us",1/us_euro);
     EuroRates.put("cad", euro_cad);
     EuroRates.put("peso", euro_peso);

     Map<String,Double> PesoRates = new HashMap<>();
     PesoRates.put("us", 1/us_peso);
     PesoRates.put("euro", 1/euro_peso);
     PesoRates.put("cad", peso_cad);

     Map<String,Double> CadRates = new HashMap<>();
     CadRates.put("us", 1/us_cad);
     CadRates.put("euro", 1/euro_cad);
     CadRates.put("peso", 1/peso_cad);

     ConvRates.put("us",UsRates);
     ConvRates.put("euro", EuroRates);
     ConvRates.put("peso",PesoRates);
     ConvRates.put("cad",CadRates);

    }
    public double convert(double amount, String Convfrom, String ConvTo){
     //checks to see if the input are keys within the mapping
     if (!ConvRates.containsKey(Convfrom) || !ConvRates.get(Convfrom).containsKey(ConvTo)) {
      throw new IllegalArgumentException("Conversion not supported");
     }
//converts the Convfrom and the ConvTo to us
     double fromToUsRate = ConvRates.get(Convfrom).get("us");
     double usToToRate = ConvRates.get(ConvTo).get("us");
//calculates the conversion
     return amount * fromToUsRate * usToToRate;

    }

//this function will be able to write the conversion to a file
    public void WriteToFile(double amount, String FromConv, String toConv){
        try(FileWriter writer = new FileWriter("Conversion.txt")){
            double ConversionAns = convert(amount,FromConv,toConv);
            String ConversionFIle = String.format("%.2f %s = %.2f %s%n", amount, FromConv, ConversionAns, toConv);
            writer.write(ConversionFIle);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }



}
