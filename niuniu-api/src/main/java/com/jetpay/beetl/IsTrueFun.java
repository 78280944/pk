package com.jetpay.beetl;

import org.beetl.core.Context;
import org.beetl.core.Function;

public class IsTrueFun
  implements Function
{
  public Object call(Object[] paras, Context ctx)
  {
     if (paras.length != 1) {
       throw new RuntimeException("参数错误，期望Object");
    }
     Object para = paras[0];
     if (para == null) {
       return Boolean.valueOf(false);
    }
     if (para.toString().equalsIgnoreCase("true")) {
       return Boolean.valueOf(true);
    }
     if (para.toString().equals("1")) {
       return Boolean.valueOf(true);
    }
     return Boolean.valueOf(false);
  }
}

