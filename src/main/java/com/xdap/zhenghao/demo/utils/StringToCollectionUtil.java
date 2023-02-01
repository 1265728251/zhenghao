package com.xdap.zhenghao.demo.utils;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:杨智
 * @create: 2023-02-01 10:13
 * @Description:
 */

@Component
public class StringToCollectionUtil {
    public static List<String> convert(String input) {
        List<String> result = new ArrayList<>();
        List<Object> objects = JSON.parseArray(input);
        for (Object object : objects) {
            result.add(JSON.toJSONString(object));
        }
        return result;
    }
}
