# 工程简介

#中间表sql
CREATE TABLE `user_information` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
`email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
`mobile` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
`job_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
`confirm_join_time` date NULL,
`work_place` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
`position` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
`political_status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
`major` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
`now_contract_end_time` date NULL,
`regular_time` date NULL,
`family_member_gender` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
`cert_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
`family_member_phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
`residence_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
`real_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
`sex_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
`employee_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
`contract_period_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
`probation_period_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
`bank_account_n` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
`contract_renew_count` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
`account_bank` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
`personal_hf` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
`contract_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
`first_contract_end_time` date NULL,
`contract_company_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
`urgent_contacts_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
`cert_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
`personal_si` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
`birth_time` date NULL,
`first_contract_start_time` date NULL,
`now_contract_start_time` date NULL,
`graduate_school` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
`address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
`urgent_contacts_phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
`graduation_time` date NULL,
`join_working_time` date NULL,
`employee_status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
`nation_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
`highest_edu` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
`dept_ids` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
`dept` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
`main_dept_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
`main_dept` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
`user_title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
PRIMARY KEY (`id`)
) ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_general_ci
AUTO_INCREMENT=538
ROW_FORMAT=DYNAMIC
AVG_ROW_LENGTH=488;