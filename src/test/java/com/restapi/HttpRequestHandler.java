/**
 * 
 */
package com.restapi;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;

import resources.utility.AutomationLogger;

/**
 * @author adar
 *
 */
public class HttpRequestHandler {

	public RestResponse doPost(String testName, RestRequest request, boolean https) throws Exception {
		
		testName = testName.trim();
	
		RestResponse callResponse = new RestResponse();

		CloseableHttpClient httpClient= HttpClientBuilder.create().build();

		HttpPost httpPost = new HttpPost(request.getUrl());
		request.setMethod(HTTPConstants.POST);
		if(null != request.getParams() && request.getParams().size()>0){
			List <NameValuePair> nvps = new ArrayList<NameValuePair>();

			for(Entry<String, Object> param: request.getParams().entrySet()){
				nvps.add(new BasicNameValuePair(param.getKey(), param.getValue().toString()));
			}
			httpPost.setEntity(new UrlEncodedFormEntity(nvps));

		}else if(null != request.getStringBody() && !request.getStringBody().isEmpty()){
			httpPost.setEntity(new StringEntity(request.getStringBody()));

		}else if(null != request.getMultiPartEntity()){
			httpPost.setEntity(request.getMultiPartEntity());

		}

		if(null != request.getHeaders() && request.getHeaders().size()>0){
			for(Entry<String, String> header: request.getHeaders().entrySet()){
				httpPost.setHeader(header.getKey(), header.getValue());		
			}
		}

		CloseableHttpResponse httpResponse = null;
		try {
			long timeBefore = System.currentTimeMillis();
			httpResponse = httpClient.execute(httpPost);

			long timeAfter = System.currentTimeMillis();
			callResponse.setTimeOfCall(timeAfter - timeBefore);
			callResponse.setStatus(httpResponse.getStatusLine().getStatusCode());
			if(callResponse.getStatus()==200 || callResponse.getStatus()==202){
				callResponse.setContent(IOUtils.toString(httpResponse.getEntity().getContent(), "UTF-8"));
			}
			callResponse.setReasonPhrase(httpResponse.getStatusLine().getReasonPhrase());
			callResponse.setHeaders(httpResponse.getAllHeaders());
			callResponse.setEntity(httpResponse.getEntity());

			AutomationLogger.info("["+testName+"] :: [Post Resp ] :: ["+callResponse.getStatus()+"], ["+callResponse.getReasonPhrase()+"], ["+callResponse.getContent()+"]");		

		} finally {
			if (httpResponse != null)
				httpResponse.close();
		}
		callResponse.setRequest(request);
		return callResponse;
	}

	public RestResponse doGet(String testName, RestRequest request, boolean https) throws Exception {

		
		testName = testName.trim();
		testName = StringUtils.rightPad(testName, 15);
		AutomationLogger.info("["+testName+"] :: [Get Call  ] :: ["+request.getUrl()+"], ["+request.getParams()+"]");		
		request.setMethod(HTTPConstants.GET);
		RestResponse callResponse = new RestResponse();

		CloseableHttpClient httpClient=getSSLClient(https);


		HttpGet httpGet = new HttpGet(request.getUrl());
		
		if(null != request.getHeaders() && request.getHeaders().size()>0){
			for(Entry<String, String> header: request.getHeaders().entrySet()){
				httpGet.setHeader(header.getKey(), header.getValue());		
			}
		}

		CloseableHttpResponse httpResponse = null;

		try {
			long timeBefore = System.currentTimeMillis();
			httpResponse = httpClient.execute(httpGet);
			long timeAfter = System.currentTimeMillis();
			callResponse.setTimeOfCall(timeAfter - timeBefore);
			callResponse.setStatus(httpResponse.getStatusLine().getStatusCode());
			if(callResponse.getStatus()==200){
				callResponse.setContent(IOUtils.toString(httpResponse.getEntity().getContent(), "UTF-8"));

			}
			callResponse.setReasonPhrase(httpResponse.getStatusLine().getReasonPhrase());
			callResponse.setHeaders(httpResponse.getAllHeaders());
			callResponse.setEntity(httpResponse.getEntity());

			AutomationLogger.info("["+testName+"] :: [Get Resp  ] :: ["+callResponse.getStatus()+"], ["+callResponse.getReasonPhrase()+"], ["+callResponse.getContent()+"]");		
		}
		finally {
			if (httpResponse != null)
				httpResponse.close();
		}
		callResponse.setRequest(request);
		return callResponse;
	}
	
	public CloseableHttpClient getSSLClient(boolean https) throws KeyStoreException, KeyManagementException {
		CloseableHttpClient httpClient = null;
		SSLContext sslContext; 
		try {
			sslContext = SSLContext.getInstance("TLS");
			sslContext.init(null,
					new TrustManager[] { new X509TrustManager() {
						@Override
						public X509Certificate[] getAcceptedIssuers() {
							return null;
						}

						@Override
						public void checkClientTrusted(
								X509Certificate[] chain, String authType)
										throws CertificateException {
						}

						@Override
						public void checkServerTrusted(
								X509Certificate[] chain, String authType)
										throws CertificateException {
						}
					} }, new SecureRandom());

			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
					sslContext, new X509HostnameVerifier() {
						@Override
						public void verify(String host, SSLSocket ssl)
								throws IOException {
						}

						@Override
						public void verify(String host, X509Certificate cert)
								throws SSLException {
						}

						@Override
						public void verify(String host, String[] cns,
								String[] subjectAlts) throws SSLException {
						}

						@Override
						public boolean verify(String s, SSLSession sslSession) {
							return true;
						}


					});

			Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder
					.<ConnectionSocketFactory> create().register("https", sslsf)
					.register("http", new PlainConnectionSocketFactory()).build();
			PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(
					socketFactoryRegistry);


			if(https){
				httpClient = HttpClients.custom()
						.setConnectionManager(cm).build();

			}else{
				httpClient = HttpClients.createDefault();	
			}
		} catch (KeyManagementException | NoSuchAlgorithmException e) {
		}
		return httpClient;
	}
}
