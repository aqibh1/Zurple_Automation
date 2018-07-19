package com.zurple.my.admin;

import com.zurple.my.Admin.LeadImportPage;
import com.zurple.my.Admin.LeadStatusAutomationPage;
import com.zurple.my.PageTest;
import java.awt.AWTException;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.testng.annotations.Test;
import resources.classes.Lead;
import resources.classes.LeadCSV;
import resources.orm.hibernate.models.Admin;
import resources.orm.hibernate.models.Import;
import resources.orm.hibernate.models.Package;
import resources.orm.hibernate.models.Site;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LeadStatusAutomationPageTest
        extends PageTest
{
    private static LeadStatusAutomationPage page;

    public LeadStatusAutomationPage getPage(){
        if(page == null){
            page = new LeadStatusAutomationPage();
            page.setUrl("https://my.dev.zurple.com/admin/processleadsstatuses");
            page.setDriver(getDriver());
        }
        return page;
    }

    public void clearPage(){
        page=null;
    };
    
    @Test
    public void testLeadStatusAutomationProcessing()
    {
        assertTrue(getPage().checkLeadsStatusAutomationProcessingButtonExists());
        assertTrue(getPage().runLeadStatusAutomationProcessing());
    }
}
