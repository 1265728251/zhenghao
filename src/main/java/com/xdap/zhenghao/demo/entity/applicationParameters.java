package com.xdap.zhenghao.demo.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.Version;

/**
 * @author:杨智
 * @create: 2023-01-30 14:01
 * @Description: 钉钉内部应用参数实体类
 */
@Data
@Builder
public class applicationParameters {

    @Value("${dingTalk.appSecret}")
    private String appSecret;

    @Value("${dingTalk.appKey}")
    private String  appKey;

}
