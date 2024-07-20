package com.rudra.response;

import lombok.Data;

@Data
public class ErrorResponse  extends Exception{
  private   String message;
  private Integer statusCode;

}
