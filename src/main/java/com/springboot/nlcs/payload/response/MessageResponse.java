package com.springboot.nlcs.payload.response;

public class MessageResponse {
  private String message;  //<=> private String token;

  public MessageResponse(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
