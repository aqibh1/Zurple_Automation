/**
 * 
 */
package com.z57.propertypulse;

import static org.testng.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import resources.AbstractPage;
import resources.DBHelperMethods;
import resources.ModuleCommonCache;
import resources.orm.hibernate.HibernateUtil;
import resources.orm.hibernate.models.pp.Posts;
import resources.orm.hibernate.models.z57.NotificationMailgun;
import resources.orm.hibernate.models.z57.Notifications;
import resources.utility.ActionHelper;
import resources.utility.AutomationLogger;

/**
 * @author adar
 *
 */
public class DatabasePageTest extends PageTest{

	private WebDriver driver;
	private PPLoginPage page;
	private Integer listingId;

	@Override
	public void testTopMenu() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void testTitle() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void testHeader() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void testBrand() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public AbstractPage getPage() {
		driver = getDriver();
		page = new PPLoginPage(driver);
		return page;
	}

	@Override
	public void clearPage() {
		// TODO Auto-generated method stub
		
	}
	
	@Test
	public void testVerifyCronIsTriggered() {
		getPage();
		//352061;
		listingId = Integer.parseInt(ModuleCommonCache.getElement(getThreadId(),"listing_id"));
		List<Posts> list_of_Posts = new DBHelperMethods(getEnvironment()).getPostsByListingId(listingId);
		int listSize = list_of_Posts.size();
		int counter = 0;
		while(listSize<5 && counter<10) {
			ActionHelper.staticWait(150);
			list_of_Posts = new DBHelperMethods(getEnvironment()).getPostsByListingId(listingId);
			listSize = list_of_Posts.size();
			AutomationLogger.info("Listing Id : "+listingId);
			AutomationLogger.info("Attempt Number : "+counter);
			AutomationLogger.info("List Size : "+listSize);
			counter++;
			HibernateUtil.setSessionFactoryEmpty();
		}
		
		assertTrue(listSize>=5,"Accelerator is unable to process the video and listing posts.. ");
		
		assertTrue(verifyAcceleratorPostedYoutubeVideo(), "Unable to post listing video on youtube..");
		assertTrue(verifyAcceleratorPostedFacebookVideo(), "Unable to post listing video on Facebook..");
		assertTrue(verifyAcceleratorPostedFacebookTwitterLink(), "Unable to verify the post link on facebook and twitter..");
		//asserttrue();
        LocalDateTime localNow = LocalDateTime.now(TimeZone.getTimeZone("PST").toZoneId());
	}
	
	
	
	@Test
	public void testConnectToZurpleDB() {
		getPage();
		
		new DBHelperMethods(getEnvironment()).getPostsByListingId(112);
	}
	private boolean verifyAcceleratorPostedYoutubeVideo() {
		boolean isVerified = true;
		boolean foundThePost = false;
		List<Posts> list_of_Posts = new DBHelperMethods(getEnvironment()).getPostsByListingId(listingId);

		for(Posts post: list_of_Posts) {
			if(post.getPostType().equalsIgnoreCase("youtube_listing_post") && post.getParentPostID()!=null) {
				foundThePost = true;
				if(!post.getSource().equalsIgnoreCase("accelerator")) {
					isVerified = false;
					AutomationLogger.error("Youtube video source is not accelerator");
					break;
				}

				if(post.getPostYoutube().isEmpty()) {
					isVerified = false;
					AutomationLogger.error("Post Youtube column is empty");
					break;
				}

//				if(post.getPostLink().isEmpty()) {
//					isVerified = false;
//					AutomationLogger.error("Youtube Post Link is empty..");
//					break;
//				}

				if(post.getPostImage()==null) {
					isVerified = false;
					AutomationLogger.error("Youtube Post image Link is empty..");
					break;
				}

				if(post.getStatus()!=null && post.getStatus()!=1) {
					isVerified = false;
					AutomationLogger.error("Unable to post the video status is not 1..");
					break;
				}
				if(post.getErrors()!=null) {
					isVerified = false;
					AutomationLogger.error("Unable to post the video error is not empty.."+post.getErrors());
					break;
				}
				break;
			}
		}
		return isVerified & foundThePost;
	}

	private boolean verifyAcceleratorPostedFacebookVideo() {
		boolean isVerified = true;
		boolean foundThePost = false;
		List<Posts> list_of_Posts = new DBHelperMethods(getEnvironment()).getPostsByListingId(listingId);

		for(Posts post: list_of_Posts) {
			if(post.getPostType().equalsIgnoreCase("facebook_listing_video_post") && post.getParentPostID()!=null) {
				foundThePost = true;
				if(!post.getSource().equalsIgnoreCase("accelerator")) {
					isVerified = false;
					AutomationLogger.error("Facebook video source is not accelerator");
					break;
				}

				if(post.getPostFacebook().isEmpty()) {
					isVerified = false;
					AutomationLogger.error("Post Facebook column is empty");
					break;
				}

//				if(post.getPostLink().isEmpty()) {
//					isVerified = false;
//					AutomationLogger.error("Facebook Video Post Link is empty..");
//					break;
//				}

				if(!post.getPostImage().contains("https://propertypulse.z57.com/remoteimg")) {
					isVerified = false;
					AutomationLogger.error("Facebook Video Post image Link is not valid..");
					break;
				}

				if(post.getStatus()!=null && post.getStatus()!=1) {
					isVerified = false;
					AutomationLogger.error("Unable to post the video status is not 1..");
					break;
				}
				if(post.getErrors()!=null) {
					isVerified = false;
					AutomationLogger.error("Unable to post the video. Error is not empty.."+post.getErrors());
					break;
				}
				break;
			}
		}
		return isVerified & foundThePost;
	}

	
	private boolean verifyAcceleratorPostedFacebookTwitterLink() {
		boolean isVerified = true;

		List<Posts> list_of_Posts = new ArrayList<Posts>();
		int counter = 0;
		boolean lPostStatus = true;
		boolean foundThePost = false;
		do {
			list_of_Posts = new DBHelperMethods(getEnvironment()).getPostsByListingId(listingId);
			lPostStatus = true;
			for(Posts post: list_of_Posts) {
				if(post.getPostType().equalsIgnoreCase("link")  && post.getParentPostID()!=null) {
					foundThePost = true;
					if(post.getStatus()==null || post.getStatus()!=1) {
						lPostStatus = false;
						AutomationLogger.error("Unable to post the video status is not 1..");
						break;
					}

					if(!post.getSource().equalsIgnoreCase("accelerator")) {
						isVerified = false;
						AutomationLogger.error("Facebook video source is not accelerator");
						break;
					}

					if(post.getPostFacebook().isEmpty()) {
						isVerified = false;
						AutomationLogger.error("Post Facebook column is empty");
						break;
					}
					
					if(post.getPostTwitter()==null && post.getPostTwitter().isEmpty()) {
						isVerified = false;
						AutomationLogger.error("Post Twitter column is empty");
						break;
					}

					if(post.getPostLink().isEmpty()) {
						isVerified = false;
						AutomationLogger.error("Facebook Video Post Link is empty..");
						break;
					}

					if(!post.getPostImage().contains("https://propertypulse.z57.com/remoteimg")) {
						isVerified = false;
						AutomationLogger.error("Facebook Video Post image Link is not valid..");
						break;
					}


					if(post.getErrors()!=null) {
						isVerified = false;
						AutomationLogger.error("Unable to post the video. Error is not empty.."+post.getErrors());
						break;
					}

				}
			}
			if(isVerified && foundThePost) {
				break;
			}
			ActionHelper.staticWait(160);
			counter++;
			HibernateUtil.setSessionFactoryEmpty();
		}while(!foundThePost && counter<10);
		
		return isVerified && foundThePost;
	}

	@Test
	public void testTwitterLinkAccelerator() {
		getPage();
		List<Posts> list_of_Posts = new DBHelperMethods(getEnvironment()).getTwitterAcceleratorLink();
		assertTrue(list_of_Posts.size()>0,"No twitter accelerator link post found..");
		AutomationLogger.info("Listing Id :: "+list_of_Posts.get(0).getListingId());
		AutomationLogger.info("Post Link :: "+list_of_Posts.get(0).getPostLink());
		AutomationLogger.info("Post Id :: "+list_of_Posts.get(0).getPostID());
		AutomationLogger.info("Status Id :: "+list_of_Posts.get(0).getStatus());
		AutomationLogger.info("Date Added :: "+list_of_Posts.get(0).getDateAdded());
		AutomationLogger.info("Account Id :: "+list_of_Posts.get(0).getAccountID());
		AutomationLogger.info("Schedule Id :: "+list_of_Posts.get(0).getScheduleID());
		AutomationLogger.info("Source :: "+list_of_Posts.get(0).getSource());
	}
	
	@Test
	public void testFacebookLinkAccelerator() {
		getPage();
		List<Posts> list_of_Posts = new DBHelperMethods(getEnvironment()).getFacebookAcceleratorLink();
		assertTrue(list_of_Posts.size()>0,"No Facebook accelerator link post found..");
		AutomationLogger.info("Listing Id :: "+list_of_Posts.get(0).getListingId());
		AutomationLogger.info("Post Link :: "+list_of_Posts.get(0).getPostLink());
		AutomationLogger.info("Post Id :: "+list_of_Posts.get(0).getPostID());
		AutomationLogger.info("Status Id :: "+list_of_Posts.get(0).getStatus());
		AutomationLogger.info("Date Added :: "+list_of_Posts.get(0).getDateAdded());
		AutomationLogger.info("Account Id :: "+list_of_Posts.get(0).getAccountID());
		AutomationLogger.info("Schedule Id :: "+list_of_Posts.get(0).getScheduleID());
		AutomationLogger.info("Source :: "+list_of_Posts.get(0).getSource());
	}
	
	@Test
	public void testFacebookVideoAccelerator() {
		getPage();
		List<Posts> list_of_Posts = new DBHelperMethods(getEnvironment()).getFacebookAcceleratorVideo();
		assertTrue(list_of_Posts.size()>0,"No Facebook accelerator link post found..");
		AutomationLogger.info("Listing Id :: "+list_of_Posts.get(0).getListingId());
		AutomationLogger.info("Post Link :: "+list_of_Posts.get(0).getPostLink());
		AutomationLogger.info("Post Id :: "+list_of_Posts.get(0).getPostID());
		AutomationLogger.info("Status Id :: "+list_of_Posts.get(0).getStatus());
		AutomationLogger.info("Date Added :: "+list_of_Posts.get(0).getDateAdded());
		AutomationLogger.info("Account Id :: "+list_of_Posts.get(0).getAccountID());
		AutomationLogger.info("Schedule Id :: "+list_of_Posts.get(0).getScheduleID());
		AutomationLogger.info("Source :: "+list_of_Posts.get(0).getSource());
	}
	
	@Test
	public void testYoutubeVideoAccelerator() {
		getPage();
		List<Posts> list_of_Posts = new DBHelperMethods(getEnvironment()).getYoutubeAcceleratorVideo();
		assertTrue(list_of_Posts.size()>0,"No Youtube accelerator video post found..");
		AutomationLogger.info("Listing Id :: "+list_of_Posts.get(0).getListingId());
		AutomationLogger.info("Post Link :: "+list_of_Posts.get(0).getPostLink());
		AutomationLogger.info("Post Id :: "+list_of_Posts.get(0).getPostID());
		AutomationLogger.info("Status Id :: "+list_of_Posts.get(0).getStatus());
		AutomationLogger.info("Date Added :: "+list_of_Posts.get(0).getDateAdded());
		AutomationLogger.info("Account Id :: "+list_of_Posts.get(0).getAccountID());
		AutomationLogger.info("Schedule Id :: "+list_of_Posts.get(0).getScheduleID());
		AutomationLogger.info("Source :: "+list_of_Posts.get(0).getSource());
	}
	
	@Test
	public void testMailgunNotifications() {
		List<NotificationMailgun> list_of_notification_mailgun = new DBHelperMethods(getEnvironment()).getMailgunNotifications();
		assertTrue(list_of_notification_mailgun.size()>0,"No mailgun notifications found..");
		AutomationLogger.info("Notification Mailgun Id :: "+list_of_notification_mailgun.get(0).getNotificationMailgunId());
		AutomationLogger.info("Notification Id :: "+list_of_notification_mailgun.get(0).getNotficationId());
		AutomationLogger.info("Notification Email Id :: "+list_of_notification_mailgun.get(0).getNotficationEmailId());
		AutomationLogger.info("Account Id :: "+list_of_notification_mailgun.get(0).getAccountId());
		AutomationLogger.info("External Mailgun Id :: "+list_of_notification_mailgun.get(0).getExternalMailgunId());
	
	}
	
	@Test
	public void testVerifyLaterAndRecurringPost() {
		getPage();
		boolean isFileEmpty = false;
		String lFileToReadProd = "/resources/cache/posts-to-verify-prod.json";
		String lFileToReadStage = "/resources/cache/posts-to-verify-qa.json";
		String lFileToRead = getIsProd()?System.getProperty("user.dir")+lFileToReadProd:System.getProperty("user.dir")+lFileToReadStage;
		try {
			JSONArray lJArray = new JSONArray(getDataFileContentJsonArray(lFileToRead));
			isFileEmpty = lJArray.length()==0?true:false;
			for(int i = 0 ;i<lJArray.length();i++) {
				JSONObject jObj = lJArray.getJSONObject(i);
				Posts lPost = getEnvironment().getPostByParentPostId(jObj.opt("postID").toString());
				if(lPost!=null) {
					assertTrue(lPost.getStatus()==1, "Unable to post scheduled post on the platform..\n"+lPost);
				}else {
					assertTrue(false,"Unable to verify the following post \n"+lPost);
				}
			}
		} catch (JSONException e) {
			assertTrue(false, "Unable to verify the posts..");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			assertTrue(false, "Unable to verify the posts..");
		}
		assertTrue(renameAndCreateNewFile(lFileToRead), "Unable to rename or create new file");
		if(isFileEmpty) {
			assertTrue(false, "JSON Data File is empty...");
		}
	}
	
	@Test
	public void testVerifyLaterAndRecurringPostFacebook() {
		getPage();
		boolean isFileEmpty = false;
		String lFileToReadProd = "/resources/cache/facebook-posts-prod.json";
		String lFileToReadStage = "/resources/cache/facebook-posts-qa.json";
		String lBackupFileName = "fb-";
		String lFileToRead = getIsProd()?System.getProperty("user.dir")+lFileToReadProd:System.getProperty("user.dir")+lFileToReadStage;
		try {
			JSONArray lJArray = new JSONArray(getDataFileContentJsonArray(lFileToRead));
			isFileEmpty = lJArray.length()==0?true:false;
			for(int i = 0 ;i<lJArray.length();i++) {
				JSONObject jObj = lJArray.getJSONObject(i);
				Posts lPost = getEnvironment().getPostByParentPostId(jObj.opt("postID").toString());
				if(lPost!=null) {
					assertTrue(lPost.getStatus()==1, "Unable to post scheduled post on the platform..\n"+lPost);
				}else {
					assertTrue(false,"Unable to verify the following post \n"+lPost);
				}
			}
		} catch (JSONException e) {
			assertTrue(false, "Unable to verify the posts..");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			assertTrue(false, "Unable to verify the posts..");
		}
		assertTrue(renameAndCreateNewFile(lFileToRead, lBackupFileName), "Unable to rename or create new file");
		if(isFileEmpty) {
			assertTrue(false, "JSON Data File is empty...");
		}
	}
	
	@Test
	public void testVerifyLaterAndRecurringPostTwitter() {
		getPage();
		boolean isFileEmpty = false;
		String lFileToReadProd = "/resources/cache/twitter-posts-prod.json";
		String lFileToReadStage = "/resources/cache/twitter-posts-qa.json";
		String lBackupFileName = "twitter-";
		String lFileToRead = getIsProd()?System.getProperty("user.dir")+lFileToReadProd:System.getProperty("user.dir")+lFileToReadStage;
		try {
			JSONArray lJArray = new JSONArray(getDataFileContentJsonArray(lFileToRead));
			isFileEmpty = lJArray.length()==0?true:false;
			for(int i = 0 ;i<lJArray.length();i++) {
				JSONObject jObj = lJArray.getJSONObject(i);
				Posts lPost = getEnvironment().getPostByParentPostId(jObj.opt("postID").toString());
				if(lPost!=null) {
					assertTrue(lPost.getStatus()==1, "Unable to post scheduled post on the platform..\n"+lPost);
				}else {
					assertTrue(false,"Unable to verify the following post \n"+lPost);
				}
			}
		} catch (JSONException e) {
			assertTrue(false, "Unable to verify the posts..");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			assertTrue(false, "Unable to verify the posts..");
		}
		assertTrue(renameAndCreateNewFile(lFileToRead,lBackupFileName), "Unable to rename or create new file");
		if(isFileEmpty) {
			assertTrue(false, "JSON Data File is empty...");
		}
	}
	
	@Test
	public void testVerifyLaterAndRecurringPostYoutube() {
		getPage();
		boolean isFileEmpty = false;
		String lFileToReadProd = "/resources/cache/youtube-posts-prod.json";
		String lFileToReadStage = "/resources/cache/youtube-posts-qa.json";
		String lBackupFileName = "yt-";
		String lFileToRead = getIsProd()?System.getProperty("user.dir")+lFileToReadProd:System.getProperty("user.dir")+lFileToReadStage;
		try {
			JSONArray lJArray = new JSONArray(getDataFileContentJsonArray(lFileToRead));
			isFileEmpty = lJArray.length()==0?true:false;
			for(int i = 0 ;i<lJArray.length();i++) {
				JSONObject jObj = lJArray.getJSONObject(i);
				Posts lPost = getEnvironment().getPostByParentPostId(jObj.opt("postID").toString());
				if(lPost!=null) {
					assertTrue(lPost.getStatus()==1, "Unable to post scheduled post on the platform..\n"+lPost);
				}else {
					assertTrue(false,"Unable to verify the following post \n"+lPost);
				}
			}
		} catch (JSONException e) {
			assertTrue(false, "Unable to verify the posts..");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			assertTrue(false, "Unable to verify the posts..");
		}
		assertTrue(renameAndCreateNewFile(lFileToRead,lBackupFileName), "Unable to rename or create new file");
		if(isFileEmpty) {
			assertTrue(false, "JSON Data File is empty...");
		}
	}
	
	@Test
	public void testVerifyLaterAndRecurringPostLinkedIn() {
		getPage();
		boolean isFileEmpty = false;
		String lFileToReadProd = "/resources/cache/linkedin-posts-prod.json";
		String lFileToReadStage = "/resources/cache/linkedin-posts-qa.json";
		String lBackupFileName = "linkedin-";
		String lFileToRead = getIsProd()?System.getProperty("user.dir")+lFileToReadProd:System.getProperty("user.dir")+lFileToReadStage;
		try {
			JSONArray lJArray = new JSONArray(getDataFileContentJsonArray(lFileToRead));
			isFileEmpty = lJArray.length()==0?true:false;
			for(int i = 0 ;i<lJArray.length();i++) {
				JSONObject jObj = lJArray.getJSONObject(i);
				Posts lPost = getEnvironment().getPostByParentPostId(jObj.opt("postID").toString());
				if(lPost!=null) {
					assertTrue(lPost.getStatus()==1, "Unable to post scheduled post on the platform..\n"+lPost);
				}else {
					assertTrue(false,"Unable to verify the following post \n"+lPost);
				}
			}
		} catch (JSONException e) {
			assertTrue(false, "Unable to verify the posts..");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			assertTrue(false, "Unable to verify the posts..");
		}
		assertTrue(renameAndCreateNewFile(lFileToRead, lBackupFileName), "Unable to rename or create new file");
		if(isFileEmpty) {
			assertTrue(false, "JSON Data File is empty...");
		}
	}
	
	@Test
	public void testDBConnectionVerification() {
		assertTrue(new DBHelperMethods(getEnvironment()).verifyLeadByEmailInDB("autoadmin@mailinator.com"), "Unable to connect to Database");
	}
	private boolean renameAndCreateNewFile(String pFilePath) {
		boolean isFileNameAndCreated = true;
		String lNewFileName = getIsProd()?System.getProperty("user.dir")+"/resources/cache/"+"Prod-"+getCurrentPSTTime()+".json":System.getProperty("user.dir")+"/resources/cache/"+"QA-"+getCurrentPSTTime()+".json";
		File fileToRename = new File(pFilePath);
		File newFile = new File(lNewFileName);
		try {
			Files.copy(fileToRename.toPath(), newFile.toPath());
			fileToRename.delete();
			fileToRename.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
			isFileNameAndCreated = false;
		}


		return isFileNameAndCreated;

	}
	private boolean renameAndCreateNewFile(String pFilePath, String pBackupFileName) {
		boolean isFileNameAndCreated = true;
		String lNewFileName = getIsProd()?System.getProperty("user.dir")+"/resources/cache/"+"Prod-"+pBackupFileName+getCurrentPSTTime()+".json":System.getProperty("user.dir")+"/resources/cache/"+"QA-"+pBackupFileName+getCurrentPSTTime()+".json";
		File fileToRename = new File(pFilePath);
		File newFile = new File(lNewFileName);
		try {
			Files.copy(fileToRename.toPath(), newFile.toPath());
			fileToRename.delete();
			fileToRename.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
			isFileNameAndCreated = false;
		}


		return isFileNameAndCreated;

	}
	private String getDataFileContentJsonArray(String pDataFile) throws IOException {
		String data = ""; 
		data = new String(Files.readAllBytes(Paths.get(pDataFile))); 
		return "["+data+"]"; 

	}
	
}
