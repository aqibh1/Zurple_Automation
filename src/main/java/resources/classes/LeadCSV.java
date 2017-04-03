package resources.classes;

import au.com.bytecode.opencsv.CSVWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LeadCSV
{

    public static String create(){

        CSVWriter writer = null;
        String res = "";

        try
        {
            File temp = File.createTempFile("lead_import_",".csv");
            writer = new CSVWriter(new FileWriter(temp), '\t');
            // feed in your array (or convert your data to an array)
            String[] entries = "first#second#third".split("#");
            writer.writeNext(entries);
            writer.close();
            res = temp.getAbsolutePath();
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
        return res;
    }

}
