package com.example.alert.strategy;

import com.example.alert.config.ParentAlertConfig;
import com.example.alert.dispatch.DispatchFactory;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class BucketedWindowConfigStrategy implements ConfigStrategy{
  private Map<String, Integer> alertMap = new HashMap<>();
  private Integer previousMinutes;

  private DispatchFactory dispatchFactory;

  public BucketedWindowConfigStrategy(DispatchFactory dispatchFactory) {
    this.dispatchFactory = dispatchFactory;
  }
  @Override
  public void execute(String client, ParentAlertConfig alertConfig) {
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    Integer currentMinutes = timestamp.getMinutes();
    if (previousMinutes == null || previousMinutes != currentMinutes) {
      previousMinutes = currentMinutes;
      alertMap.put(client, 0);
      return;
    }
    alertMap.put(client, alertMap.get(client)+1);
    if (alertMap.get(client) >= alertConfig.getAlertConfig().getCount()) {
        dispatchFactory.dispatch(alertConfig.getEventType(),alertConfig.getDispatchStrategy());
    }
  }
}
