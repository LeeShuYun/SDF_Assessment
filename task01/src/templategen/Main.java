package templategen;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/*
 * Generates a bunch of text with replaced strings
 */
public class Main {

    public static void main(String[] args) throws Exception {
        // instructions
        // javac -cp classes templategen.Main <csv> <template>

        // parse commands
        String csvFile = args[0];
        String templateFile = args[1];

        // Processing CSV
        // =====================================================================================
        // create a List of Hashmap with the format {String what to replace:string actual value}
        // eg. {first_name: Harry}

        // prep work opening csv file and loading text
        FileReader fileReader = new FileReader(csvFile);
        BufferedReader br = new BufferedReader(fileReader);

        // grabbing header first
        String header = br.readLine();
        String[] headerLabels = header.trim().split(",");

        //the hashmap we're putting our {first_name: Harry} KV into
        HashMap<String, String> csvMap;

        // contains our replacement name variables
        List<HashMap<String, String>> csvMapList = new LinkedList<>();
        String csvLine;
        String[] csvLineList;
        // creating the list of hashmap of replacements to put into the template
        while ((csvLine = br.readLine()) != null) {
            csvMap = new HashMap<>();
            // read a line
            csvLineList = csvLine.trim().split(",");

            // matching each label with its corresponding value and inserting into hashmap
            for (Integer i = 0; i < headerLabels.length; i++) {
                csvMap.put(headerLabels[i], csvLineList[i]);
            }
            // pop the finished map of replacement into the list for later processing
            csvMapList.add(csvMap);
        }

        // now that we read all the data, we close stream
        br.close();
        fileReader.close();

        // processing the template ====================================================
        // first we read the template file
        String templFilePath = templateFile;
        File tempFileObj = Paths.get(templFilePath).toFile();
        FileReader tempFileReader = new FileReader(tempFileObj);
        BufferedReader bufferedreader = new BufferedReader(tempFileReader);

        // prep work
        String templateOutput;
        String completedTemplate = "";

        // generating the complete template String first
        while ((templateOutput = bufferedreader.readLine()) != null) {
            completedTemplate = completedTemplate + templateOutput;
            completedTemplate += "\n";
        }
        bufferedreader.close();
        tempFileReader.close();

        // searching thru the completed template string to find words to replace
        String finalMail = completedTemplate;
        // for each set of replacement, we process and print
        for (HashMap<String, String> replacementWordsMap : csvMapList) {
            // for each replacement word we look through the single template String
            for (String replacementWord : headerLabels) { 
                // remove the <<>> and replace the variable with the correct one value
                finalMail = finalMail.replace("<<" + replacementWord + ">>", replacementWordsMap.get(replacementWord));

            }
            //replacing the escaped \n character with line break
            finalMail = finalMail.replace("\\n", "\n");

            // now that all replacements is complete for one line of csv, we print it out
            System.out.println(finalMail);

            // reset the current instance of mail for the next mail
            finalMail = completedTemplate;

        }

    }
}
