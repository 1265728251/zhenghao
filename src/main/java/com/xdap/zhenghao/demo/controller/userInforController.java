package com.xdap.zhenghao.demo.controller;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xdap.zhenghao.demo.entity.UserInFor;
import com.xdap.zhenghao.demo.service.IGetEmployeeInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/custom/common/tool")
public class userInforController {

    @Autowired
    private IGetEmployeeInformationService iGetEmployeeInformationService;
    /*字符串拼接接口*/
    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String saveUser(@RequestBody JSONObject jsonParam) {
            System.out.println("请求的数据"+jsonParam.toJSONString());
            JSONObject jsonObject =new JSONObject();
            try {
                boolean isNo =  iGetEmployeeInformationService.save();
                jsonObject.put("save",isNo);

            }catch (Exception e) {
                e.printStackTrace();
            }
            return jsonObject.toJSONString();
        }

    /*字符串拼接接口*/
    @ResponseBody
    @RequestMapping(value = "/getUserInFor", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String getuser(@RequestBody JSONObject jsonParam) {
        System.out.println("请求的数据"+jsonParam.toJSONString());
        JSONObject jsonObject =new JSONObject();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            UserInFor user = iGetEmployeeInformationService.getUserInFor(jsonObject.get("mobile").toString());
            // 将实体类转换为json
            String s = objectMapper.writeValueAsString(user);
            System.out.println("请求返回的数据是："+s);
            jsonObject = (JSONObject) JSON.parse(s);

        }catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject.toJSONString();
    }
}
