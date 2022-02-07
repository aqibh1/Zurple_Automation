/**
 * 
 */
package com.zurple.backoffice;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.zurple.my.PageTest;

import resources.AbstractPage;

/**
 * @author darrraqi
 *
 */
public class ZurpleDockersImplementation extends PageTest{

	@Test
	public void dummyDockersTest() {
//		AutomationLogger.info("Hello Dockers from Dummy Dockers Test");
		getDriver();
		System.out.println("Hello Dockers from Dummy Dockers Test");
		assertTrue(true,"");
	}
	
	@Test
	public void callingRemoteDriver() {
//		RemoteWebDriver driver = getDriver("", 0);
	}

	@Override
	public AbstractPage getPage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void clearPage() {
		// TODO Auto-generated method stub
		
	}

}
