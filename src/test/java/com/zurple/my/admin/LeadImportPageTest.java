//package com.zurple.my.admin;
//
//import static org.testng.Assert.assertEquals;
//import static org.testng.Assert.assertTrue;
//
//import java.awt.AWTException;
//import java.io.File;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Set;
//import java.util.UUID;
//
//import org.testng.annotations.Test;
//
//import com.zurple.my.PageTest;
//import com.zurple.my.Admin.LeadImportPage;
//
//import resources.classes.Lead;
//import resources.classes.LeadCSV;
//import resources.orm.hibernate.models.zurple.Admin;
//import resources.orm.hibernate.models.zurple.Import;
//import resources.orm.hibernate.models.zurple.Package;
//import resources.orm.hibernate.models.zurple.Site;
//
//public class LeadImportPageTest
//        extends PageTest
//{
//    private LeadImportPage page;
//
//    public LeadImportPage getPage(){
//        if(page == null){
//            page = new LeadImportPage();
//            page.setDriver(getDriver());
//        }
//        return page;
//    }
//
//    public void clearPage(){
//        page=null;
//    };
//
//    @Test
//    public void testLeadImportFormExists()
//    {
//
//        assertTrue(getPage().checkLeadsImportFormExists());
//        assertTrue(getPage().getLeadsImportForm().checkElementExistsById("package"));
//        assertTrue(getPage().getLeadsImportForm().checkElementExistsById("admins"));
//        assertTrue(getPage().getLeadsImportForm().checkElementExistsById("sites"));
//        assertTrue(getPage().getLeadsImportForm().checkElementExistsById("pun"));
//        assertTrue(getPage().getLeadsImportForm().checkElementExistsById("csv"));
//
//    }
//
//    @Test
//    public void testLeadImportUpload()
//    {
//
//        assertTrue(getPage().checkLeadsImportFormExists());
//
//        Admin admin = getEnvironment().getAdmin();
//
//        Package pkg = admin.getPackage();
//        assertTrue(pkg!=null);
//
//        Set<Site> siteSet =  getEnvironment().getAdmin().getSites();
//        assertTrue(siteSet.size()>0);
//
//        Boolean siteFoundFlag = false;
//        Site s = new Site();
//        for (Iterator<Site> it = siteSet.iterator(); it.hasNext(); ) {
//            s = it.next();
//            if (s.getDomainName().equals("zengtest1.us"))
//                siteFoundFlag=true;
//        }
//        assertTrue(siteFoundFlag);
//
//        getPage().getLeadsImportForm().setSelectValueByValue("package",pkg.getId().toString());
//
//        waitLoad();
//
//        getPage().getLeadsImportForm().setSelectValueByValue("admins",getEnvironment().getAdmin().getId().toString());
//
//        waitLoad();
//
//        getPage().getLeadsImportForm().setSelectValueByValue("sites",s.getId().toString());
//
//        waitLoad();
//
//        getPage().getLeadsImportForm().setSelectValueByIndex("cities",2);
//
//        List<Lead> leads = new ArrayList<Lead>();
//
//        String uuid = "";
//
//
//        for(int i=1; i<10; i++){
//            uuid = UUID.randomUUID().toString();
//            leads.add(Lead.generateLead(getEnvironment().getAdmin().getId(),s.getId(),uuid.substring(0,5),true));
//        }
//
//        uuid = UUID.randomUUID().toString();
//        leads.add(Lead.generateLead(getEnvironment().getAdmin().getId(),s.getId(),uuid.substring(0,5),false));
//
//        String  csvFilePath = LeadCSV.create(leads);
//        File f = new File(csvFilePath);
//        String csvFileName = f.getName();
//
//        try
//        {
//            getPage().getLeadsImportForm().uploadFile(csvFilePath);
//        }
//        catch (AWTException e)
//        {
//            assertTrue(false);
//        }
//
//        getPage().getLeadsImportForm().submit();
//
//        assertTrue(getPage().checkLeadsImportFeedbackBlockExists());
//        assertTrue(getPage().getLeadsImportFeedbackBlock().isVisible());
//        assertTrue(getPage().getLeadsImportFeedbackBlock().getMessage().contains("Import is finished."));
//
//        Boolean match_flag = false;
//
//        Import expectedImport = getEnvironment().getImportByFilename(csvFileName);
//        Integer expectedIgnored = expectedImport.getRowCount() - expectedImport.getAddedLeads();
//
//        for(resources.classes.Import imp: getPage().getImportsListBlock().getImportsList()){
//            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            if(
//                imp.getFileName().equals(expectedImport.getFileName()) &&
//                imp.getDataRows().equals(expectedImport.getRowCount()) &&
//                imp.getNewLeads().equals(expectedImport.getAddedLeads()) &&
//                imp.getIgnoredLeads().equals(expectedIgnored) &&
//                imp.getImporter().equals(expectedImport.getImporterAdmin().getEmail()) &&
//                df.format(imp.getDate()).equals(df.format(expectedImport.getCreateDatetime()))
//            )
//            {
//                    match_flag = true;
//            }
//        }
//
//        assertTrue(match_flag);
//
//    }
//
//    @Test
//    public void testLeadImportHistoryExists()
//    {
//
//        assertTrue(getPage().checkLeadsImportFormExists());
//
//        Admin admin = getEnvironment().getAdmin();
//
//        Package pkg = admin.getPackage();
//        assertTrue(pkg!=null);
//
//        Set<Site> siteSet =  getEnvironment().getAdmin().getSites();
//        assertTrue(siteSet.size()>0);
//
//        Boolean siteFoundFlag = false;
//        Site s = new Site();
//        for (Iterator<Site> it = siteSet.iterator(); it.hasNext(); ) {
//            s = it.next();
//            if (s.getDomainName().equals("zengtest1.us"))
//                siteFoundFlag=true;
//        }
//        assertTrue(siteFoundFlag);
//
//        getPage().getLeadsImportForm().setSelectValueByValue("package",pkg.getId().toString());
//
//        waitLoad();
//
//        getPage().getLeadsImportForm().setSelectValueByValue("admins",getEnvironment().getAdmin().getId().toString());
//
//        waitLoad();
//
//        getPage().getLeadsImportForm().setSelectValueByValue("sites",s.getId().toString());
//
//        waitLoad();
//
//        List<Import> expectedImports = new ArrayList<Import>();
//        for (Iterator<Import> its = s.getImports().iterator(); its.hasNext(); ) {
//            expectedImports.add(its.next());
//        }
//
//        List<resources.classes.Import> parsedImports =getPage().getImportsListBlock().getImportsList();
//
//        assertTrue(getPage().checkImportsListBlockExists());
//        assertEquals(parsedImports.size(),expectedImports.size());
//
//        for (Import expectedImport : expectedImports) {
//
//            Integer expectedIgnored = expectedImport.getRowCount() - expectedImport.getAddedLeads();
//
//            Boolean match_flag=false;
//
//            for (resources.classes.Import parsedImport: parsedImports){
//
//
//                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                if(
//                    parsedImport.getFileName().equals(expectedImport.getFileName()) &&
//                    parsedImport.getDataRows().equals(expectedImport.getRowCount()) &&
//                    parsedImport.getNewLeads().equals(expectedImport.getAddedLeads()) &&
//                    parsedImport.getIgnoredLeads().equals(expectedIgnored) &&
//                    parsedImport.getImporter().equals(expectedImport.getImporterAdmin().getEmail()) &&
//                    df.format(parsedImport.getDate()).equals(df.format(expectedImport.getCreateDatetime()))
//                ){
//                    match_flag=true;
//                }
//            }
//            assertTrue(match_flag);
//        }
//
//    }
//}
