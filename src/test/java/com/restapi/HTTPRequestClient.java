package com.restapi;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import com.restapi.Part.PartType;

import resources.utility.AutomationLogger;

public class HTTPRequestClient {
	private String url;
	private String requestMethod="";
	private Map<String,String> header;
	private Map<String,Object> body;
	HttpPost httpPost;
	HttpGet httpGet;
	int status=0;
	String contentType;
	private InputStream content;
	HTTPResponseClient responseClient;
	HttpResponse response;
	
	public HTTPRequestClient() {
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
			doPost();
		}else if(requestMethod.equalsIgnoreCase("GET")) {
			doGet();
		}else if(requestMethod.equalsIgnoreCase("PUT")) {
			
		}
	}
	private void doPost() throws ClientProtocolException, IOException {
		
		HttpClient client = HttpClientBuilder.create().build();
		httpPost = new HttpPost(url);
		setPostHeaders();
		//check content type
		if(ContentType.MULTIPART_FORM_DATA.toString().contains(contentType)) {
			setMultipartEntityFromRestContent("STRING");
		}else {
			
			if(null != body && !body.isEmpty()){
				List <NameValuePair> nvps = new ArrayList<NameValuePair>();

				for(Entry<String, Object> param: body.entrySet()){
					nvps.add(new BasicNameValuePair(param.getKey(), param.getValue().toString()));
				}
				httpPost.setEntity(new UrlEncodedFormEntity(nvps));

			}
		}
		response = client.execute(httpPost);
		responseClient = new HTTPResponseClient();
		responseClient.setResponse(response);
	}
	private void doGet() throws ClientProtocolException, IOException {

		HttpClient client = HttpClientBuilder.create().build();
		httpGet = new HttpGet(url);
		setGetHeaders();
		//check content type
		response = client.execute(httpGet);
		responseClient = new HTTPResponseClient();
		responseClient.setResponse(response);
	}
	private void setPostHeaders() {
		//setting headers
		for (Map.Entry<String, String> entry : header.entrySet()) {
		    System.out.println(entry.getKey() + " = " + entry.getValue());
		    if(entry.getKey().equalsIgnoreCase(HTTPConstants.ContentType)) {
				contentType = entry.getValue();
			}
			httpPost.setHeader(entry.getKey(), entry.getValue());
		}
	}
	private void setGetHeaders() {
		//setting headers
		for (Map.Entry<String, String> entry : header.entrySet()) {
		    System.out.println(entry.getKey() + " = " + entry.getValue());
		    if(entry.getKey().equalsIgnoreCase(HTTPConstants.ContentType)) {
				contentType = entry.getValue();
			}
			httpGet.setHeader(entry.getKey(), entry.getValue());
		}
	}
	private void multipartFormData() throws UnsupportedEncodingException {
		MultipartEntity entity = new MultipartEntity();
		if(null != body && !body.isEmpty()){
			List <NameValuePair> nvps = new ArrayList<NameValuePair>();

			for(Entry<String, Object> param: body.entrySet()){
				StringBody value = new StringBody((String) param.getValue());
				entity.addPart(param.getKey(),value);
			}
			

		}
		
		httpPost.setEntity(entity);
	}

	private void setMultipartEntityFromRestContent(String pType) throws UnsupportedEncodingException {
		
		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
		builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
		
		for(Entry<String, Object> param: body.entrySet()){
			StringBody value = new StringBody(param.getValue().toString(),ContentType.MULTIPART_FORM_DATA);
			switch (pType) {
			case "FILE":
				builder.addPart(param.getKey(), new FileBody(new File((String) param.getValue())));
				break;
			case "STRING":
				builder.addPart(param.getKey(), value);
//				builder.addTextBody(param.getKey(), param.getValue().toString(), ContentType.DEFAULT_BINARY);
				break;

			default:
				break;
			}
			
		}
		httpPost.setEntity(builder.build());
	}
//	private void setMultipartEntityFromRestContent(RestContent restContent) {
//		
//		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
//		builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
//		
//		Map<String, Part> parts = restContent.getParts();
//		
//		for (String partName : parts.keySet()) {
//			Part part = parts.get(partName);
//			PartType partType = part.getType();
//			
//			switch (partType) {
//			case FILE:
//				builder.addPart(partName, new FileBody(new File(part.getContent())));
//				break;
//			case STRING:
//				builder.addTextBody(partName, part.getContent(), ContentType.TEXT_PLAIN);
//				break;
//
//			default:
//				break;
//			}
//		}
//		httpPost.setEntity(builder.build());
//	}

	public HTTPResponseClient getResponse() {
		return responseClient;
	}
}
