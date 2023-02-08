package com.xdap.zhenghao.demo.controller;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xdap.zhenghao.demo.entity.UserInFor;
import com.xdap.zhenghao.demo.service.IGetEmployeeInformationService;
import com.xdap.zhenghao.demo.utils.highestEdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/custom/common/tool")
public class userInforController {

    @Autowired
    private IGetEmployeeInformationService iGetEmployeeInformationService;
    @Autowired
    private highestEdUtils highestEdUtils;

    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String saveUser(@RequestBody JSONObject jsonParam) {
            System.out.println("请求的数据"+jsonParam.toJSONString());
            JSONObject jsonObject =new JSONObject();
            try {
                iGetEmployeeInformationService.save();
                boolean isNo = true ;
                jsonObject.put("save",isNo);

            }catch (Exception e) {
                e.printStackTrace();
            }
            return jsonObject.toJSONString();
        }


    @ResponseBody
    @RequestMapping(value = "/getUserInFor", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String getuser(@RequestBody JSONObject jsonParam) {
        System.out.println("请求的数据"+jsonParam.toJSONString());
        JSONObject jsonObject =new JSONObject();
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            UserInFor user = iGetEmployeeInformationService.getUserInFor("+86-"+jsonParam.get("mobile").toString());
            // 将实体类转换为json
            String s = objectMapper.writeValueAsString(highestEdUtils.highest(user));
            System.out.println("请求返回的数据是："+s);
            jsonObject = (JSONObject) JSON.parse(s);

            jsonObject.put("data",jsonObject.toJSONString());

        }catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject.toJSONString();
    }
}
