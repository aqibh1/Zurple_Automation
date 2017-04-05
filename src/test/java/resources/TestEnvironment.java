package resources;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import resources.orm.hibernate.dao.ManageAdmin;
import resources.orm.hibernate.dao.ManageDistributionRules;
import resources.orm.hibernate.dao.ManageEmailQueue;
import resources.orm.hibernate.dao.ManageImports;
import resources.orm.hibernate.dao.ManageTransactionGoals;
import resources.orm.hibernate.dao.ManageTransactions;
import resources.orm.hibernate.dao.ManageUser;
import resources.orm.hibernate.models.Admin;
import resources.orm.hibernate.HibernateUtil;
import resources.orm.hibernate.dao.ManagePackageProducts;
import resources.orm.hibernate.models.DistributionRule;
import resources.orm.hibernate.models.Import;
import resources.orm.hibernate.models.PackageProduct;
import resources.orm.hibernate.models.EmailQueue;
import resources.orm.hibernate.models.Lead;
import resources.orm.hibernate.dao.ManageLead;
import resources.orm.hibernate.models.Transaction;
import resources.orm.hibernate.models.TransactionGoal;
import resources.orm.hibernate.models.User;

public class TestEnvironment
{

    private Integer leadToCheck;
    private Integer currentAgentId;
    private Integer propertyToCheck;
    private Integer agentToCheck;
    private Integer templateToCheck;

    private List<PackageProduct> packageProducts;

    private List<Transaction> transactions;

    private Import imp;

    private Admin admin;

    private static SessionFactory factory;

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

    public User getUserById(Integer user_id)
    {

        ManageUser mu = new ManageUser(getSession());

        return mu.getUser(user_id);
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

    private Session getSession(){
        return HibernateUtil.getSessionFactory().openSession();
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

    public void updateUser(User user)
    {
        ManageUser mu = new ManageUser(getSession());
        mu.updateUser(user);
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
}
