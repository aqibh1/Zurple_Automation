package resources;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.net.ssl.HttpsURLConnection;


public class HTTPSRequestConnector {
	private URL url;
	private String requestType="";
	private HashMap<String,String> header;
	HttpURLConnection connection = null;
	int status=0;
	
	public URL getUrl() {
		return url;
	}
	public void setUrl(URL url) {
		this.url = url;
	}
	public String getRequestType() {
		return requestType;
	}
	public void setRequestType(String pRequestType) throws ProtocolException {
		requestType= pRequestType;
	}
	public HashMap<String, String> getHeader() {
		return header;
	}
	public void setHeader(String pKey, String pValue) {
		header.put(pKey, pValue);
	}
	public HttpURLConnection getConnection() {
		return connection;
	}
	public boolean setConnection() throws IOException, NoSuchAlgorithmException, KeyManagementException { 
		try {
			
			this.connection = (HttpURLConnection) getUrl().openConnection();
			connection.setRequestMethod(getRequestType());
			connection.connect();
			return true;
		}catch(Exception ex) {
			return false;
		}
	}
	public int getStatus() throws IOException {
		return connection.getResponseCode(); 
	}
	
	public HTTPSRequestConnector() {
		header = new HashMap<String,String>();
		disableCertificateValidation();
	}
	

	 public void disableCertificateValidation() {
		  // Create a trust manager that does not validate certificate chains
		  TrustManager[] trustAllCerts = new TrustManager[] { 
		    new X509TrustManager() {
		      public X509Certificate[] getAcceptedIssuers() { 
		        return new X509Certificate[0]; 
		      }
		      public void checkClientTrusted(X509Certificate[] certs, String authType) {}
		      public void checkServerTrusted(X509Certificate[] certs, String authType) {}
		  }};

		  // Ignore differences between given hostname and certificate hostname
		  HostnameVerifier hv = new HostnameVerifier() {
		    public boolean verify(String hostname, SSLSession session) { return true; }
		  };

		  // Install the all-trusting trust manager
		  try {
		    SSLContext sc = SSLContext.getInstance("SSL");
		    sc.init(null, trustAllCerts, new SecureRandom());
		    HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		    HttpsURLConnection.setDefaultHostnameVerifier(hv);
		  } catch (Exception e) {}
		}
}
