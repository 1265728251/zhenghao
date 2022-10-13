/*
package com.zhenghao.demo.common.config;

import com.xdap.common.constant.DatasourceConstant;
import com.xdap.runtime.service.RuntimeAppContextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

*/
/**
 * Description: 数据源配置类
 * @version 1.0
 * @author huzhibo
 * @date 2022/4/21 17:09
 *//*

@Component
public class MyDatasource {

    @Autowired
    private RuntimeAppContextService runtimeAppContextService;

    private String schema;

    @PostConstruct
    public void setSchema(){
        this.schema=DatasourceConstant.XDAP_MYSQL_PREFIX + runtimeAppContextService.getCurrentTenantId();
    }

    public String getDataSource(){
        return this.schema;
    }

    public String getActivitiDataSource(){
        return "xdap_activiti_"+runtimeAppContextService.getCurrentTenantId();
    }
}
*/
