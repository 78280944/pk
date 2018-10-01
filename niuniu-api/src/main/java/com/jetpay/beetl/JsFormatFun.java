 package com.jetpay.beetl;
 
 import org.beetl.core.Context;
import org.beetl.core.Function;

import com.jetpay.utils.XX;
 
 public class JsFormatFun
   implements Function
 {
   public Object call(Object[] paras, Context ctx)
  {
    if (paras.length != 1) {
       throw new RuntimeException("参数错误，期望Object");
     }
     Object obj = paras[0];
     if (XX.isEmpty(obj)) {
      return "undefined";
    }
     if (XX.isNum(obj)) {
       return obj.toString();
    }
     return XX.format(obj);
  }
 }
