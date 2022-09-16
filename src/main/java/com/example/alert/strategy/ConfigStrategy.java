package com.example.alert.strategy;

import com.example.alert.config.AlertConfig;
import com.example.alert.config.ParentAlertConfig;
import java.sql.Timestamp;

public interface ConfigStrategy {

  void execute(String client, ParentAlertConfig alertConfig);

}
