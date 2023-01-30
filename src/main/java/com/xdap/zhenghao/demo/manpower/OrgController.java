package com.xdap.zhenghao.demo.manpower;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/custom/common/tool")
public class OrgController {

    /*字符串拼接接口*/
    @ResponseBody
    @RequestMapping(value = "/reString", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String getString(@RequestBody JSONObject jsonParam) {
            System.out.println("请求的数据"+jsonParam.toJSONString());
            JSONObject jsonObject =new JSONObject();
            try {
                jsonObject.put("Certificate_no",jsonParam.get("Certificate_no").toString());
                jsonObject.put("majorString",jsonParam.get("major_list").toString());

            }catch (Exception e) {
                e.printStackTrace();
            }
            return jsonObject.toJSONString();
        }
}
