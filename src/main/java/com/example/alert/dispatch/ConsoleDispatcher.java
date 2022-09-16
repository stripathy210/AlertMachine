package com.example.alert.dispatch;

public class ConsoleDispatcher implements DispatchEngine {

  @Override
  public void dispatch(String message) {
    System.out.println(message);
  }
}
