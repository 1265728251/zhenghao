package com.xdap.zhenghao.demo.scheduledTasks;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xdap.zhenghao.demo.service.IGetEmployeeInformationService;
import com.xdap.zhenghao.demo.utils.HttpUtil;
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
    @Autowired
    private HttpUtil httpUtil;

    private String remindUrl;

    @Scheduled(cron = "0 0/10 * * * ?")
    private void myTasks(){
        try {
            JSONObject json =new JSONObject();
            json =  iGetEmployeeInformationService.save();

            /*通知是否更新成功，以及更新的条数*/
            /*JSONObject jsonObject = new JSONObject();
            jsonObject.put("key","");
            jsonObject.put("first","正浩技术中台项目");
            jsonObject.put("code","人员信息定时同步监控");
            jsonObject.put("infor","本次同步共更新了"+json.get("updata")+"个员工信息。"+"新增了"+json.get("add")+"个员工信息");
            httpUtil.sendPost(remindUrl,jsonObject.toJSONString());*/
        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}
