package com.xdap.zhenghao.demo.utils;

import com.alibaba.fastjson.JSONObject;
import com.xdap.zhenghao.demo.entity.UserInFor;
import org.h2.engine.User;
import org.springframework.stereotype.Component;

/**
 * @author:杨智
 * @create: 2023-02-08 10:12
 * @Description: 学历信息解析
 */
@Component
public class highestEdUtils {
  public UserInFor highest(UserInFor userInFor){
      /*学历的在json中的key是highestEdu*/
      if (userInFor.getHighestEdu()!=null){
          if (userInFor.getHighestEdu().equals("大专")){
              userInFor.setHighestEdu("大学专科");
          }else if (userInFor.getHighestEdu().equals("本科")){
              userInFor.setHighestEdu("大学本科");
          }else if (userInFor.getHighestEdu().equals("高中")){
              userInFor.setHighestEdu("普通高中");
          }else if (userInFor.getHighestEdu().equals("中专")){
              userInFor.setHighestEdu("中等专科");
          }
      }

      return userInFor;
  }
}
