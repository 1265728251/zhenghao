package com.xdap.zhenghao.demo.service.impl;

import com.xdap.zhenghao.demo.service.IGetEmployeeInformationService;
import com.xdap.zhenghao.demo.utils.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author:杨智
 * @create: 2023-01-30 11:50
 * @Description: 实现从钉钉智能人事获取员工的详细详细，存入中间表。
 */
@Service
public class getEmployeeInformationServiceImpl implements IGetEmployeeInformationService {

    @Autowired
    private HttpUtil httpUtil;

    @Override
    public String getToken() {
       httpUtil.sendPost

        return null;
    }
}
