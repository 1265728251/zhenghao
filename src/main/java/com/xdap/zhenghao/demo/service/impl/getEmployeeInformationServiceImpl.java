package com.xdap.zhenghao.demo.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.common.utils.StringUtils;
import com.xdap.zhenghao.demo.dao.userInForDao;
import com.xdap.zhenghao.demo.entity.UserInFor;
import com.xdap.zhenghao.demo.entity.applicationParameters;
import com.xdap.zhenghao.demo.service.IGetEmployeeInformationService;
import com.xdap.zhenghao.demo.utils.ArrayUtil;
import com.xdap.zhenghao.demo.utils.HttpUtil;
import com.xdap.zhenghao.demo.utils.StringToCollectionUtil;
import org.apache.avro.data.Json;
import org.bouncycastle.crypto.signers.ISOTrailers;
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

    @Autowired
    private userInForDao userInForDao;
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

       // System.out.println("获取token请求参数"+jsonObject.toJSONString());
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


//            System.out.println("原始数组长度"+convert.size());
//            System.out.println("原始数组数据"+convert.toString());

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

       // System.out.println("获取到的员工userid数组"+useridList);

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
        //System.out.println("数组分为了"+strings.size()+"组");
        for (String[] array : list) {

            //System.out.println("分组后的数组"+Arrays.toString(array));
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
            String useridString = str.replace("\"", "");

            jsonObject.put("userid_list",useridString);

            System.out.println("员工详细信息请求参数"+jsonObject.toJSONString());
            /*与钉钉进行网络请求
             */
            String userInFor = httpUtil.sendPost("https://oapi.dingtalk.com/topapi/smartwork/hrm/employee/list?access_token=" + token, jsonObject.toJSONString());


            inForList.add((JSONObject) JSON.parse(userInFor));

        }

       // System.out.println("与钉钉请求获取到的所有员工详细信息"+inForList);



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

    @Override
    public JSONObject save() throws Exception {

        /*计数*/
        int add = 0;
        int updata=0;
        ArrayList<JSONObject> allUserInFor = getAllUserInFor();
        /*遍历保存或更新*/
        for (int i =0; i<allUserInFor.size(); i++){
            /*解析数据*/
            JSONObject infor = allUserInFor.get(i);

            List<String> listResult = stringToCollectionUtil.convert(infor.get("result").toString());

            for (int b = 0;b<listResult.size();b++){

            }


            /*遍历每一次请求的所有用户信息*/
            for (int t =0;t<listResult.size();t++){

            JSONObject jsonObject = (JSONObject) JSON.parse(listResult.get(t));


            String ss = jsonObject.get("field_list").toString();

           // System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaa"+ss);

            List<String> listInFor = stringToCollectionUtil.convert(ss);

            /*保存单个人的数据*/
            JSONObject user = new JSONObject();

                for (int e =0;e<listInFor.size();e++){
                    JSONObject parse = (JSONObject) JSON.parse(listInFor.get(e));

                    String in ="";
                    try{
                        in = parse.get("label").toString();
                    }catch (Exception E){
                        in = "";
                    }

                    user.put(parse.get("field_code").toString(),in);

                }
                System.out.println("保存单个人的数据"+user.toJSONString());

                /**/
                String[] str = {"sys02-politicalStatus", "sys03-major", "sys00-reportManager", "sys02-certAddress", "sys07-familyMemberPhone", "sys02-realName", "sys01-employeeType", "sys07-familyMemberBirthday", "sys05-contractPeriodType", "sys00-deptIds", "sys00-jobNumber", "sys00-confirmJoinTime", "sys04-bankAccountNo", "sys05-contractRenewCount", "sys04-accountBank", "sys05-contractType", "3baafdf8-f74a-489e-bb03-fd094eecfc3b", "sys00-mainDeptId", "881e46fd-443f-4605-a45a-a16a5c817ddd", "sys05-firstContractStartTime", "sys05-nowContractStartTime", "sys07-familyMemberRelation", "sys00-workPlace", "sys03-graduationTime", "sys03-highestEdu", "sys00-dept", "sys01-positionLevel", "sys05-nowContractEndTime", "sys01-regularTime", "sys07-familyMemberGender", "sys02-residenceType", "sys00-mobile", "sys02-sexType", "sys01-probationPeriodType", "f325f768-f2f8-4f35-bc73-ce397d0cf404", "sys02-certEndTime", "sys09-personalHf", "sys05-firstContractEndTime", "sys00-mainDept", "dad51699-632f-4408-8381-976ec13b1dbc", "sys05-contractCompanyName", "sys00-tel", "sys06-urgentContactsName", "sys02-certNo", "sys09-personalSi", "sys02-birthTime", "sys03-graduateSchool", "sys02-address", "sys06-urgentContactsPhone", "sys01-planRegularTime", "sys07-familyMemberName", "sys00-reportManagerId", "sys06-urgentContactsRelation", "sys02-marriage", "sys00-remark", "sys00-position", "sys00-name", "sys02-joinWorkingTime", "sys01-employeeStatus", "sys02-nationType", "ce8d436b-1078-4a67-9eff-f237fa71c10e", "sys00-email"};

                /*补充空的数据*/
                for (int z = 0;z<str.length;z++){
                    try{
                        if (user.get(str[z])==null){
                            user.put(str[z],"");
                        }
                    }catch (Exception e){

                    }

                }
                UserInFor userInFor = UserInFor.builder()
                        .name(user.get("sys00-name").toString())
                        .email(user.get("sys00-email").toString())
                        .mobile(user.get("sys00-mobile").toString())
                        .jobNumber(user.get("sys00-jobNumber").toString())
                        .confirmJoinTime(user.get("sys00-confirmJoinTime").toString())
                        .workPlace(user.get("sys00-workPlace").toString())
                        .position(user.get("sys00-position").toString())
                        .politicalStatus(user.get("sys02-politicalStatus").toString())
                        .major(user.get("sys03-major").toString())
                        .nowContractEndTime(user.get("sys05-nowContractEndTime").toString())
                        .regularTime(user.get("sys01-regularTime").toString())

                        .familyMemberGender(user.get("sys07-familyMemberGender").toString())
                        .certAddress(user.get("sys02-certAddress").toString())
                        .familyMemberPhone(user.get("sys07-familyMemberPhone").toString())
                        .residenceType(user.get("sys02-residenceType").toString())
                        .realName(user.get("sys02-realName").toString())
                        .sexType(user.get("sys02-sexType").toString())
                        .employeeType(user.get("sys01-employeeType").toString())
                        .contractPeriodType(user.get("sys05-contractPeriodType").toString())
                        .probationPeriodType(user.get("sys01-probationPeriodType").toString())
                        .bankAccountN(user.get("sys04-bankAccountNo").toString())
                        .contractRenewCount(user.get("sys05-contractRenewCount").toString())
                        .accountBank(user.get("sys04-accountBank").toString())
                        .personalHf(user.get("sys09-personalHf").toString())
                        .contractType(user.get("sys05-contractType").toString())
                        .firstContractStartTime(user.get("sys05-firstContractEndTime").toString())
                        .contractCompanyName(user.get("sys05-contractCompanyName").toString())
                        .certNo(user.get("sys02-certNo").toString())
                        .personalSi(user.get("sys09-personalSi").toString())
                        .birthTime(user.get("sys02-birthTime").toString())
                        .firstContractStartTime(user.get("sys05-firstContractStartTime").toString())
                        .nowContractStartTime(user.get("sys05-nowContractStartTime").toString())
                        .graduateSchool(user.get("sys03-graduateSchool").toString())
                        .address(user.get("sys02-address").toString())
                        .urgentContactsPhone(user.get("sys06-urgentContactsPhone").toString())
                        .joinWorkingTime(user.get("sys02-joinWorkingTime").toString())
                        .graduationTime(user.get("sys03-graduationTime").toString())
                        .employeeStatus(user.get("sys01-employeeStatus").toString())
                        .nationType(user.get("sys02-nationType").toString())
                        .highestEdu(user.get("sys03-highestEdu").toString())
                        .deptIds(user.get("sys00-deptIds").toString())
                        .dept(user.get("sys00-dept").toString())
                        .mainDeptId(user.get("sys00-mainDeptId").toString())
                        .mainDept(user.get("sys00-mainDept").toString())
                        .build();
            /*查询数据库是该用户的数据，如果有，更新，没有新增*/
            boolean isNo = userInForDao.mobileGetUserInFor(userInFor.getMobile()).size()>0;

            if (isNo){
                /*更新*/
                boolean is = userInForDao.updateUserData(userInFor.getMobile(), userInFor) > 0;
                System.out.println("更新："+is);
                if (is==true){
                   updata+=1;
                }

            }else {
                /*新增*/
                userInForDao.save(userInFor);
                add+=1;

            }

            }

        }

        JSONObject json = new JSONObject();
        json.put("add",add);
        json.put("updata",updata);

        return json;
    }
    /*
    * @Author: yangzhi
    * @Date: 2023/2/1 14:03
    * @Description:根据手机号码查询用户信息
    * @return:
    */
    @Override
    public UserInFor getUserInFor(String mobile) {

        return  userInForDao.mobileGetUserInFor(mobile).get(0);
    }
}
