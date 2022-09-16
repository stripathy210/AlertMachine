package com.example.alert;

import com.example.alert.client.ClientParser;
import com.example.alert.config.ParentAlertConfig;
import com.example.alert.dispatch.DispatchFactory;
import com.example.alert.engine.AlertEngine;
import com.example.alert.engine.AlertEngineImpl;
import java.time.LocalDateTime;
import java.util.Map;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AlertApplication {
  private AlertEngine alertEngine;
  public void execute() {
    Map<String, ParentAlertConfig> parentAlertConfigMap = ClientParser.parse();
    DispatchFactory dispatchFactory = new DispatchFactory();
    alertEngine = new AlertEngineImpl(parentAlertConfigMap, dispatchFactory);
//    addSimpleAlert();
//    addBucketAlert();
    addMovingAlert();

  }

  public static void main(String[] args) {
    AlertApplication alertApplication = new AlertApplication();
    alertApplication.execute();
  }

  private void addSimpleAlert() {
    for (int i = 0; i < 15; i++) {
      alertEngine.addAlert("X");
    }
  }

  private void addMovingAlert() {
    for (int i = 0; i < 15; i++) {
      try {
        Thread.sleep(5000);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }

      System.out.println("Sending Moving Alert " + i);
      alertEngine.addAlert("M");
    }
  }

  private void addBucketAlert() {
    for (int i = 0; i < 15; i++) {
      try {
        Thread.sleep(5000);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
      alertEngine.addAlert("Y");
    }

  }

}
