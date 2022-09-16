package com.example.alert.client;

import com.example.alert.config.AlertConfig;
import com.example.alert.config.ConfigurationType;
import com.example.alert.config.DispatchStrategy;
import com.example.alert.config.DispatchType;
import com.example.alert.config.ParentAlertConfig;
import java.util.HashMap;
import java.util.Map;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ClientParser {
  public Map<String, ParentAlertConfig> parse() {
    Map<String, ParentAlertConfig> parentAlertConfigMap = new HashMap<>();
//    addSimpleCountStrategyClient(parentAlertConfigMap);
//    addBucketStrategyClient(parentAlertConfigMap);
    addMovingStrategyClient(parentAlertConfigMap);
    return parentAlertConfigMap;
  }

  private void addBucketStrategyClient(Map<String, ParentAlertConfig> parentAlertConfigMap) {
    AlertConfig alertConfig = AlertConfig.builder().configurationType(ConfigurationType.BUCKETED_WINDOW)
        .count(2)
        .build();

    DispatchStrategy dispatchStrategy = DispatchStrategy.builder().dispatchType(DispatchType.CONSOLE).message("issue in payment").build();

    ParentAlertConfig parentAlertConfig = ParentAlertConfig.builder().client("B")
        .eventType("PAYMENT_EXCEPTION")
        .alertConfig(alertConfig)
        .dispatchStrategy(dispatchStrategy)
        .build();
    parentAlertConfigMap.put("B", parentAlertConfig);
  }

  private void addMovingStrategyClient(Map<String, ParentAlertConfig> parentAlertConfigMap) {
    AlertConfig alertConfig = AlertConfig.builder().configurationType(ConfigurationType.MOVING_WINDOW)
        .count(4)
        .duration("11")
        .build();

    DispatchStrategy dispatchStrategy = DispatchStrategy.builder().dispatchType(DispatchType.CONSOLE).message("issue in payment").build();

    ParentAlertConfig parentAlertConfig = ParentAlertConfig.builder().client("M")
        .eventType("PAYMENT_EXCEPTION")
        .alertConfig(alertConfig)
        .dispatchStrategy(dispatchStrategy)
        .build();
    parentAlertConfigMap.put("M", parentAlertConfig);
  }
  private void addSimpleCountStrategyClient(Map<String, ParentAlertConfig> parentAlertConfigMap) {
    AlertConfig alertConfig = AlertConfig.builder().configurationType(ConfigurationType.SIMPLE_COUNT)
        .count(10)
        .build();

    DispatchStrategy dispatchStrategy = DispatchStrategy.builder().dispatchType(DispatchType.CONSOLE).message("issue in payment").build();

    ParentAlertConfig parentAlertConfig = ParentAlertConfig.builder().client("S")
        .eventType("PAYMENT_EXCEPTION")
        .alertConfig(alertConfig)
        .dispatchStrategy(dispatchStrategy)
        .build();
    parentAlertConfigMap.put("S", parentAlertConfig);
  }

}
