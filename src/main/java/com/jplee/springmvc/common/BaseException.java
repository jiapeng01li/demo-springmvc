package com.jplee.springmvc.common;
/*
 *自定义异常类
 */
public class BaseException extends RuntimeException
{
  private static final long serialVersionUID = 4290784890876524392L;
  private String errorDesc;
  private String errorCode;

  public String getErrorDesc()
  {
    return this.errorDesc;
  }

  public String getErrorCode() {
    return this.errorCode;
  }

  public BaseException()
  {
  }

  public BaseException(String message) {
    super(message);
    this.errorDesc = message;
  }

  public BaseException(String message, Throwable cause) {
    super(message, cause);
    this.errorDesc = message;
  }

  public BaseException(String message, String errorCode)
  {
    super(message);
    this.errorDesc = message;
    this.errorCode = errorCode;
  }

  public BaseException(String message, String errorCode, Throwable cause) {
    super(message, cause);
    this.errorDesc = message;
    this.errorCode = errorCode;
  }
  
}