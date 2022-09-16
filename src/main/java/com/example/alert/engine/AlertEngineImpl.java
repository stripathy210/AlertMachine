package com.example.alert.engine;

import com.example.alert.config.AlertConfig;
import com.example.alert.config.ConfigurationType;
import com.example.alert.config.ParentAlertConfig;
import com.example.alert.dispatch.DispatchFactory;
import com.example.alert.strategy.BucketedWindowConfigStrategy;
import com.example.alert.strategy.MovingWindowStrategy;
import com.example.alert.strategy.SimpleCountConfigStrategy;
import java.util.Map;

public class AlertEngineImpl implements AlertEngine{

  private Map<String, ParentAlertConfig> clientStrategyMap;
  private SimpleCountConfigStrategy simpleCountConfigStrategy;
  private BucketedWindowConfigStrategy bucketedWindowConfigStrategy;
  private MovingWindowStrategy movingWindowStrategy;
  public AlertEngineImpl(Map<String, ParentAlertConfig> clientStrategyMap, DispatchFactory dispatchFactory) {
    this.clientStrategyMap = clientStrategyMap;
    simpleCountConfigStrategy = new SimpleCountConfigStrategy(dispatchFactory);
    bucketedWindowConfigStrategy = new BucketedWindowConfigStrategy(dispatchFactory);
    movingWindowStrategy = new MovingWindowStrategy(dispatchFactory);

  }

  @Override
  public void addAlert(String client) {

    if (clientStrategyMap.get(client).getAlertConfig().getConfigurationType() == ConfigurationType.SIMPLE_COUNT) {
      simpleCountConfigStrategy.execute(client, clientStrategyMap.get(client));
    } else if (clientStrategyMap.get(client).getAlertConfig().getConfigurationType()
        == ConfigurationType.BUCKETED_WINDOW) {
      bucketedWindowConfigStrategy.execute(client, clientStrategyMap.get(client));
    } else if (clientStrategyMap.get(client).getAlertConfig().getConfigurationType()
        == ConfigurationType.MOVING_WINDOW) {
      movingWindowStrategy.execute(client, clientStrategyMap.get(client));
    }

  }
}
