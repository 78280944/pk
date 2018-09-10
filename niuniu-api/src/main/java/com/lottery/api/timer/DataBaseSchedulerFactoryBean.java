package com.lottery.api.timer;

import java.util.Set;

import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import com.lottery.orm.bo.TSTimeTask;
import com.lottery.orm.dao.TSTimeTaskMapper;
/**
 * 读取数据库 然后判断是否启动任务
 * @author JueYue
 * @date 2013-9-22
 * @version 1.0
 */
public class DataBaseSchedulerFactoryBean extends SchedulerFactoryBean {
	
	@Autowired
	private TSTimeTaskMapper TSTimeTaskMapper;
	/**
	 * 读取数据库判断是否开始定时任务
	 */
	public void afterPropertiesSet() throws Exception {
		super.afterPropertiesSet();
		
		Set<TriggerKey> trigerrNames = this.getScheduler().getTriggerKeys(GroupMatcher.triggerGroupEquals(Scheduler.DEFAULT_GROUP));
		
		TSTimeTask task = null;
		for (TriggerKey triggerKey : trigerrNames) {
			task = TSTimeTaskMapper.selectByTaskId(triggerKey.getName());
			System.out.println("TASK...."+task.getTaskId());
			//数据库查询不到的定时任务或者定时任务的运行状态不为1时，都停止
			//TASK #327 定时器任务默认未启动 
			if(task==null || !"1".equals(task.getIsStart())){
				this.getScheduler().pauseTrigger(triggerKey);
				logger.warn("Task "+triggerKey.getName()+" pause!");
			}
		}
	}

}
