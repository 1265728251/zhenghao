package com.zhenghao.demo.common.config;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description: 项目启动类
 * @version 1.0
 * @author huzhibo
 * @date 2022/4/19 12:43
 */
@RestController
@RequestMapping("/custom/common")
public class HealthCheck {
    @GetMapping("/isServiceStart")
    public int isServiceStart(){
        return 0;
    }


}
