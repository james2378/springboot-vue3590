package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *退费信息：(RefundInformation)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "RefundInformation")
public class RefundInformation implements Serializable {

    //RefundInformation编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "refund_information_id")
    private Integer refund_information_id;
    // 患者
    @Basic
    private Integer patient;
    // 姓名
    @Basic
    private String full_name;
    // 出院日期
    @Basic
    private Timestamp discharge_date_;
    // 预缴金额
    @Basic
    private String prepayment_amount;
    // 消费金额
    @Basic
    private Integer consumption_amount;
    // 退款金额
    @Basic
    private String refund_amount_;
    // 消费内容
    @Basic
    private String consumption_content;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
