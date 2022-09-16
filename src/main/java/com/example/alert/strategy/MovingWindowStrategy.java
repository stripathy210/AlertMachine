package com.example.alert.strategy;

import com.example.alert.config.ParentAlertConfig;
import com.example.alert.dispatch.DispatchFactory;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class MovingWindowStrategy implements ConfigStrategy{
  private Map<String, PriorityQueue<Timestamp>> alertMap = new HashMap<>();
  private DispatchFactory dispatchFactory;

  public MovingWindowStrategy(DispatchFactory dispatchFactory) {
    this.dispatchFactory = dispatchFactory;
  }

  @Override
  public void execute(String client, ParentAlertConfig alertConfig) {
    Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
    Timestamp cutOffTimeStamp = Timestamp.valueOf(
        LocalDateTime.now().minusSeconds(Integer.parseInt(alertConfig.getAlertConfig().getDuration())));
    if (alertMap.containsKey(client)) {
      PriorityQueue<Timestamp> heap = alertMap.get(client);

//      TODO Handle for Minutes also.
//      Remove till we are in window
        while (!heap.isEmpty() && cutOffTimeStamp.after(heap.peek())) {
          heap.poll();
        }

        heap.add(currentTimestamp);
      if (heap.size() >= alertConfig.getAlertConfig().getCount()) {
        dispatchFactory.dispatch(alertConfig.getEventType(),alertConfig.getDispatchStrategy());
      }
    }
    else {
      PriorityQueue<Timestamp> heap = new PriorityQueue<>();
      heap.add(currentTimestamp);
      alertMap.put(client, heap);
    }
  }
}
