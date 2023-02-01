package com.xdap.zhenghao.demo.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.common.utils.StringUtils;
import com.xdap.zhenghao.demo.entity.applicationParameters;
import com.xdap.zhenghao.demo.service.IGetEmployeeInformationService;
import com.xdap.zhenghao.demo.utils.ArrayUtil;
import com.xdap.zhenghao.demo.utils.HttpUtil;
import com.xdap.zhenghao.demo.utils.StringToCollectionUtil;
import org.apache.avro.data.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.apache.hadoop.hbase.security.visibility.VisibilityLabelsValidator.regex;

/**
 * @author:杨智
 * @create: 2023-01-30 11:50
 * @Description: 实现从钉钉智能人事获取员工的详细详细，存入中间表。
 */
@Service
public class getEmployeeInformationServiceImpl implements IGetEmployeeInformationService {

    @Autowired
    private HttpUtil httpUtil;

    @Value("${dingTalk.user.appSecret}")
    private String appSecret;

    @Value("${dingTalk.user.appKey}")
    private String  appKey;

    @Autowired
    private ArrayUtil arrayUtil;

    @Autowired
    private StringToCollectionUtil stringToCollectionUtil;
    /*
    * @Author: yangzhi
    * @Date: 2023/1/30 17:25
    * @Description:此方法获取钉钉的授权token
    * @return: 返回钉钉签名token
    */
    @Override
    public String getToken() throws Exception {
       applicationParameters applicationParameters = new applicationParameters();


       JSONObject jsonObject = new JSONObject();
       jsonObject.put("appKey",appKey);
       jsonObject.put("appSecret",appSecret);

        System.out.println("获取token请求参数"+jsonObject.toJSONString());
        String s = httpUtil.sendPost("https://api.dingtalk.com/v1.0/oauth2/accessToken", jsonObject.toJSONString());
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1 = (JSONObject) JSON.parse(s);
        return jsonObject1.get("accessToken").toString();
    }

    /*
    * @Author: yangzhi
    * @Date: 2023/1/30 17:59
    * @Description: 该方法是循环一页一页的每页50条，从钉钉智能人事中查询出用户的userid数组，然后最后返回所有的userid数组；
    * @return: 然后最后返回所有的userid数组
    */

    @Override
    public ArrayList<String> getUserList() throws Exception {

        /*员工userid*/
        ArrayList<String> useridList = new ArrayList<>();

        /*获取token*/
        String token = getToken();

        String next_cursor = "0";

        while (next_cursor!=null) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("offset", next_cursor);
            jsonObject.put("size", "50");
            jsonObject.put("status_list", "2,3,5");

            String s = httpUtil.sendPost("https://oapi.dingtalk.com/topapi/smartwork/hrm/employee/queryonjob?access_token=" +token, jsonObject.toJSONString());

            JSONObject jsonObject1 = (JSONObject) JSON.parse(s);
            JSONObject jsonObjectResult = (JSONObject) jsonObject1.get("result");
            String data_list =  jsonObjectResult.get("data_list").toString();

           // String[] array = data_list.substring(1, data_list.length() - 1).split(", ");

            List<String> convert = stringToCollectionUtil.convert(data_list);


            System.out.println("原始数组长度"+convert.size());
            System.out.println("原始数组数据"+convert.toString());

            /*把每一页的集合添加到useridList数组中*/
            for (int i =0;i<convert.size();i++){
                useridList.add(convert.get(i));
            }

            try {
                next_cursor = jsonObjectResult.get("next_cursor").toString();
            } catch (Exception e) {
                next_cursor = null;
            }
        }

        System.out.println("获取到的员工userid数组"+useridList);

        return useridList;
    }

    /*
    * @Author: yangzhi
    * @Date: 2023/1/31 15:09
    * @Description: 通过钉钉智能人事的接口获取用户的详细信息数据
    * @return: 返回一个详细信息列表
    */

    @Override
    public ArrayList<JSONObject> getAllUserInFor() throws Exception {
        /*token*/
        String token =getToken();

        /*所有的员工的userid列表*/
        ArrayList<String> useridList =getUserList();

        /*把员工的userid分组，因为钉钉不支持提示查询所有用户*/
        ArrayList<String[]> strings = arrayUtil.splitArray(useridList, 20);

       /*打印输入*/
        ArrayList<String[]> list = strings;
        System.out.println("数组分为了"+strings.size()+"组");
        for (String[] array : list) {

            System.out.println("分组后的数组"+Arrays.toString(array));
        }

        /*存储请求的用户详情*/
        ArrayList<JSONObject> inForList = new ArrayList<>();

        /*循环请求数据存入集合*/
        for (int i =0; i<strings.size();i++){
            /*获取每一项的数组*/
            String[] userid =strings.get(i);
            /*把每一项的数组转换为用逗号隔开的字符串*/
            String str = StringUtils.join(",", userid);
            /*封装请求参数*/
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("userid_list",str);

            System.out.println("员工详细信息请求参数"+jsonObject.toJSONString());
            /*与钉钉进行网络请求
             */
            String userInFor = httpUtil.sendPost("https://oapi.dingtalk.com/topapi/smartwork/hrm/employee/list?access_token=" + token, jsonObject.toJSONString());

            inForList.add((JSONObject) JSON.parse(userInFor));

        }
        System.out.println("与钉钉请求获取到的所有员工详细信息"+inForList);



      /*  StringBuilder useridStringBuilder = new StringBuilder();
        for (int i = 0; i < useridList.size(); i++) {
            useridStringBuilder.append(useridList.get(i));
            if (i != useridList.size() - 1) {
                useridStringBuilder.append(",");
            }
        }
        String useridString = useridStringBuilder.toString().replace("\"", "");

        System.out.println("所有员工的userid请求字符串"+useridString);

        String[] array = useridString.split(",");
        System.out.println("员工总数是:"+array.length+"人");


        *//*获取员工的详细信息*//*
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userid_list",useridString);*/


        return inForList;
    }
}
