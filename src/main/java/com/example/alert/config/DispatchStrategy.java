package com.example.alert.config;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class DispatchStrategy {
  private DispatchType dispatchType;
  private String message;

}
