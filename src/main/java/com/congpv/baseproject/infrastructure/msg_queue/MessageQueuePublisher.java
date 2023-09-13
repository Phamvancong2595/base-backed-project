package com.congpv.baseproject.infrastructure.msg_queue;

public interface MessageQueuePublisher {

  void publish(String topic, Message message) throws MessageQueueException;

}
