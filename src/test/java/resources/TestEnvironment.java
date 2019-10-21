package resources;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import resources.orm.hibernate.dao.zurple.*;
import resources.orm.hibernate.dao.pp.ManagePosts;
import resources.orm.hibernate.dao.z57.*;
import resources.orm.hibernate.HibernateUtil;
import resources.orm.hibernate.models.AbstractLead;
import resources.orm.hibernate.models.pp.Posts;
import resources.orm.hibernate.models.z57.*;
import resources.orm.hibernate.models.zurple.*;
import resources.utility.AutomationLogger;

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
    
    private User lastRegisteredUser;
    private User userToCheck;


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

    public AbstractLead getLeadObject(Integer lead_id)
    {

        String project = System.getProperty("project");

        if (project.equals("z57"))
        {
            resources.orm.hibernate.dao.z57.ManageLead ml = new resources.orm.hibernate.dao.z57.ManageLead(getSession());
            return ml.getLead(lead_id);
        }
        else
        {
            resources.orm.hibernate.dao.zurple.ManageLead ml = new resources.orm.hibernate.dao.zurple.ManageLead(getSession());
            return ml.getLead(lead_id);
        }

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
        resources.orm.hibernate.dao.zurple.ManageLead ml = new resources.orm.hibernate.dao.zurple.ManageLead(getSession());
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
    
    //////////////////
    public NotificationEmails getNotificationEmailsObject(String pEmail)
    {
        String project = System.getProperty("project");

        if (project.equals("z57"))
        {
        	ManageNotificationEmails notification_emails_object = new ManageNotificationEmails(getSession());
            return notification_emails_object.getNotificationId(pEmail);
        }else {
        	return null;
        }
    }

    //////////////////
    public List<NotificationEmails> getNotificationEmailsObjectsList(String pEmail)
    {
        String project = System.getProperty("project");

        if (project.equals("z57"))
        {
        	ManageNotificationEmails notification_emails_object = new ManageNotificationEmails(getSession());
            return notification_emails_object.getNotificationIdList(pEmail,10);
        }else {
        	return null;
        }
    }

    //////////////////
    public IdxLeadSearches getIdxLeadSavedSearch(Integer pLeadId, String pTitle)
    {
        String project = System.getProperty("project");

        if (project.equals("z57"))
        {
        	ManageIdxLeadSearches idx_lead_searches_object = new ManageIdxLeadSearches(getSession());
            return idx_lead_searches_object.getIdxLeadSearcheByTitle(pLeadId, pTitle);
        }else {
        	return null;
        }
    }

    public NotificationMailgun getNotificationMailgunObject(Integer pNotificationId)
    {
        String project = System.getProperty("project");

        if (project.equals("z57"))
        {
        	ManageNotificationMailgun notification_mailgun_object = new ManageNotificationMailgun(getSession());
            return notification_mailgun_object.getMailgunStatus(pNotificationId);
        }else {
        	return null;
        }
    }
    
    public Notifications getNotificationObject(Integer pNotificationId)
    {
        String project = System.getProperty("project");
        if (project.equals("z57"))
        {
        	ManageNotifications notification_object = new ManageNotifications(getSession());
            return notification_object.getAllNotifications(pNotificationId);
        }else {
        	return null;
        }
    }
    
    public List<ListingImages> getListOfListingImages(Integer pListingId)
    {
        String project = System.getProperty("project");

        if (project.equals("z57"))
        {
        	ManageListingImages listing_images_object = new ManageListingImages(getSession());
            return listing_images_object.getListingImages(pListingId);
        }else {
        	return null;
        }
    }
    
    public AbstractLead getLeadObject(String pLeadEmail)
    {

        String project = System.getProperty("project");

        if (project.equals("z57"))
        {
            resources.orm.hibernate.dao.z57.ManageLead ml = new resources.orm.hibernate.dao.z57.ManageLead(getSession());
            return ml.getLead(pLeadEmail);
        }
//        else
//        {
//            resources.orm.hibernate.dao.zurple.ManageLead ml = new resources.orm.hibernate.dao.zurple.ManageLead(getSession());
//            return ml.getLead(lead_id);
//        }
        return null;

    }
    public List<NotificationEmails> getNotificationEmailsObject(String pEmail,Integer pMaxRows)
    {
        String project = System.getProperty("project");

        if (project.equals("z57"))
        {
        	ManageNotificationEmails notification_emails_object = new ManageNotificationEmails(getSession());
            return notification_emails_object.getNotificationIdList(pEmail, pMaxRows);
        }else {
        	return null;
        }
    }
    
    public resources.orm.hibernate.models.z57.Lead getNewLeadObject(String pLeadEmail)
    {

        String project = System.getProperty("project");

        if (project.equals("z57"))
        {
            resources.orm.hibernate.dao.z57.ManageLead ml = new resources.orm.hibernate.dao.z57.ManageLead(getSession());
            return ml.getLead(pLeadEmail);
        }
//        else
//        {
//            resources.orm.hibernate.dao.zurple.ManageLead ml = new resources.orm.hibernate.dao.zurple.ManageLead(getSession());
//            return ml.getLead(lead_id);
//        }
        return null;

    }
    public Listings getListingById(Integer pListingId) {
    	 String project = System.getProperty("project");

         if (project.equals("z57"))
         {
             ManageListings manageListings = new ManageListings(getSession());
             return manageListings.getListing(pListingId);
         }
         return null;
    	
    }
    public Sites getSitesByUrl(String pWPUrl) {
   	 String project = System.getProperty("project");

        if (project.equals("z57"))
        {
            ManageSites manageSites = new ManageSites(getSession());
            return manageSites.getSitesByWPUrl(pWPUrl);
        }
        return null;
   	
   }
    public Posts getPostByParentPostId(String pParentPostId) {
    	 String project = System.getProperty("project");
    	 Posts posts = null;
         if (project.equals("z57"))
         {
             ManagePosts postObject = new ManagePosts(getSession());
             posts = postObject.getPostsByParentPostId(pParentPostId);
             if(posts!=null) {
            	 AutomationLogger.info("Successfully fetched Posts object for Parent Post Id "+pParentPostId);
             }else {
            	 AutomationLogger.error("Posts object is null for Parent Post Id "+pParentPostId);
             }
         }else {
        	 
         }
         
         return posts;
    }
    
    public Posts getPostByFacebookStatus(String pFacebookStatus,String pFacebookPage) {
   	 String project = System.getProperty("project");
   	 Posts posts = null;
        if (project.equals("z57"))
        {
            ManagePosts postObject = new ManagePosts(getSession());
            posts = postObject.getPostsByFacebookStatus(pFacebookStatus,pFacebookPage);
            if(posts!=null) {
           	 AutomationLogger.info("Successfully fetched Posts object for Parent Post Id "+pFacebookStatus);
            }else {
           	 AutomationLogger.error("Posts object is null for Parent Post Id "+pFacebookStatus);
            }
        }else {
       	 
        }
        
        return posts;
   }
    public Posts getPostByTwitterStatus(String pTwitterStatus) {
      	 String project = System.getProperty("project");
      	 Posts posts = null;
           if (project.equals("z57"))
           {
               ManagePosts postObject = new ManagePosts(getSession());
               posts = postObject.getPostsByTwitterStatus(pTwitterStatus);
               if(posts!=null) {
              	 AutomationLogger.info("Successfully fetched Posts object for Parent Post Id "+pTwitterStatus);
               }else {
              	 AutomationLogger.error("Posts object is null for Parent Post Id "+pTwitterStatus);
               }
           }else {
          	 
           }
           
           return posts;
      }
    
    public Posts getPostByLinkedInStatus(String pLinkedInStatus) {
     	 String project = System.getProperty("project");
     	 Posts posts = null;
          if (project.equals("z57"))
          {
              ManagePosts postObject = new ManagePosts(getSession());
              posts = postObject.getPostsByLinkedInStatus(pLinkedInStatus);
              if(posts!=null) {
             	 AutomationLogger.info("Successfully fetched Posts object for Parent Post Id "+pLinkedInStatus);
              }else {
             	 AutomationLogger.error("Posts object is null for Parent Post Id "+pLinkedInStatus);
              }
          }else {
         	 
          }
          
          return posts;
     }
    
    public List<Posts> getPostsByListingId(Integer pListingId){
    	String project = System.getProperty("project");
    	if (project.equals("z57"))
    	{
    		ManagePosts postObject = new ManagePosts(getSession());
    		return postObject.getPostsByListingId(pListingId);
    	}else {
    		getSession();
    		return null;
    	}
    }

    
    public List<Posts> getTwitterAcceleratorLinkPosts(){
    	String project = System.getProperty("project");
    	if (project.equals("z57"))
    	{
    		ManagePosts postObject = new ManagePosts(getSession());
    		return postObject.getTwitterAcceleratorLinkPosts();
    	}else {
    		getSession();
    		return null;
    	}
    }
    
    public List<Posts> getFacebookAcceleratorLinkPosts(){
    	String project = System.getProperty("project");
    	if (project.equals("z57"))
    	{
    		ManagePosts postObject = new ManagePosts(getSession());
    		return postObject.getFacebookAcceleratorLinkPosts();
    	}else {
    		getSession();
    		return null;
    	}
    }
    
    public List<Posts> getFacebookAcceleratorVideoPosts(){
    	String project = System.getProperty("project");
    	if (project.equals("z57"))
    	{
    		ManagePosts postObject = new ManagePosts(getSession());
    		return postObject.getFacebookAcceleratorVideoPosts();
    	}else {
    		getSession();
    		return null;
    	}
    }

	public List<Posts> getYoutubeAcceleratorVideoPosts() {
		String project = System.getProperty("project");
    	if (project.equals("z57"))
    	{
    		ManagePosts postObject = new ManagePosts(getSession());
    		return postObject.getYoutubeAcceleratorVideoPosts();
    	}else {
    		getSession();
    		return null;
    	}
	}
	
	public List<NotificationMailgun> getMailgunNotifications() {
		String project = System.getProperty("project");
    	if (project.equals("z57"))
    	{
    		ManageNotificationMailgun notificationMailgunObject = new ManageNotificationMailgun(getSession());
    		return notificationMailgunObject.getNotificationMailgunByDate();
    	}else {
    		getSession();
    		return null;
    	}
	}
}
