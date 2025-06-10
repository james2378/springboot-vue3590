package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *挂号预约：(RegistrationReservation)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "RegistrationReservation")
public class RegistrationReservation implements Serializable {

    //RegistrationReservation编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "registration_reservation_id")
    private Integer registration_reservation_id;
    // 预约编号
    @Basic
    private String reservation_number;
    // 医生编号
    @Basic
    private Integer doctor_number;
    // 医生姓名
    @Basic
    private String name_of_doctor;
    // 科室名称
    @Basic
    private String department_name;
    // 患者
    @Basic
    private Integer patient;
    // 姓名
    @Basic
    private String full_name;
    // 预约时间
    @Basic
    private Timestamp time_of_appointment;
    // 审核状态
    @Basic
    private String examine_state;
    // 审核回复
    @Basic
    private String examine_reply;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
