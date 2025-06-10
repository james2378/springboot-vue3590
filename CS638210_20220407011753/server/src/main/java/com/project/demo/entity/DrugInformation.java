package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *药品信息：(DrugInformation)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "DrugInformation")
public class DrugInformation implements Serializable {

    //DrugInformation编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "drug_information_id")
    private Integer drug_information_id;
    // 药品编号
    @Basic
    private String drug_number;
    // 药品名称
    @Basic
    private String drug_name;
    // 药品封面
    @Basic
    private String drug_cover;
    // 药品类型
    @Basic
    private String drug_type;
    // 单价
    @Basic
    private Integer unit_price;
    // 主治功能
    @Basic
    private String main_function;
    // 药品介绍
    @Basic
    private String drug_introduction;
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
