
import java.io.File;


public class Elevator {
    /*Main class feeds values to classed  from protected file checks if the file 
    exists and prints the average times of conveyance and arrival classes Protected File and 
    Elevator Simulation are apart of this implementation please ensure they are together 
     * 
     */
   public static void main(String[] args) {
       boolean fileExists = false;
      if (args.length > 0) {
           File file = new File(args[0]);
           fileExists = file.exists();
          
       }


       ProtectedFile myFile  = new ProtectedFile();
       if (fileExists) {
           myFile.fileExist(fileExists);
       } else {
           myFile.fileNotExist(fileExists);
          
          
        
       }


       // Now you can call getter methods on protectedFile to retrieve properties.
       int duration = myFile.getDuration();
       
       int floors = myFile.getFloors();
       
       int elevators = myFile.getElevators();
       double passengers = myFile.getPassengers();
       


       //Simulate elevator actions using ElevatorSimulation methods here...
      ElevatorSimulation simulation = new  ElevatorSimulation();
    
      simulation.simulateElevatorActions(duration, floors, elevators, passengers);
      simulation.reportSimulationResults();
      
    
     
     
   }

}