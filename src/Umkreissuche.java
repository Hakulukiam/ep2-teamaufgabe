/**
 * Autors:
 * Mold Florian
 * Ruckenbauer Markus
 */

import java.util.Scanner;
import java.util.regex.Pattern;

public class Umkreissuche {

    /**
     * Default Constructor
     * @param data Datastructure to use
     */
    public Umkreissuche(DataStructure data){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello World! What would you like to know?");
        while(true) {
            String command;
            command = scanner.nextLine();
            if(command.equals("exit"))break;
            if(!validCommand(command)){
                System.err.println("I don't know this command...");
            }else{
                String[] params = decodeCommand(command);
                switch (params[0]){
                    case "Junctions":
                        int[] inRange = data.inRange(new double[]{Double.valueOf(params[6].split("=")[1]),Double.valueOf(params[7].split("=")[1])},Double.valueOf(params[3]));
                        System.out.println("Airports: "+inRange[0]+" Trainstations: "+inRange[1]);
                        break;
                    case "Airports":
                        System.out.println(data.AwTinRange(Double.valueOf(params[8]),Integer.valueOf(params[4])));
                        break;
                }
            }
        }
        System.out.print("bye!");
    }

    /**
     * Test Constructor DO NOT USE
     * @param data Datastructure
     * @param test 0 -> Junctions, 1 -> Airports
     */
    public Umkreissuche(DataStructure data, int test){
        String[] params;
        switch (test){
            case 0:
                params = decodeCommand("Junctions less than 100.0 units from x=1818.54657 y=5813.29982");
                data.inRange(new double[]{Double.valueOf(params[6].split("=")[1]),Double.valueOf(params[7].split("=")[1])},Double.valueOf(params[3]));
                break;
            case 1:
                params = decodeCommand("Airports with at least 5 Trainstations less than 1.0 units away");
                data.AwTinRange(Double.valueOf(params[8]),Integer.valueOf(params[4]));
                break;
        }
    }

    private Boolean validCommand(String command){
        Pattern valid1, valid2;
        valid1 = Pattern.compile("^Junctions less than [0-9]+[.]?[0-9]* units from x=[0-9]+[.]?[0-9]* y=[0-9]+[.]?[0-9]*$");
        valid2 = Pattern.compile("^Airports with at least [0-9]+ Trainstations less than [0-9]+[.]?[0-9]* units away$");
        return valid1.matcher(command).matches() || valid2.matcher(command).matches();
    }

    private String[] decodeCommand(String command){
        return command.split(" ");
    }
}