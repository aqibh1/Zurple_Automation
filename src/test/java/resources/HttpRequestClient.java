package resources;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.HttpClientBuilder;

import resources.utility.HTTPConstants;

public class HttpRequestClient {
	private String url;
	private String requestMethod="";
	private Map<String,String> header;
	private Map<String,Object> body;
	HttpPost post;
	HttpResponse response;
	int status=0;
	String contentType;
	private InputStream content;
	
	public HttpRequestClient() {
		header = new HashMap<String,String>();
		body = new HashMap<String, Object>();
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getRequestType() {
		return requestMethod;
	}
	public void setRequestType(String pRequestType) throws ProtocolException {
		requestMethod= pRequestType;
	}
	public Map<String, String> getHeader() {
		return header;
	}
	public void setHeader(String pKey, String pValue) {
		header.put(pKey, pValue);
	}
	public void setStatus(int pStatus) {
		status = pStatus;
	}
	public int getStatus() {
		return status;
	}
	
	public Map<String, Object> getBody() {
		return body;
	}
	public void setBody(String pKey, String pValue) {
		body.put(pKey, pValue);
	}
	public InputStream getContent() {
		return content;
	}
	public void setContent(InputStream content) {
		this.content = content;
	}
	public void execute() throws ClientProtocolException, IOException {
		if(requestMethod.equalsIgnoreCase("POST")) {
			composePostCall();
		}else if(requestMethod.equalsIgnoreCase("GET")) {
			
		}
	}
	private void composePostCall() throws ClientProtocolException, IOException {
		
		HttpClient client = HttpClientBuilder.create().build();
		post = new HttpPost(url);
		//setting headers
		for (Map.Entry<String, String> entry : header.entrySet()) {
		    System.out.println(entry.getKey() + " = " + entry.getValue());
		    if(entry.getKey().equalsIgnoreCase(HTTPConstants.ContentType)) {
				contentType = entry.getValue();
			}
			post.setHeader(entry.getKey(), entry.getValue());
		}
//		header.forEach((k,v)->{
//			System.out.println("Item : " + k + " Count : " + v);
//			if(k.equalsIgnoreCase("ContentType")) {
//				contentType = v;
//			}
//			post.setHeader(k, v);
//		});
		//check content type
		if(contentType.equalsIgnoreCase(ContentType.MULTIPART_FORM_DATA.toString())) {
			multipartFormData();
		}else {
			
		}
		response = client.execute(post);
		setStatus(response.getStatusLine().getStatusCode());
		setContent(response.getEntity().getContent());
	}
	
	private void multipartFormData() {
		MultipartEntity entity = new MultipartEntity();
		File file = new File(body.get("file").toString());
		entity.addPart("file", new FileBody(file));
		post.setEntity(entity);
	}
	
}
