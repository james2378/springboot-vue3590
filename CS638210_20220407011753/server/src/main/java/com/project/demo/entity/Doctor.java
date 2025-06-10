package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *医生：(Doctor)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "Doctor")
public class Doctor implements Serializable {

    //Doctor编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doctor_id")
    private Integer doctor_id;
    // 医生编号
    @Basic
    private String doctor_number;
    // 医生姓名
    @Basic
    private String name_of_doctor;
    // 用户编号
    @Id
    @Column(name = "user_id")
    private Integer userId;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
