package com.hkw.mpdemo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

@Data
public class User {
    @TableId(type = IdType.ID_WORKER)// 主键策略:mp自动生成的19位的id
    private Long id;
    private String name;
    private Integer age;
    private String email;

    // 加入两个需要自动生成的属性
    // 创建时间
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    // 修改时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @TableField(fill = FieldFill.INSERT)
    @Version
    private Integer version;

    @TableField(fill = FieldFill.INSERT)
    @TableLogic
    private Integer deleted;
}
