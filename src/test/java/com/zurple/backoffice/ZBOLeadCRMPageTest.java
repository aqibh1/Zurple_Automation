/**
 * 
 */
package com.zurple.backoffice;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zurple.my.PageTest;
import com.zurple.website.ZWRegisterUserPageTest;

import resources.AbstractPage;
import resources.ModuleCommonCache;
import resources.utility.ActionHelper;
import resources.utility.AutomationLogger;

/**
 * @author adar
 *
 */
public class ZBOLeadCRMPageTest extends PageTest{

	private ZBOLeadCRMPage page;
	private WebDriver driver;
	
	public AbstractPage getPage() {
    	page=null;
    	if(page == null){
        	driver = getDriver();
			page = new ZBOLeadCRMPage(driver);
			page.setUrl("");
			page.setDriver(driver);
        }
        return page;
	}
    
    public AbstractPage getPage(String pUrl){
        if(page == null){
        	driver = getDriver();
			page = new ZBOLeadCRMPage(driver);
			page.setUrl(pUrl);
			page.setDriver(driver);
        }
        return page;
    }

	@Override
	public void clearPage() {
		// TODO Auto-generated method stub
	}
	
	@Test
	public void testAddAndDeleteNote() {
		getPage("/leads/crm");
		assertTrue(page.isLeadCRMPage(), "Lead CRM page is not visible..");
		String lead_name_id = page.getLeadName();
		String l_leadName = lead_name_id.split(",")[0].trim();
		String l_leadId = lead_name_id.split(",")[1].trim();
		AutomationLogger.info("Lead ID::"+l_leadId);
		AutomationLogger.info("Lead ID::"+l_leadName);
		String l_comment = updateName("This is automated generated note");
		assertTrue(page.searchLead(l_leadName), "Unable to search lead..");
		assertTrue(page.clickOnNoteButton(), "Unable to click on Note button on CRM page..");
		assertTrue(page.getAddNoteForm().isAddNoteForm(), "Add Note form is not visible..");
		assertTrue(page.getAddNoteForm().typeComment(l_comment), "Unable to type not in comment section..");
		assertTrue(page.getAddNoteForm().clickOnSaveButton(), "Unable to click on Save button..");
		assertTrue(page.getAddNoteForm().getSuccessAlert().clickOnOkButton(), "Unable to click on Ok button..");
		ActionHelper.staticWait(5);
		assertTrue(page.clickOnNoteButton(), "Unable to click on Note button on CRM page..");
		assertTrue(page.getAddNoteForm().isCommentAddedSuccessfully(l_comment), "Unable to verify comment..");
		
		page = null;
		getPage("/lead/"+l_leadId);
		ZBOLeadDetailPage leadDetailPage = new ZBOLeadDetailPage(driver);
		assertTrue(leadDetailPage.isLeadDetailPage(), "Lead Detail page is not visible..");
		ActionHelper.staticWait(5);
		assertTrue(leadDetailPage.verifyNoteAndTime(l_comment), "Unable to verify note and time in lead details page..");
		
		page = null;
		getPage("/leads/crm");
		assertTrue(page.isLeadCRMPage(), "Lead CRM page is not visible..");
		ActionHelper.staticWait(5);
		assertTrue(page.searchLead(l_leadName), "Unable to search lead..");
		assertTrue(page.clickOnNoteButton(), "Unable to click on Note button on CRM page..");
		assertTrue(page.getAddNoteForm().isAddNoteForm(), "Add Note form is not visible..");
		assertTrue(page.getAddNoteForm().clickOnDeleteButton(), "Unable to click on delete button..");
		assertTrue(page.getAddNoteForm().confirmDeleteAlert(), "Unable to close the alert..");
		assertFalse(page.getAddNoteForm().isCommentAddedSuccessfully(l_comment), "Note is not deleted successfully..");
	}
}
