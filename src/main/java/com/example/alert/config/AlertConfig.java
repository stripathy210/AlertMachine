package com.example.alert.config;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class AlertConfig {
  private ConfigurationType configurationType;
  private int count;
  private String duration;
}
