package resources;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import resources.orm.hibernate.dao.*;
import resources.orm.hibernate.models.*;
import resources.orm.hibernate.HibernateUtil;

public class TestEnvironment
{

    private WebDriver driver;

    private Integer leadToCheck;
    private Integer currentAgentId;
    private Integer propertyToCheck;
    private Integer agentToCheck;
    private Integer templateToCheck;

    private List<PackageProduct> packageProducts;

    private List<Transaction> transactions;

    private Import imp;

    private Admin admin;
    
    private User lastRegisteredUser;
    private User userToCheck;


    private static SessionFactory factory;

    public WebDriver getDriver(){

        if(driver == null){
            driver = new ChromeDriver(new ChromeDriverService.Builder().withSilent(true).build());
        }
        return driver;
    }

    public void closeDriver(){
        driver.close();
        driver = null;
    }

    public Integer getLeadToCheck()
    {
        return leadToCheck;
    }

    public void setLeadToCheck(Integer leadToCheck)
    {
        this.leadToCheck = leadToCheck;
    }

    public void setTemplateToCheck(Integer templateToCheck)
    {
        this.templateToCheck = templateToCheck;
    }

    public void setPropertyToCheck(Integer propertyToCheck)
    {
        this.propertyToCheck = propertyToCheck;
    }

    public List<PackageProduct> getProductsList( )
    {
        if(packageProducts == null){

            ManageAdmin ma = new ManageAdmin(getSession());
            ManagePackageProducts map = new ManagePackageProducts(getSession());
            packageProducts = map.getPackageProductsList(ma.getAdmin(agentToCheck).getPackage());

        }

        return packageProducts;
    }

    public List<Transaction> getTransactionsList( )
    {
        if(transactions == null){

            ManageTransactions mt = new ManageTransactions(getSession());
            transactions = mt.getTransactionsListByCustomerId(agentToCheck);

        }else{
            for(Transaction trans : transactions){
                getSession().refresh(trans);
            }
        }

        return transactions;
    }

    public Lead getLeadObject(Integer lead_id)
    {

        ManageLead ml = new ManageLead(getSession());

        return ml.getLead(lead_id);
    }

    public Property getDetailedProperty(Integer prop_id)
    {

        ManageViewDetailedProperty mp = new ManageViewDetailedProperty(getSession());

        return mp.getDetailedProperty(prop_id);
    }

    public SessionAnonymous getSessionAnonymous(String session_id)
    {

        ManageSessionAnonymous msa = new ManageSessionAnonymous(getSession());

        return msa.getSessionAnonymous(session_id);
    }

    public User getUserById(Integer user_id)
    {

        ManageUser mu = new ManageUser(getSession());

        return mu.getUser(user_id);
    }

    public User getUserObject()
    {

        ManageUser mu = new ManageUser(getSession());

        return mu.getUser(getLeadToCheck());
    }

    public Site getSiteById(Integer site_id)
    {

        ManageSite ms = new ManageSite(getSession());

        return ms.getSite(site_id);
    }

    public Import getImportByFilename(String file_name)
    {
        if(imp==null){

            ManageImports mi = new ManageImports(getSession());
            imp = mi.getImportByFilename(file_name);


        }else{
            getSession().refresh(imp);
        }

        return imp;
    }

    public Admin getAdmin()
    {
        return getAdmin(getAgentToCheck());
    }

    public Session getSession(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.flush();
        return session; 
    }

    public Admin getAdmin(Integer admin_id)
    {
        if(admin==null){
            ManageAdmin ma = new ManageAdmin(getSession());
            admin =  ma.getAdmin(admin_id);
        }else{
            getSession().refresh(admin);
        }
        return admin;
    }

    public User getLastRegisteredUser(){

        if(admin==null){
            ManageUser mu = new ManageUser(getSession());
            lastRegisteredUser =  mu.getLastRegisteredUser();
        }else{
            getSession().refresh(lastRegisteredUser);
        }
                
        return lastRegisteredUser;

    }
    
    public List<SessionUser> getUserSessions(User user){

        ManageSessionUser msu = new ManageSessionUser(getSession());
        List<SessionUser> sessions =  msu.getSessionByUser(user);
        return sessions;
    }
    
    public List<DistributionRule> getDistributionRulesBySiteId(Integer site_id)
    {
        ManageDistributionRules mdr = new ManageDistributionRules(getSession());
        return mdr.getSitesDistributionRulesById(site_id);
    }

    public List<User> getLeadsAssignedToAdmin(Integer admin_id)
    {
        ManageUser mu = new ManageUser(getSession());
        return mu.getNewLeadsAssignedToAdminById(admin_id);
    }

    public List<TransactionGoal> getTransactionGoalsByLeadId(Integer lead_id)
    {
        ManageTransactionGoals mtg = new ManageTransactionGoals(getSession());
        return mtg.getTransactionGoalsListByUserId(lead_id);
    }

    public Integer getNumberAssignedToAdminOfLeadsByStatus(Integer admin_id, String status)
    {
        ManageUser mu = new ManageUser(getSession());
        return mu.getNumberAssignedToAdminOfLeadsByStatus(admin_id, status);
    }

    public Admin getAdminByEmail(String email)
    {
        ManageAdmin ma = new ManageAdmin(getSession());
        return ma.getAdminByEmail(email);
    }
    
    public EmailQueue getLastEmailQueueEntry()
    {
        ManageEmailQueue m = new ManageEmailQueue(getSession());
        return m.getLastEmailQueue();
    }

    public List<String> getLeadFlags(Integer lead_id)
    {
        ManageLead ml = new ManageLead(getSession());
        return ml.getFlags(lead_id);
    }

    public Integer getAgentToCheck()
    {
        return agentToCheck;
    }

    public void setAgentToCheck(Integer agentToCheck)
    {
        this.agentToCheck = agentToCheck;
    }

    public Integer getTemplateToCheck()
    {
        return templateToCheck;
    }

    public Integer getPropertyToCheck()
    {
        return propertyToCheck;
    }

    public Integer getCurrentAgentId()
    {
        return currentAgentId;
    }

    public void setCurrentAgentId(Integer currentAgentId)
    {
        this.currentAgentId = currentAgentId;
    }

    public User getUserToCheck() {
        if(userToCheck!=null){
            getSession().refresh(userToCheck);
        }
        return userToCheck;
    }

    public void setUserToCheck(User userToCheck) {
        this.userToCheck = userToCheck;
    }
}
