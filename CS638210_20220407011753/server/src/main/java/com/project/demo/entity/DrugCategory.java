package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *药品类别：(DrugCategory)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "DrugCategory")
public class DrugCategory implements Serializable {

    //DrugCategory编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "drug_category_id")
    private Integer drug_category_id;
    // 药品类型
    @Basic
    private String drug_type;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
