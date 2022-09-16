package com.example.alert.config;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class ParentAlertConfig {
  private String client;
  private String eventType;
  private AlertConfig alertConfig;
  private DispatchStrategy dispatchStrategy;

}
