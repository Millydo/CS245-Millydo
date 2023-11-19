import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.HashMap;
import java.util.Map;



public class ProtectedFile {
    /** This code uses the protected class to grab values from a file and to grab the key and value
     * and add it to hashmap it then uses varaious methods to set the key values 
     * @return duration, passengers, floors,elevators,capacity,structure,passengers
     * 
     */


   String myKey = "";
   String value = "";
   int duration = 0;
   int floors = 0;
   int elevatorCapacity = 0;
   int elevators = 0;
   double passengers = 0.0;
   String theStructure = "";


   String propertyFileName = "/Users/milly/documents/cs245milly/Assingment2/TestFile.txt";


   Map<String, String> propertiesMap = new HashMap<>(); // Use HashMap to store properties


   public void fileExist(boolean exists) {
       Properties prop = new Properties();
       try {
           prop.load(new FileReader(propertyFileName));
           // Load properties from the file into the HashMap
           for (String key : prop.stringPropertyNames()) {
               value = prop.getProperty(key);
               propertiesMap.put(key, value);
            System.out.println("key" +  key +  "value " + value );
              
               // Use key and value as needed
           }
          


           // set values
           duration = Integer.parseInt(propertiesMap.getOrDefault("duration", "0"));
           passengers = Double.parseDouble(propertiesMap.getOrDefault("passengers", "0.0"));
           floors = Integer.parseInt(propertiesMap.getOrDefault("floors", "0"));
           elevatorCapacity = Integer.parseInt(propertiesMap.getOrDefault("elevatorCapacity", "0"));
           elevators = Integer.parseInt(propertiesMap.getOrDefault("elevators", "0"));
           theStructure = propertiesMap.getOrDefault("structures", "");


       } catch (IOException e) {
           e.printStackTrace();
       }
   }


   public void fileNotExist(boolean doesNotexist) {
       // If no property file is provided, set default values
       Properties defaultProp = new Properties();
       defaultProp.setProperty("structures", "linked");
       defaultProp.setProperty("floors", "32");
       defaultProp.setProperty("passengers", "0.03");
       defaultProp.setProperty("elevators", "1");
       defaultProp.setProperty("elevatorCapacity", "10");
       defaultProp.setProperty("duration", "500");


       // Load default properties into the HashMap
       for (String key : defaultProp.stringPropertyNames()) {
           value = defaultProp.getProperty(key);
           propertiesMap.put(key, value);
          
           // Use key and value as needed for defaults
       }
       System.out.println("file name " + propertyFileName);
       duration = Integer.parseInt(propertiesMap.getOrDefault("duration", "0"));
       passengers = Double.parseDouble(propertiesMap.getOrDefault("passengers", "0.0"));
       floors = Integer.parseInt(propertiesMap.getOrDefault("floors", "0"));
       elevatorCapacity = Integer.parseInt(propertiesMap.getOrDefault("elevatorCapacity", "0"));
       elevators = Integer.parseInt(propertiesMap.getOrDefault("elevators", "0"));
       theStructure = propertiesMap.getOrDefault("structures", "linked");
   }


   public int getDuration() {
       return duration;
   }


   public int getFloors() {

       return floors;
   }


   public double getPassengers() {
       return passengers;
   }


   public int getElevatorCapacity() {
       return elevatorCapacity;
   }


   public int getElevators() {
    
       return elevators;
   }


   public String getTheStructure() {
       return theStructure;
   }


}
