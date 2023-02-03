package com.xdap.zhenghao.demo.service;

import com.alibaba.fastjson.JSONObject;
import com.xdap.zhenghao.demo.entity.UserInFor;

import java.util.ArrayList;

public interface IGetEmployeeInformationService {

    /*通过钉钉的身份验证，获取token*/
    String getToken() throws Exception;

    /*通过轮循获取智能人事所有的用户userid列表*/
    ArrayList<String> getUserList() throws Exception;

    /*通过获取到的userid列表，查询所有的用户信息字段信息*/
    ArrayList<JSONObject> getAllUserInFor() throws Exception;

    /*把查询到的信息更新保存到数据库*/
    JSONObject save() throws Exception;

    /*按手机号码查询员工信息*/
    UserInFor getUserInFor(String mobile);

}
