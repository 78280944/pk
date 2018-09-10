package com.lottery.api.filter;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.springframework.http.server.ServletServerHttpRequest;

public class RestHttpServletRequest extends HttpServletRequestWrapper {
	private static final String METHOD_POST = "POST";

	private byte[] requestBody = null;

	public RestHttpServletRequest(HttpServletRequest httpServletRequest)
	  {
	    super(httpServletRequest); // super.request = httpServletRequest;
	  }

	public ServletInputStream getInputStream() throws IOException {
		if (requestBody == null) { // first time
			InputStream in = super.getRequest().getInputStream();
			requestBody = new byte[super.getRequest().getContentLength()];
			for (int r, offset = 0; (r = in.read(requestBody, offset, requestBody.length - offset)) > -1;) {
				offset += r;
			}
		}
		final InputStream in = new ByteArrayInputStream(requestBody);
		return new ServletInputStream() {    
            @Override    
            public int read() throws IOException {    
                return in.read();    
            }    
        }; 
	}

	public BufferedReader getReader() throws IOException {
		return new BufferedReader(new InputStreamReader(getInputStream()));
	}
	
}
