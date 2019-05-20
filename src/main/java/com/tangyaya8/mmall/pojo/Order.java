package com.tangyaya8.mmall.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Table(name = "mmall_order")
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;
    private long orderNo;
    private long userId;
    private long shippingId;
    private BigDecimal payment;
    private long paymentType;
    private long postage;
    private long status;
    private LocalDateTime paymentTime;
    private LocalDateTime sendTime;
    private LocalDateTime endTime;
    private LocalDateTime closeTime;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
