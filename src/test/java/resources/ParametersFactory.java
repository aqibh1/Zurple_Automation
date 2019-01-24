package resources;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import resources.data.z57.SearchFormData;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ParametersFactory {

    static Iterator searchParamsList = null;
    static Map<Long,HashMap<String,String>> searchParamsForThread = new HashMap<Long,HashMap<String,String>>();
    static Map<Long,File> searchDataForThread = new HashMap<Long,File>();
    static Iterator dataIteratorList = null;

    private static Iterator getSearchParamsList(){

        if ( searchParamsList == null )
        {
            ArrayList<HashMap<String ,String>> paramsList = new ArrayList<HashMap<String ,String>>();

            HashMap<String ,String> action1 = new HashMap<String, String>();
            action1.put("search_by","city");
            action1.put("search_criteria","San Diego, CA");
            action1.put("min_price","425000");
            action1.put("max_price","1500000");
            action1.put("bedrooms","2");
            action1.put("bathrooms","2");
            action1.put("square_feet","1000");
            action1.put("year_built","2005");
            action1.put("lot_sqft","2000");
            action1.put("types","home,condo,land");
            action1.put("features","Air Conditioning");
            action1.put("results_expected","30");

            paramsList.add(action1);

            HashMap<String ,String> action2 = new HashMap<String, String>();
            action2.put("search_by","zip");
            action2.put("search_criteria","92130");
            action2.put("min_price","425000");
            action2.put("max_price","1500000");
            action2.put("bedrooms","1");
            action2.put("bathrooms","1");
            action2.put("square_feet","500");
            action2.put("types","home,condo,land");
            action2.put("features","Air Conditioning");
            action2.put("results_expected","20");

            paramsList.add(action2);

            HashMap<String ,String> action3 = new HashMap<String, String>();
            action3.put("search_by","county");
            action3.put("search_criteria","San Diego");
            action3.put("bedrooms","1");
            action3.put("bathrooms","1");
            action3.put("square_feet","500");
            action3.put("types","home,condo,land");
            action3.put("features","Air Conditioning");
            action3.put("results_expected","20");

            paramsList.add(action3);

            HashMap<String ,String> action4 = new HashMap<String, String>();
            action4.put("search_by","address");
            action4.put("search_criteria","1 Avenue");

            paramsList.add(action4);

            HashMap<String ,String> action5 = new HashMap<String, String>();
            action5.put("search_by","mls");
            action5.put("search_criteria","160040634");

            paramsList.add(action5);

            HashMap<String ,String> action6 = new HashMap<String, String>();
            action6.put("search_by","neighborhood");
            action6.put("search_criteria","Carmel Valley");
            action6.put("bedrooms","1");
            action6.put("bathrooms","1");
            action6.put("square_feet","500");
            action6.put("types","home,condo,land");
            action6.put("features","Air Conditioning");
            action6.put("results_expected","5");

            paramsList.add(action6);

            HashMap<String ,String> action7 = new HashMap<String, String>();
            action7.put("search_by","school");
            action7.put("search_criteria","San Diego Unified");
            action7.put("bedrooms","1");
            action7.put("bathrooms","1");
            action7.put("square_feet","500");
            action7.put("types","home,condo,land");
            action7.put("features","Air Conditioning");
            action7.put("results_expected","5");

            paramsList.add(action7);

            searchParamsList =paramsList.iterator();
        }

        return searchParamsList;

    }

    public static HashMap<String,String> getSearchParameters(long thread_id){

        if(  searchParamsForThread.containsKey(thread_id) )
        {
            return searchParamsForThread.get(thread_id);
        }
        else
        {

            if (getSearchParamsList().hasNext()){
                HashMap<String,String> param = (HashMap<String, String>) getSearchParamsList().next();
                searchParamsForThread.put(thread_id, param);
                return param;
            }
            else
            {
                return null;
            }

        }

    }

    public static void removeSearchParameters(long thread_id)
    {
        if(  searchParamsForThread.containsKey(thread_id) )
        {
            searchParamsForThread.remove(thread_id);
        }
    }
    
    public static File getSearchFormData(long thread_id,String pFolder) {
    	if(searchDataForThread.containsKey(thread_id)) {
    		return searchDataForThread.get(thread_id);
    	}else {
    		if(getDataFilesList(pFolder).hasNext()) {
    			File data_files= (File)getDataFilesList(pFolder).next();
    			searchDataForThread.put(thread_id, data_files);
    			return data_files;
    		}else {
    			return null;
    		} 		
    	}
    }
    
    public static void removeDataFiles(long thread_id)
    {
        if(  searchDataForThread.containsKey(thread_id) )
        {
        	searchDataForThread.remove(thread_id);
        }
    }
    
    private static Iterator getDataFilesList(String pFolder) {
    	
    	if(dataIteratorList==null) {
    		ArrayList<File> list_of_File = new ArrayList<File>();
    		File data_folder = new File(pFolder);
    		File[] listOfFiles = data_folder.listFiles();

    		for (File file : listOfFiles) {
    		    if (file.isFile()) {
    		        System.out.println(file.getName());
    		        list_of_File.add(file);
    		    }
    		}
    		dataIteratorList=list_of_File.iterator();
    	}
    	
    	return dataIteratorList;
    }
}
