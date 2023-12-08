import java.util.*;
import java.io.*;



public class IRoadTrip {
    private File file1;
    private File file2;
    private File file3;
    // initialize hahmap called borders
    public HashMap<String, String> borders = new HashMap<>();

    public IRoadTrip(String[] args) {
        for (String arg : args) {
            File file = new File(arg);
            if (file.getName().equals("borders.txt")) {
                file1 = file;
            } else if (file.getName().equals("capidist.csv")) {
                file2 = file;
            } else {
                file3 = file;
            }
        }
    }
//reads the borders file 
    public String readBorders() {
        StringBuilder dataBuilder = new StringBuilder();

        File[] files = { file1, file2, file3 };
        boolean found = false;

        for (File file : files) {
            if (file != null && file.getName().equals("borders.txt")) {
                try {
                    Scanner reader = new Scanner(file);
                    while (reader.hasNextLine()) {
                        dataBuilder.append(reader.nextLine()).append("\n");
                    }
                    reader.close();
                    found = true;
                    break;
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }

        if (!found) {
            System.out.println("No file named 'borders.txt' found.");
        }

        return dataBuilder.toString();
    }
//reads the capidist file
    public String readCapidist() {
        StringBuilder dataBuilderCapidist = new StringBuilder();

        File[] files = { file1, file2, file3 };
        boolean found = false;

        for (File file : files) {
            if (file != null && file.getName().equals("capidist.csv")) {
                try {
                    Scanner reader = new Scanner(file);
                    while (reader.hasNextLine()) {
                        dataBuilderCapidist.append(reader.nextLine()).append("\n");

                    }
                    reader.close();
                    found = true;
                    break;
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }

        if (!found) {
            System.out.println("No file named" + " capidst.csv " + "found.");
        }

        return dataBuilderCapidist.toString();
    }
//reads the state name file 
    public String readStateName() {
        StringBuilder dataBuilderStateName = new StringBuilder();

        File[] files = { file1, file2, file3 };
        boolean found = false;

        for (File file : files) {
            if (file != null && file.getName().equals("state_name.tsv")) {
                try {
                    Scanner reader = new Scanner(file);
                    while (reader.hasNextLine()) {
                        dataBuilderStateName.append(reader.nextLine()).append("\n");

                    }
                    reader.close();
                    found = true;
                    break;
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }

        if (!found) {
            System.out.println("No file named" + " state_name.tsv " + "found.");
        }

        return dataBuilderStateName.toString();
    }

    //parses border lengths to numbers 
    private int parseBorderLength(String borderLengthStr) {
        return Integer.parseInt(borderLengthStr.replaceAll("[^0-9]", ""));
    }
    //adds data from state to hashmap and returns data

    private Map<String, String> associate() {
        String dataState = this.readStateName();
        if (!dataState.isEmpty()) {
            // Create a map to store country, its bordering countries, and their lengths
            String[] lines = dataState.split("\n");
            Map<String, String> countryToID = new HashMap<>();

            for (String line : lines) {
                if (line.contains("2020")) {
                    String[] splitLine = line.split("\t"); // Split by tab or spaces

                    // Assuming the identifier is in the second column and the country name is in
                    // the third column
                    if (splitLine.length >= 3) {
                        String countryID = splitLine[1];
                        String countryName = splitLine[2];

                        countryToID.put(countryName, countryID);
                    }
                }
            }
           
            return countryToID;
        }
        return new HashMap<>(); // Return an empty map if dataState is empty
    }

//adds data from capidist and extracts data 
    private Map<String, String> extractCountryDistances() {
        Map<String, String> IDtoDistancekm = new HashMap<>(); // Declare the map outside the if block
        String data = this.readCapidist();
        if (!data.isEmpty()) {
            // Process data and populate the map
            String[] lines = data.split("\n");
            for (int i = 0; i < lines.length; i++) {
                // Skip the header row
                if (i == 0) {
                    continue;
                }

                // Split the line by commas
                String[] splitCommas = lines[i].split(",");
                String countryID1 = splitCommas[1].trim();
                String countryID2 = splitCommas[3].trim();
                String kmDist = splitCommas[4].trim();

                // Store country IDs and their distances
                IDtoDistancekm.put(countryID1 + "-" + countryID2, kmDist);
            }
        }

        
        
        return IDtoDistancekm;
    }

    //hasmap for broders extract data 
    public Map<String, Map<String, Integer>> getBorders() {
        String data = this.readBorders();
        Map<String, Map<String, Integer>> borders = new LinkedHashMap<>();

        if (!data.isEmpty()) {
            String[] lines = data.split("\n");
            for (String line : lines) {
                String[] splitLineAtEquals = line.split("=");

                if (splitLineAtEquals.length > 1) {
                    String country = splitLineAtEquals[0].trim();
                    String[] splitLineAtColons = splitLineAtEquals[1].split(";");

                    Map<String, Integer> borderingCountries = new HashMap<>();
                    int totalBorderLength = 0;

                    for (String border : splitLineAtColons) {
                        String[] borderComponents = border.trim().split("\\s+", 2);
                        if (borderComponents.length > 1 && !borderComponents[0].isEmpty()) {
                            String borderCountry = borderComponents[0];
                            int borderLength = parseBorderLength(borderComponents[1]);
                            borderingCountries.put(borderCountry, borderLength);
                            totalBorderLength += borderLength;
                        }
                    }

                    borderingCountries.put("Total Border Length", totalBorderLength);
                    borders.put(country, borderingCountries);
                }
            }
        }
        System.out.println("these are the borders " + borders);
        return borders;
    }
//gets teh distancce of the shortest path 
    public int getDistance(String country1, String country2) {
        // Get necessary data
        Map<String, String> IDtoDistancekm = extractCountryDistances();
        Map<String, Map<String, Integer>> borders = getBorders();
        Map<String, String> state = associate();
        
        // Check if the given countries exist
        if (!state.containsKey(country1) || !state.containsKey(country2)) {
            return -1; // Return -1 if either country doesn't exist
        }
        
        // Retrieve capitals for the given countries
        String capital1 = state.get(country1);
        String capital2 = state.get(country2);
        
        // Check if countries share a land border
        if (!borders.containsKey(country1) || !borders.get(country1).containsKey(country2)) {
            return -1; // Return -1 if countries don't share a land border
        }
        
        // Use Dijkstra's or any shortest path algorithm to calculate distance between capitals
        int shortestDistance = calculateShortestPathDistance(capital1, capital2, IDtoDistancekm);
        System.out.println(shortestDistance);
        
        return shortestDistance;
    }
    // Method to calculate shortest path distance between capitals
    private int calculateShortestPathDistance(String capital1, String capital2, Map<String, String> IDtoDistancekm) {
        Map<String, Integer> distances = new HashMap<>();
        Set<String> visited = new HashSet<>();
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(node -> node.distance));
        
        // Initialize distances to infinity except for the starting capital
        for (String capital : IDtoDistancekm.keySet()) {
            distances.put(capital, capital.equals(capital1) ? 0 : Integer.MAX_VALUE);
        }
        
        queue.add(new Node(capital1, 0));
        
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            
            if (visited.contains(current.country)) {
                continue;
            }
            visited.add(current.country);
            
            if (current.country.equals(capital2)) {
                return distances.get(current.country);
            }
            
            for (Map.Entry<String, String> entry : IDtoDistancekm.entrySet()) {
                String[] countries = entry.getKey().split("-");
                String country1 = countries[0];
                String country2 = countries[1];
                int distance = Integer.parseInt(entry.getValue());
                
                if (country1.equals(current.country)) {
                    if (distances.get(country2) > distances.get(country1) + distance) {
                        distances.put(country2, distances.get(country1) + distance);
                        queue.add(new Node(country2, distances.get(country2)));
                    }
                } else if (country2.equals(current.country)) {
                    if (distances.get(country1) > distances.get(country2) + distance) {
                        distances.put(country1, distances.get(country2) + distance);
                        queue.add(new Node(country1, distances.get(country1)));
                    }
                }
            }
        }
        
        return -1; // Return -1 if no path found
    }
    
    // Node class for Dijkstra's algorithm
    private static class Node {
        String country;
        int distance;
    
        Node(String country, int distance) {
            this.country = country;
            this.distance = distance;
        }
    }
    
    
    //finds path 
    public List<String> findPath(String country1, String country2) {
        Map<String, String> IDtoDistancekm = extractCountryDistances();
        Map<String, Map<String, Integer>> borders = getBorders();
        Map<String, String> state = associate();
    
        List<String> path = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        Queue<NodeWithPath> queue = new LinkedList<>();
        System.out.println("these are the borders" + borders);
    
        // Ensure both countries exist and have borders
        if (!borders.containsKey(country1) || !borders.containsKey(country2)) {
            System.out.println("One or both countries are not found in the dataset.");
            return path; // Return empty list as no path can be found
        }
    
        queue.add(new NodeWithPath(country1, country1));
    
        while (!queue.isEmpty()) {
            NodeWithPath current = queue.poll();
    
            if (visited.contains(current.country)) {
                continue;
            }
            visited.add(current.country);
    
            if (current.country.equals(country2)) {
                // Reconstruct the path
                String currentCountry = current.country;
                while (!currentCountry.equals(country1)) {
                    path.add(currentCountry + " --> " + current.parentCountry + " (" + IDtoDistancekm.get(current.parentCountry + "-" + currentCountry) + " km)");
                    currentCountry = current.parentCountry;
                }
                Collections.reverse(path);
                return path;
            }
    
            if (borders.containsKey(current.country)) {
                for (String neighbor : borders.get(current.country).keySet()) {
                    if (!visited.contains(neighbor)) {
                        queue.add(new NodeWithPath(neighbor, current.country));
                    }
                }
            }
        }
    
        // No path found
        return path;
    }
    
    
    private static class NodeWithPath {
        String country;
        String parentCountry;
    
        NodeWithPath(String country, String parentCountry) {
            this.country = country;
            this.parentCountry = parentCountry;
        }
    }
    
    
//accepts user input and checks if counties/capitals are vali

    public  void acceptUserInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to IRoadTrip!");
    
        while (true) {
            System.out.println("Enter the name of the first country (type EXIT to quit):");
           
            String country1 = scanner.nextLine().trim();
    
            if (country1.equalsIgnoreCase("EXIT")) {
                System.out.println("Exiting the program...");
                break;
            }
    
            System.out.println("Enter the name of the second country (type EXIT to quit):");
            String country2 = scanner.nextLine().trim();
    
            if (country2.equalsIgnoreCase("EXIT")) {
                System.out.println("Exiting the program...");
                break;
            }
    
            // Check if the entered countries exist
            Map<String, String> state = associate();
    
            if (!state.containsKey(country1) || !state.containsKey(country2)) {
                System.out.println("One or both of the entered countries do not exist in the dataset. Please try again.");
                continue; // Ask for input again
            }
    
            // If the countries exist, proceed to find the route
            List<String> route = findPath(country1, country2);
            
            if (!route.isEmpty()) {
                System.out.println("Route from " + country1 + " to " + country2 + ":");
                for (String step : route) {
                    System.out.println("* " + step);
                }
            } else {
                System.out.println("No route found between " + country1 + " and " + country2);
            }
        }
        // This is outside the loop, so it will only be reached after the user decides to EXIT
        System.out.println("Exiting the program...");
    }
    
    
    
 public static void main(String[] args) throws IOException {
    IRoadTrip a3 = new IRoadTrip(args);
  
    a3.acceptUserInput();
}
}
