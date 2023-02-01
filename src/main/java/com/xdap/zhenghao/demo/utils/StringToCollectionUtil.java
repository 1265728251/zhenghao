package com.xdap.zhenghao.demo.utils;
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
    public  List<String> convert(String input) {
        input = input.replaceAll("\\[", "").replaceAll("\\]", "");
        String[] array = input.split(",");
        List<String> result = new ArrayList<>();
        for (String item : array) {
            result.add(item.trim());
        }
        return result;
    }
}
