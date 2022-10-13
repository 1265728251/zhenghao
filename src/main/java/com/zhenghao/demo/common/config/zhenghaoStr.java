package com.zhenghao.demo.common.config;

import com.xdap.api.moudle.custom.AllowUrlManage;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;


/**
 * Description: 白名单配置
 * @version 1.0
 * @author huzhibo
 * @date 2022/4/21 17:06
 */
@Component
public class zhenghaoStr implements AllowUrlManage {

    @Override
    public Set<String> getCustomAllowUrls() {
        Set<String> urlSet = new HashSet<>();
        urlSet.add("/custom/common/*");
        return urlSet;
    }
}
