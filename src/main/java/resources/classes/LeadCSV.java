//package resources.classes;
//
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.List;
//
//import au.com.bytecode.opencsv.CSVWriter;
//
//public class LeadCSV
//{
//
//    public static String create(List<Lead> leads){
//        return create(leads,',');
//    }
//
//    public static String create(List<Lead> leads, char separator){
//
//        CSVWriter writer = null;
//        String res = "";
//
//        try
//        {
//            File temp = File.createTempFile("lead_import_",".csv");
//            writer = new CSVWriter(new FileWriter(temp), separator);
//            // feed in your array (or convert your data to an array)
//            String[] entries = {
//                    "First Name",
//                    "Last Name",
//                    "Phone",
//                    "Cell Phone",
//                    "Email Address",
//                    "User Name",
//                    "Street",
//                    "City",
//                    "State",
//                    "Zip Code",
//                    "Price Low",
//                    "Price High",
//                    "Bedrooms",
//                    "Date",
//                    "Admin ID",
//                    "Site ID",
//                    "Notes",
//                    "Buyer Transaction Goals",
//                    "Seller Transaction Goals"
//            };
//            writer.writeNext(entries);
//            for(Lead lead: leads){
//                writer.writeNext(lead.toCSVLine());
//            }
//            writer.close();
//            res = temp.getAbsolutePath();
//
//        }
//        catch (IOException e)
//        {
//            System.out.println(e.getMessage());
//        }
//        return res;
//    }
//
//}
