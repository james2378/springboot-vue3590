package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *就诊单：(VisitForm)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "VisitForm")
public class VisitForm implements Serializable {

    //VisitForm编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "visit_form_id")
    private Integer visit_form_id;
    // 医生编号
    @Basic
    private Integer doctor_number;
    // 医生姓名
    @Basic
    private String name_of_doctor;
    // 患者
    @Basic
    private Integer patient;
    // 姓名
    @Basic
    private String full_name;
    // 就诊时间
    @Basic
    private Timestamp visit_time;
    // 药品金额品
    @Basic
    private Integer pharmaceutical_products;
    // 诊断结果
    @Basic
    private String diagnostic_results;
    // 处方
    @Basic
    private String prescription;
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
