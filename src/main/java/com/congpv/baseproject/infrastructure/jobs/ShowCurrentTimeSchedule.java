package com.congpv.baseproject.infrastructure.jobs;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ShowCurrentTimeSchedule {

  private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

  @Scheduled(initialDelay = 1000, fixedDelay = 2000)
  public void showCurrenTime() {
    Date d = new Date();
    System.out.println(df.format(d));
  }
  @Scheduled(cron = "15 * * * * ?", zone = "Asia/Saigon")
  public void doSomethingInEachMinutes() {
    System.out.println("Read a book");
  }
}
