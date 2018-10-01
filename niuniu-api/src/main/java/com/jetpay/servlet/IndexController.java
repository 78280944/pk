package com.jetpay.servlet;

import org.apache.log4j.Logger;

import com.jfinal.core.Controller;

public class IndexController extends Controller{
	
	private Logger logger=Logger.getLogger(IndexController.class);
	
	public void main(){
		render("/main/index.html");
	}

}
