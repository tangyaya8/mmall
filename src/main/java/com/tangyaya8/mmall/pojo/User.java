package com.tangyaya8.mmall.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tangyaya8.mmall.common.UserRole;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Table(name = "mmall_user")
@Entity
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})//会导致jackson序列化出错
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(updatable = false)
    @NotEmpty(message = "用户名不能为空")
    private String userName;
    @NotEmpty(message = "密码不能为空")
    private String password;
    @NotEmpty(message = "邮箱不能为空")
    @Email(message = "email格式不正确")
    private String email;
    @Pattern(message = "手机号格式不正确", regexp = "^(1[1-9]\\d{9}|[4,8]00\\d{7}|1010\\d{4}|0\\d{2}[2-9]\\d{7}(\\d{1,4})?|0\\d{3}[2-9]\\d{6,7}(\\d{1,4})?)$")
    @NotEmpty(message = "手机号不能为空")
    private String phone;
    private String question;
    private String answer;
    private UserRole role;
    @Column(updatable = false, insertable = false)
    private LocalDateTime createTime;
    @Column(updatable = false, insertable = false)
    private LocalDateTime updateTime;


}
