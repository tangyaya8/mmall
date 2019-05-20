package com.tangyaya8.mmall.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class Shipping {

  private long id;
  private long userId;
  private String receiverName;
  private String receiverPhone;
  private String receiverMobile;
  private String receiverProvince;
  private String receiverCity;
  private String receiverDistrict;
  private String receiverAddress;
  private String receiverZip;
  private LocalDateTime createTime;
  private LocalDateTime updateTime;


}
