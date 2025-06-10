package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *检查信息：(CheckInformation)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "CheckInformation")
public class CheckInformation implements Serializable {

    //CheckInformation编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "check_information_id")
    private Integer check_information_id;
    // 患者
    @Basic
    private Integer patient;
    // 姓名
    @Basic
    private String full_name;
    // 检查时间
    @Basic
    private Timestamp inspection_time;
    // 检查项目
    @Basic
    private String inspection_items;
    // 检查金额
    @Basic
    private Integer check_amount;
    // 医生编号
    @Basic
    private Integer doctor_number;
    // 支付状态
    @Basic
    private String pay_state;

    // 支付类型: 微信、支付宝、网银
    @Basic
    private String pay_type;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
