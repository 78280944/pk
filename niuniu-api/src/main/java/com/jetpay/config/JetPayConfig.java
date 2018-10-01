package com.jetpay.config;

import java.util.HashMap;
import java.util.Map;

import org.beetl.core.GroupTemplate;
import org.beetl.ext.jfinal.BeetlRenderFactory;

import com.jetpay.Interceptor.IndexInterceptor;
import com.jetpay.servlet.IndexController;
import com.jetpay.servlet.NetpaymentServlet;
import com.jetpay.servlet.QueryOrderServlet;
import com.jetpay.utils.XX;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.plugin.druid.DruidStatViewHandler;

public class JetPayConfig extends JFinalConfig {
	
	public static Map props=new HashMap();
	private long startTime = 0L;

	/**
	 * 配置常量1
	*/
	public void configConstant(Constants me){
		//加载少量必要配置，随后可用PropKit.get(...)获取值
		this.startTime =System.currentTimeMillis();
		
		//加载用户缓存信息
	    System.err.println("Config Constants Starting...");
	    JetPayInit.initConfig();
	    
	    me.setMainRenderFactory(new BeetlRenderFactory());
		me.setMaxPostSize(524288000);
		
	    me.setDevMode(XX.toBoolean(props.get("devMode"),Boolean.valueOf(true)).booleanValue());
	    GroupTemplate group = BeetlRenderFactory.groupTemplate;
	    
	    //BeetlRenderFactory.groupTemplate.setSharedVars(sharedVars);
	    //ApiConfigKit 设为开发模式可以在开发阶段输出请求交互的 xml 与 json 数据
		me.setDevMode(me.getDevMode());
	}
	
	/**
	 * 配置路由
	 */
	public void configRoute(Routes me){
		System.err.println("Routes loading...");
		//配置网银支付servlet
		me.add("/main", IndexController.class,"/index");
		me.add("/netpayment", NetpaymentServlet.class, "/index");
		me.add("/queryorder", QueryOrderServlet.class, "/index");
		System.err.println("Routes success...");

	}
	
	/**
	 * 配置插件
	 */
	public void configPlugin(Plugins me){
		System.err.println("Plugins loading...");	
	}
	
	/**
	 * 配置全局拦截器
	 */
	public void configInterceptor(Interceptors me){
		System.err.println("Config Interceptors Starting...");
		me.add(new IndexInterceptor());
		System.err.println("Config Interceptors success...");
	}
	
	/**
	 * 配置处理器
	*/
	public void configHandler(Handlers me){
		System.err.println("Config Handlers Starting...");
		me.add(new ContextPathHandler("ctx")); 
		DruidStatViewHandler dvh = new DruidStatViewHandler("/druid");
		me.add(dvh);
		System.err.println("Config Handlers success...");
	}
	
	private void costTime(long time){
	    System.err.println("Load Cost Time:" + (System.currentTimeMillis() - time) + "ms\n");
	}
}
