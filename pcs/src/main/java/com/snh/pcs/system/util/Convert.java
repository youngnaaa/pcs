package com.snh.pcs.system.util;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


public class Convert {

	/**
	 * request -> Map 변
	 * @param request
	 * @return
	 */
	public HashMap<String, Object> reqToMap(HttpServletRequest request) {
		 
	    HashMap<String, Object> hmap = new HashMap<String, Object>();
	    String key;
	 
	    Enumeration<?> enumt = request.getParameterNames();
	 
	    while (enumt.hasMoreElements()) {
	        key = (String) enumt.nextElement();
	        if (request.getParameterValues(key).length > 1) {
	            hmap.put(key, request.getParameterValues(key));
	        } else {
	            hmap.put(key, request.getParameter(key));
	        }
	 
	    }
	 
	    return hmap;
	}

	
	
}
