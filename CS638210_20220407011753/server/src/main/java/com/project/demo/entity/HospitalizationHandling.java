package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *住院办理：(HospitalizationHandling)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "HospitalizationHandling")
public class HospitalizationHandling implements Serializable {

    //HospitalizationHandling编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hospitalization_handling_id")
    private Integer hospitalization_handling_id;
    // 患者
    @Basic
    private Integer patient;
    // 姓名
    @Basic
    private String full_name;
    // 入住时间
    @Basic
    private Timestamp check_in_time;
    // 预缴金额
    @Basic
    private String prepayment_amount;
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
