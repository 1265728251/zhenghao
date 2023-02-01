package com.xdap.zhenghao.demo.dao;

import com.definesys.mpaas.query.MpaasQuery;
import com.xdap.runtime.service.RuntimeDatasourceService;
import com.xdap.zhenghao.demo.entity.UserInFor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author:杨智
 * @create: 2023-02-01 11:27
 * @Description:员工信息中间表
 */
@Repository
@RequiredArgsConstructor
public class userInForDao {
    private final RuntimeDatasourceService runtimeDatasourceService;

    @Value("${apaas.single.tenantId}")
    private String tenantId;

    /*
    * @Author: yangzhi
    * @Date: 2023/2/1 11:34
    * @Description: 根据手机号码，查询用户信息表
    * @return:
    */
    public List<UserInFor> mobileGetUserInFor(String Mobile){
        MpaasQuery mpaasQuery = runtimeDatasourceService.buildBusinessMpaasQuery(
          "xdap_app_" + tenantId, "user_information"
        );

        List<UserInFor> userInFors =
                mpaasQuery
                        .eq("mobile",Mobile)
                        .table("user_information")
                        .doQuery(UserInFor.class);
        return userInFors;
    }

    /*
    * @Author: yangzhi
    * @Date: 2023/2/1 11:46
    * @Description: 保存用户信息表
    * @return:
    */
    public void save(UserInFor userInFor) {
        MpaasQuery mpaasQuery = runtimeDatasourceService.buildBusinessMpaasQuery(
                "xdap_app_" + tenantId, "user_information");
        mpaasQuery.table("user_information");
        mpaasQuery.doInsert(userInFor);
    }

    /*
    * @Author: yangzhi
    * @Date: 2023/2/1 11:50
    * @Description: 更新用户信息数据
    * @return:
    */

    public Integer updateUserData(String Mobile,UserInFor userInFor) {
        MpaasQuery mpaasQuery = runtimeDatasourceService.buildBusinessMpaasQuery(
                "xdap_app_" + tenantId, "user_information");
        return mpaasQuery.update("mobile", Mobile)

                .table("user_information")
                .doUpdate(userInFor);
    }
    /*
    * @Author: yangzhi
    * @Date: 2023/2/1 11:59
    * @Description: 删除数据
    * @return:
    */
    public Integer delete(UserInFor userInFor) {
        MpaasQuery mpaasQuery = runtimeDatasourceService.buildBusinessMpaasQuery(
                "xdap_app_" + tenantId, "user_information");
        return mpaasQuery
                .eq("mobile", userInFor.getMobile())
                .doDelete(UserInFor.class);
    }

}
