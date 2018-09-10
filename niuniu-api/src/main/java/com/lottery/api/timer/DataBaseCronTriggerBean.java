package com.lottery.api.timer;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;

import com.lottery.orm.bo.TSTimeTask;
import com.lottery.orm.dao.TSTimeTaskMapper;
/**
 * 在原有功能的基础上面增加数据库的读取
 * @author JueYue
 * @date 2013-9-22
 * @version 1.0
 */
public class DataBaseCronTriggerBean extends CronTriggerFactoryBean{

	@Autowired
	private TSTimeTaskMapper TSTimeTaskMapper;
	/**
	 * 读取数据库更新文件
	 */
	public void afterPropertiesSet() {
		super.afterPropertiesSet();
		TSTimeTask task = TSTimeTaskMapper.selectByTaskId(this.getObject().getJobKey().getName());
		if(task!=null&&task.getIsEffect().equals("1")
				&&!task.getCronExpression().equals(this.getObject().getCronExpression())){
			try {
				if(task.getStartTime()!=null)	{
					this.setStartDelay(task.getStartTime().getTime()-System.currentTimeMillis());
					System.out.println("RunTime...."+String.valueOf(task.getStartTime().getTime()-System.currentTimeMillis()));
				}
				System.out.println("RunTimeExpression...."+task.getCronExpression());
				this.setCronExpression(task.getCronExpression());
			} catch (Exception e) {
				// TODO 异常必须被处理
				e.printStackTrace();
			}
			//DynamicTask.updateSpringMvcTaskXML(this,task.getCronExpression());
		}
	}

}
