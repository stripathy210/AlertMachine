package com.example.alert.dispatch;

import com.example.alert.config.DispatchStrategy;
import com.example.alert.config.DispatchType;

public class DispatchFactory {

  private DispatchEngine dispatchEngine;
  public void dispatch(String event, DispatchStrategy dispatchStrategy) {
    if (dispatchStrategy.getDispatchType() == DispatchType.CONSOLE) {
      dispatchEngine = new ConsoleDispatcher();
    }
    dispatchEngine.dispatch(event + "_" + dispatchStrategy.getMessage());
  }
}
