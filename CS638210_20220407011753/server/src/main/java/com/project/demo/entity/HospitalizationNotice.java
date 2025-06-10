package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *住院通知：(HospitalizationNotice)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "HospitalizationNotice")
public class HospitalizationNotice implements Serializable {

    //HospitalizationNotice编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hospitalization_notice_id")
    private Integer hospitalization_notice_id;
    // 患者
    @Basic
    private Integer patient;
    // 姓名
    @Basic
    private String full_name;
    // 预缴金额
    @Basic
    private Integer prepayment_amount;
    // 医生编号
    @Basic
    private Integer doctor_number;
    // 诊断结果
    @Basic
    private String diagnostic_results;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
