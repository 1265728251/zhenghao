package com.xdap.zhenghao.demo.scheduledTasks;

import com.xdap.zhenghao.demo.service.IGetEmployeeInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author:杨智
 * @create: 2023-01-30 11:40
 * @Description:定时获取钉钉智能人事的员工详细详细
 */
@Configuration //标记配置类
@EnableScheduling  //开启定时任务
public class getUserData {
    @Autowired
    private IGetEmployeeInformationService iGetEmployeeInformationService;

    @Scheduled(cron = "0 0 0 ? * *")
    private void myTasks(){


    }
}
