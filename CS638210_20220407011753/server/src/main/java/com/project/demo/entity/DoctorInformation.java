package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *医生信息：(DoctorInformation)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "DoctorInformation")
public class DoctorInformation implements Serializable {

    //DoctorInformation编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doctor_information_id")
    private Integer doctor_information_id;
    // 医生编号
    @Basic
    private Integer doctor_number;
    // 医生姓名
    @Basic
    private String name_of_doctor;
    // 职业
    @Basic
    private String occupation;
    // 科室名称
    @Basic
    private String department_name;
    // 擅长领域
    @Basic
    private String areas_of_expertise;
    // 医生图片
    @Basic
    private String doctor_picture;
    // 职业经历
    @Basic
    private String professional_experience;
    // 荣誉嘉奖
    @Basic
    private String honorary_awards;
    // 学术成就
    @Basic
    private String academic_achievements;
    // 医生风采
    @Basic
    private String doctor_style;
    // 医生简介
    @Basic
    private String doctor_profile;
    // 点击数
    @Basic
    private Integer hits;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
