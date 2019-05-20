package com.tangyaya8.mmall.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class PayInfo {

  private long id;
  private long userId;
  private long orderNo;
  private long payPlatform;
  private String platformNumber;
  private String platformStatus;
  private LocalDateTime createTime;
  private LocalDateTime updateTime;


}
