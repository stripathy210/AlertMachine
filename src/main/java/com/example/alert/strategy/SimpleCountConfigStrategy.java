package com.example.alert.strategy;

import com.example.alert.config.AlertConfig;
import com.example.alert.config.ParentAlertConfig;
import com.example.alert.dispatch.DispatchFactory;
import java.util.HashMap;
import java.util.Map;

public class SimpleCountConfigStrategy implements ConfigStrategy{

  private Map<String, Integer> alertMap = new HashMap<>();
  private DispatchFactory dispatchFactory;

  public SimpleCountConfigStrategy(DispatchFactory dispatchFactory) {
    this.dispatchFactory = dispatchFactory;
  }

  @Override
  public void execute(String client, ParentAlertConfig alertConfig) {
      alertMap.put(client, alertMap.get(client) == null ? 0 :alertMap.get(client)+1);
      if (alertMap.get(client) >= alertConfig.getAlertConfig().getCount()) {
          dispatchFactory.dispatch(alertConfig.getEventType(),alertConfig.getDispatchStrategy());
      }
  }
}
