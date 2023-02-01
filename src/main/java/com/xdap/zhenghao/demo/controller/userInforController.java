package com.xdap.zhenghao.demo.controller;
import com.alibaba.fastjson.JSONObject;
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
    @RequestMapping(value = "/reString", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String getString(@RequestBody JSONObject jsonParam) {
            System.out.println("请求的数据"+jsonParam.toJSONString());
            JSONObject jsonObject =new JSONObject();
            try {
                String s= iGetEmployeeInformationService.getAllUserInFor().toString();
                System.out.println("==============================================="+s);
                jsonObject.put("useridList",s);

            }catch (Exception e) {
                e.printStackTrace();
            }
            return jsonObject.toJSONString();
        }
}
