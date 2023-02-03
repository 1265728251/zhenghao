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
        return mpaasQuery.update("email",userInFor.getEmail())
                .update("mobile", userInFor.getMobile())
                .update("job_number", userInFor.getJobNumber())
                .update("confirm_join_time", userInFor.getConfirmJoinTime())
                .update("work_place", userInFor.getWorkPlace())
                .update("position", userInFor.getPosition())
                .update("political_status", userInFor.getPoliticalStatus())
                .update("major", userInFor.getMajor())
                .update("now_contract_end_time", userInFor.getNowContractEndTime())
                .update("regular_time", userInFor.getRegularTime())
                .update("family_member_gender", userInFor.getFamilyMemberGender())
                .update("cert_address", userInFor.getCertAddress())
                .update("family_member_phone", userInFor.getFamilyMemberPhone())
                .update("residence_type", userInFor.getResidenceType())
                .update("real_name", userInFor.getRealName())
                .update("sex_type", userInFor.getSexType())
                .update("employee_type", userInFor.getEmployeeType())
                .update("contract_period_type", userInFor.getContractPeriodType())
                .update("probation_period_type", userInFor.getProbationPeriodType())
                .update("bank_account_n", userInFor.getBankAccountN())
                .update("contract_renew_count", userInFor.getContractRenewCount())
                .update("account_bank", userInFor.getAccountBank())
                .update("personal_hf", userInFor.getPersonalHf())
                .update("contract_type", userInFor.getContractType())
                .update("first_contract_end_time", userInFor.getFirstContractEndTime())
                .update("contract_company_name", userInFor.getContractCompanyName())
                .update("urgent_contacts_name", userInFor.getUrgentContactsName())
                .update("cert_no", userInFor.getCertNo())
                .update("personal_si", userInFor.getPersonalSi())
                .update("birth_time", userInFor.getBirthTime())
                .update("first_contract_start_time", userInFor.getFirstContractStartTime())
                .update("now_contract_start_time", userInFor.getNowContractStartTime())
                .update("graduate_school", userInFor.getGraduateSchool())
                .update("address", userInFor.getAddress())
                .update("urgent_contacts_phone", userInFor.getUrgentContactsPhone())
                .update("graduation_time",userInFor.getGraduationTime())
                .update("join_working_time",userInFor.getJoinWorkingTime())
                .update("employee_status",userInFor.getEmployeeStatus())
                .update("nation_type",userInFor.getNationType())
                .update("highest_edu",userInFor.getHighestEdu())
                .update("dept_ids",userInFor.getDeptIds())
                .update("dept",userInFor.getDept())
                .update("main_dept_id",userInFor.getMainDeptId())
                .update("main_dept",userInFor.getMainDept())
                .update("user_title",userInFor.getUserTitle())

                .in("mobile",userInFor.getMobile())
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
