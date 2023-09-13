package com.congpv.baseproject;

import com.congpv.baseproject.infrastructure.msg_queue.KafkaPublisher;
import com.congpv.baseproject.infrastructure.msg_queue.vo.DemoMessage;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class KafkaPublisherTest {

  @Value("${kafka.topic.demo_topic}")
  private String demoTopic;

  @Autowired
  private KafkaPublisher kafkaPublisher;
  @Test
  void test() {
    DemoMessage message = DemoMessage.builder().id(1L).created(LocalDateTime.now()).build();
    kafkaPublisher.publish(demoTopic, message);
  }
}
