package com.tangyaya8.mmall.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@ToString
@Data
@NotEmpty(message = "参数错误")
@Table(name = "mmall_category")
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;
    private long parentId = 0;
    @NotEmpty(message = "类别名称为空")
    private String categoryName;
    private long status;
    private Long sortOrder;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
