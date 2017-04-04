package com.zurple.my.admin;

import com.zurple.my.Admin.LeadImportPage;
import com.zurple.my.PageTest;
import java.awt.AWTException;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
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

public class LeadImportPageTest
        extends PageTest
{
    private static LeadImportPage page;

    public LeadImportPage getPage(){
        if(page == null){
            page = new LeadImportPage();
            page.setUrl("https://my.dev.zurple.com/leadmgr/import");
            page.setDriver(getDriver());
        }
        return page;
    }

    public void clearPage(){
        page=null;
    };

    @Test
    public void testLeadImportFormExists()
    {

        assertTrue(getPage().checkLeadsImportFormExists());
        assertTrue(getPage().getLeadsImportForm().checkElementExistsById("package"));
        assertTrue(getPage().getLeadsImportForm().checkElementExistsById("admins"));
        assertTrue(getPage().getLeadsImportForm().checkElementExistsById("sites"));
        assertTrue(getPage().getLeadsImportForm().checkElementExistsById("pun"));
        assertTrue(getPage().getLeadsImportForm().checkElementExistsById("csv"));

    }

    @Test
    public void testLeadImportUpload()
    {

        assertTrue(getPage().checkLeadsImportFormExists());

        Admin admin = getEnvironment().getAdmin();

        Package pkg = admin.getPackage();
        assertTrue(pkg!=null);

        Set<Site> siteSet =  getEnvironment().getAdmin().getSites();
        assertTrue(siteSet.size()>0);

        Boolean siteFoundFlag = false;
        Site s = new Site();
        for (Iterator<Site> it = siteSet.iterator(); it.hasNext(); ) {
            s = it.next();
            if (s.getDomainName().equals("zengtest1.us"))
                siteFoundFlag=true;
        }
        assertTrue(siteFoundFlag);

        getPage().getLeadsImportForm().setSelectValueByValue("package",pkg.getId().toString());

        waitLoad();

        getPage().getLeadsImportForm().setSelectValueByValue("admins",getEnvironment().getAdmin().getId().toString());

        waitLoad();

        getPage().getLeadsImportForm().setSelectValueByValue("sites",s.getId().toString());

        waitLoad();

        getPage().getLeadsImportForm().setSelectValue("cities",2);

        List<Lead> leads = new ArrayList<Lead>();

        String uuid = UUID.randomUUID().toString();
        //leads.add(Lead.generateLead(getEnvironment().getAdmin().getId(),s.getId(),uuid.substring(0,5),false));

        for(int i=1; i<10; i++){
            uuid = UUID.randomUUID().toString();
            leads.add(Lead.generateLead(getEnvironment().getAdmin().getId(),s.getId(),uuid.substring(0,5),true));
        }


        try
        {
            getPage().getLeadsImportForm().uploadFile(LeadCSV.create(leads));
        }
        catch (AWTException e)
        {
            assertTrue(false);
        }

        getPage().getLeadsImportForm().submit();

        assertEquals(getDriver().getCurrentUrl(),getPage().getUrl());

        assertTrue(getPage().checkLeadsImportFeedbackBlockExists());
        assertTrue(getPage().getLeadsImportFeedbackBlock().isVisible());
        assertEquals(getPage().getLeadsImportFeedbackBlock().getMessage(),"Import is finished.");

    }

    @Test
    public void testLeadImportHistoryExists()
    {

        assertTrue(getPage().checkLeadsImportFormExists());

        Admin admin = getEnvironment().getAdmin();

        Package pkg = admin.getPackage();
        assertTrue(pkg!=null);

        Set<Site> siteSet =  getEnvironment().getAdmin().getSites();
        assertTrue(siteSet.size()>0);

        Boolean siteFoundFlag = false;
        Site s = new Site();
        for (Iterator<Site> it = siteSet.iterator(); it.hasNext(); ) {
            s = it.next();
            if (s.getDomainName().equals("zengtest1.us"))
                siteFoundFlag=true;
        }
        assertTrue(siteFoundFlag);

        getPage().getLeadsImportForm().setSelectValueByValue("package",pkg.getId().toString());

        waitLoad();

        getPage().getLeadsImportForm().setSelectValueByValue("admins",getEnvironment().getAdmin().getId().toString());

        waitLoad();

        getPage().getLeadsImportForm().setSelectValueByValue("sites",s.getId().toString());

        waitLoad();

        List<Import> expectedImports = new ArrayList<Import>();
        for (Iterator<Import> its = s.getImports().iterator(); its.hasNext(); ) {
            expectedImports.add(its.next());
        }

        List<resources.classes.Import> parsedImports =getPage().getImportsListBlock().getImportsList();

        assertTrue(getPage().checkImportsListBlockExists());
        assertEquals(parsedImports.size(),expectedImports.size());

        for (Import expectedImport : expectedImports) {

            Integer expectedIgnored = expectedImport.getRowCount() - expectedImport.getAddedLeads();

            Boolean match_flag=false;

            for (resources.classes.Import parsedImport: parsedImports){


                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                if(
                    parsedImport.getFileName().equals(expectedImport.getFileName()) &&
                    parsedImport.getDataRows().equals(expectedImport.getRowCount()) &&
                    parsedImport.getNewLeads().equals(expectedImport.getAddedLeads()) &&
                    parsedImport.getIgnoredLeads().equals(expectedIgnored) &&
                    parsedImport.getImporter().equals(expectedImport.getImporterAdmin().getEmail()) &&
                    df.format(parsedImport.getDate()).equals(df.format(expectedImport.getCreateDatetime()))
                ){
                    match_flag=true;
                }
            }
            assertTrue(match_flag);
        }

    }

    private static void waitLoad(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException qq) {
            qq.printStackTrace();
        }
    }
}
