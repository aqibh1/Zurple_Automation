package resources.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HotAlertsFlags
{

    private List<String> flagsList=new ArrayList<String>();

    public List<String> getFlagsList()
    {
        return flagsList;
    }

    public void addFlag(String flag)
    {
        this.flagsList.add(flag);
    }

}
