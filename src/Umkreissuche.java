/**
 * Autors:
 * Mold Florian
 * Ruckenbauer Markus
 */

import java.util.Scanner;
import java.util.regex.Pattern;

public class Umkreissuche {

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
                        System.out.println(data.AwTinRange(15.0,20));
                        break;
                }
            }
        }
        System.out.print("bye!");
    }

    private static Boolean validCommand(String command){
        Pattern valid1, valid2;
        valid1 = Pattern.compile("^Junctions less than [0-9]+[.]?[0-9]* units from x=[0-9]+[.]?[0-9]* y=[0-9]+[.]?[0-9]*$");
        valid2 = Pattern.compile("^Airports with at least [0-9]+ Trainstations less than [0-9]+[.]?[0-9]* units away$");
        return valid1.matcher(command).matches() || valid2.matcher(command).matches();
    }

    private static String[] decodeCommand(String command){
        //Junctions less than 100.0 units from x=1818.54657 y=5813.29982
        //Airports with at least 5 Trainstations less than 1.0 units away
        return command.split(" ");
    }
}