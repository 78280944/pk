 package com.jetpay.handler;
 
 import com.jfinal.handler.Handler;
 import java.io.PrintStream;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 
 public class DruidHandler extends Handler
 {
   public void handle(String target, HttpServletRequest request, HttpServletResponse response, boolean[] isHandled)
   {
     System.out.println("Handlder一下...");
     this.next.handle(target, request, response, isHandled);
   }
 }

