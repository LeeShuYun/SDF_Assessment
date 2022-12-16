package task01.src;

import java.io.BufferedReader;
import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.regex.*;

/**
 * Generates a bunch of text with replaced strings
 */
public class Main {

    public static void main(String[] args) {
        // instructions
        System.out.println(">>> javac -sourcepath src -d classes src/Main.java <csv> <template>");
        
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
        
        //contains our replacement name variables
        HashMap<String, String> csvMap = new HashMap<>();
        List<HashMap<String, String>> csvMapList = new LinkedList<>();

        //grabbing header
        String header = br.readLine(); 
        System.out.println("TEST>>>" + header);
        String[] headerLabels = header.trim().split(",");
            
        //creating the list of hashmap of replacements to put into the template
        while ((csvLine = br.readLine()) != null){
            //read a line
            String[] csvLineList = br.readLine().trim().split(",");
            
            //matching each label with its corresponding value and inserting into hashmap
            for (Integer i = 0; i < headerLabels.length; i++) {
                csvMap.put(headerLabels[i], csvLineList[i]);
            }
            //pop the finished set of replacement into the list
            csvMapList.add(csvMap);
        }

        //now that we read all the data, we close stream
        br.close();
        fileReader.close();

        //processing the template ====================================================
        //first we read the template file
        String templFilePath = templateFile;
        File tempFileObj = Paths.get(templFilePath).toFile();
        FileReader tempFileReader = new FileReader(tempFileObj);
        BufferedReader bufferedreader = new BufferedReader(tempFileReader);

        //prep work
        String templateOutput;

        //generating the complete template String first
        String completedTemplate; 
        while ((templateOutput = br.readLine()) != null){
            completedTemplate += br.readLine();
            completedTemplate += "\n";
        }

        //searching thru the completed template string to find words to replace
        for (HashMap<String, String> replacementWordsMap : csvMapList) {//for each set of replacement, we process and print
            for (String replacementWord : headerLabels) {  //for each replacement word we look through the String
                //check if the list has <<replacement>>
                if (completedTemplate.contains("<<" + replacementWord + ">>")){
                    // remove the <<>> and replace the variable with the correct one value
                    completedTemplate.replaceAll("<<" + replacementWord + ">>", replacementWordsMap.get(replacementWord));
                }
            } 
            //now that the set of replacements is complete, we print it out
            System.out.println(completedTemplate);

        }

    // }catch(FileNotFoundException e)
    // {
    // System.out.println("File not found." + e.getMessage());
    // e.printStackTrace();
    // }catch(IOException e)
    // {
    // e.printStackTrace();
    // }
}
