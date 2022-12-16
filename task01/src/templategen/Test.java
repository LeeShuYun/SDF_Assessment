package templategen;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Test {
    

    public static void main(String[] args) throws Exception{
        //parse commands       
        String csvFile = args[0];
        String templateFile = args[1];

        // Processing CSV =====================================================================================
        // create a List of Hashmap with the format {String what to replace:string actual value}
        // eg. {first_name: Harry}

        //prep work opening csv file and loading text
        String csvfilePath = csvFile;
        File fileObj = Paths.get(csvfilePath).toFile();
        FileReader fileReader = new FileReader(fileObj);
        BufferedReader br = new BufferedReader(fileReader);
        
        String csvLine;
        while ((csvLine = br.readLine()) != null){
            csvLine = br.readLine();
            System.out.println(csvLine);
        }

    }

}
