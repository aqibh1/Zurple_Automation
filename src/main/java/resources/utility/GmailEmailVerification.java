/**
 * 
 */
package resources.utility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * @author adar
 *
 */
public class GmailEmailVerification {
	
	public boolean isEmailPresentAndReply(String pEmail, String pAppPassword, String pSubjectToVerify, String pEmailAddressToReply, boolean pReplyToEmail) {
		   boolean isEmailSent = true;
		   Date date = null;
//		   pSubjectToVerify = "0929202038762 Scheduleshowing";
//		   pEmail = "z57testuser@gmail.com";
//		   pAppPassword = "vindthawdqwinsqw";
		   
		   Properties properties = new Properties();
		   properties.put("mail.store.protocol", "pop3");
		      properties.put("mail.pop3s.host", "pop.gmail.com");
		      properties.put("mail.pop3s.port", "995");
		      properties.put("mail.pop3.starttls.enable", "true");
		      properties.put("mail.smtp.auth", "true");
		      properties.put("mail.smtp.starttls.enable", "true");
		      properties.put("mail.smtp.host", "relay.jangosmtp.net");
		      properties.put("mail.smtp.port", "25");
		   Session session = Session.getDefaultInstance(properties);
//		   session.setDebug(true);
		   try 
		   {
			   // Get a Store object and connect to the current host
			   Store store = session.getStore("pop3s");
			   //		         store.connect("pop.gmail.com", "z57testuser@gmail.com","vindthawdqwinsqw");//change the user and password accordingly
			   store.connect("pop.gmail.com", pEmail,pAppPassword);
			   Folder folder = store.getFolder("inbox");
			   if (!folder.exists()) {
				   AutomationLogger.error("Class :: ActionHelper");
				   AutomationLogger.error("Method Name :: isEmailPresentAndReply");
				   AutomationLogger.error("Error :: Inbox not found..");
				   return false;
			   }
			   
			   folder.open(Folder.READ_ONLY);
			   Message[] messages = folder.getMessages();
			   if (messages.length != 0) {

				   for (int i = 0, n = messages.length; i < n; i++) {
					   Message message = messages[i];
					   date = message.getSentDate();
					   SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/YYYY");
					   String subject = message.getSubject();
					   AutomationLogger.info("Subject :: "+subject);
					   if (subject != null && subject.contains(pSubjectToVerify) && getTodaysDate(0).equalsIgnoreCase(sdf.format(date).toString())) {
						   AutomationLogger.info("Subject: " + subject);
						   if(pReplyToEmail) {
							   isEmailSent = replyToEmail(message, pEmailAddressToReply, session, pEmail, pAppPassword);
						   }else {
							   isEmailSent = true;
							   break;
						   }
					   }
				   }
			   } 
			   // close the store and folder objects
			   folder.close(false);
			   store.close();
		   } catch (Exception e) {
			   AutomationLogger.error(e.getMessage());
			   isEmailSent = false;
		   }
		   return isEmailSent;
	   }
	   private boolean replyToEmail(Message pMessageToReply, String pEmailAddressToReply, Session pSession, String pEmail, String pAppPassword) throws MessagingException {
		   boolean isEmailSent = true;
		   // Get all the information from the message
		  
		   String from = InternetAddress.toString(pMessageToReply.getFrom());
		   if (from != null) {
			   AutomationLogger.info("From: " + from);
		   }
		   String replyTo = pEmailAddressToReply;//InternetAddress.toString(pMessageToReply.getReplyTo());
		   if (replyTo != null) {
			   AutomationLogger.info("Reply-to: " + replyTo);
		   }
		   String to = pEmail;//InternetAddress.toString(pMessageToReply.getRecipients(Message.RecipientType.TO));
		   if (to != null) {
			   AutomationLogger.info("To: " + pEmail);
		   }
		   String ans = "y";
		   if ("Y".equals(ans) || "y".equals(ans)) {
			   
			   Message replyMessage = new MimeMessage(pSession);
			   replyMessage = (MimeMessage) pMessageToReply.reply(false);
			   replyMessage.setFrom(new InternetAddress(to));
			   replyMessage.setText("Thanks");
//			   replyMessage.setReplyTo(pMessageToReply.getReplyTo());
			   replyMessage.setReplyTo(InternetAddress.parse(replyTo));
			   // Send the message by authenticating the SMTP server
			   // Create a Transport instance and call the sendMessage
			   Transport t = pSession.getTransport("smtp");
			   try {
				   //connect to the smpt server using transport instance
				   //change the user and password accordingly	
				   t.connect("smtp.gmail.com",pEmail.trim(),pAppPassword.trim());

				   t.sendMessage(replyMessage,replyMessage.getAllRecipients());
			   } catch(Exception ex){
				   isEmailSent = false;
			   }finally {
				   t.close();
			   }
			   if(isEmailSent) {
				   System.out.println("message replied successfully ....");	  
			   }
		   } 
		   return isEmailSent;
	   }

	   
	   private String getTodaysDate(int pDays) {
	    	Date date = new Date();
	    	SimpleDateFormat df  = new SimpleDateFormat("MM/dd/YYYY");
	    	Calendar c1 = Calendar.getInstance();
	    	String currentDate = df.format(date);// get current date here
	    	
	    	if(pDays>0) {
	        	c1.add(Calendar.DAY_OF_YEAR, pDays);
	        	df = new SimpleDateFormat("MM/dd/YYYY");
	        	Date resultDate = c1.getTime();
	        	currentDate = df.format(resultDate);
	    	}
	    	return currentDate;
	    	
	    }
}
