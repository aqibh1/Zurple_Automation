/**
 * 
 */
package com.z57.propertypulse;

import static org.testng.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import resources.AbstractPage;
import resources.DBHelperMethods;
import resources.ModuleCommonCache;
import resources.orm.hibernate.HibernateUtil;
import resources.orm.hibernate.models.pp.Posts;
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
			HibernateUtil.setSessionFactoryEmpty();
		}
		
		assertTrue(listSize>=5,"Accelerator is unable to process the video and listing posts.. ");
		
		assertTrue(verifyAcceleratorPostedYoutubeVideo(), "Unable to post listing video on youtube..");
		assertTrue(verifyAcceleratorPostedFacebookVideo(), "Unable to post listing video on Facebook..");
		assertTrue(verifyAcceleratorPostedFacebookTwitterLink(), "Unable to verify the post link on facebook and twitter..");
		//asserttrue();
        LocalDateTime localNow = LocalDateTime.now(TimeZone.getTimeZone("PST").toZoneId());
        

		
	
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

	
	
}
