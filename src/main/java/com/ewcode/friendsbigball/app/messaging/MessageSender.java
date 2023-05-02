package com.ewcode.friendsbigball.app.messaging;

public interface MessageSender {
    void send(String destinationName, byte[] message);
}
