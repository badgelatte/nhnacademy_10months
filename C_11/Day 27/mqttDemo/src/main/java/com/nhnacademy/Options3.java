package com.nhnacademy;

import java.util.UUID;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class Options3 {
    // MqttConnectOptions 출처 : https://eclipse.dev/paho/files/javadoc/org/eclipse/paho/client/mqttv3/MqttConnectOptions.html
    public static void main(String[] args) throws InterruptedException {
        // UUID(Universally unique identifier) = 정보 식별을 위하여 사용되는 식별자, 아이디 생성하는거
        String publisherId = UUID.randomUUID().toString();
        try (IMqttClient client = new MqttClient("tcp://localhost:1883", publisherId)) {
            MqttConnectOptions options = new MqttConnectOptions();
            // 정용님이 설명해 주신 거
            // mqtt -  byte 단위 통신, socket 연결해서 연결되면 msg 보내 주는 게 다 byte형식이다
            // http - get/어쩌다 던져주기 응답도 규격에 맞춰서하는데 mqtt 연결된게 있으면 socket으로 payload랑 topic 던져준다
            // 통신의 대부분은 tcp , tcp통신방식으로 mqtt, httq가 있다
            // 통신 - 연결이 되어야함
            // 하드웨어 연결하는 젤 낮은 방법 - tcp 연결 -> mqtt로 연결 방법 순
            // mqtt protocol 약속 



            options.setAutomaticReconnect(true);
            options.setCleanSession(true);
            options.setConnectionTimeout(10);
            // 끊기면 보낼말 설정 / retained true라 설정하면 will이 retained로 설정됨
            options.setWill("test/will", "Disconnected".getBytes(), 1, false);

            // 연결 유지 간격을 설정 -> 초 단위로 측정되는 이 값은 보내거나 받는 메시지 간의 최대 시간 간격을 정의
            options.setKeepAliveInterval(1000);

            // client에게 연결하기
            client.connect(options);

            // topic = "test/a/b/c"로, msg = "Hello"로 보낸다
            client.publish("test/a/b/c", new MqttMessage("Hello".getBytes()));
            client.subscribe("test/a/b/d", (topic, msg) -> {
            System.out.println("Message received : " + topic);
            });

            while (!Thread.currentThread().isInterrupted()) {
                Thread.sleep(100);
            }

            client.disconnect();
        } catch (MqttException e) {
            e.printStackTrace();
        }

    }
}