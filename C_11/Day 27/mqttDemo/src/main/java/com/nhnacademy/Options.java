package com.nhnacademy;

import java.util.UUID;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class Options {
    public static void main(String[] args) throws InterruptedException {
        // UUID(Universally unique identifier) = 정보 식별을 위하여 사용되는 식별자, 아이디 생성하는거
        String publisherId = UUID.randomUUID().toString();

        // IMqttClient eclipse : https://eclipse.dev/paho/files/javadoc/org/eclipse/paho/client/mqttv3/IMqttClient.html
        // MqttClient exlipse : https://eclipse.dev/paho/files/javadoc/org/eclipse/paho/client/mqttv3/MqttClient.html

        try (IMqttClient client = new MqttClient("tcp://localhost:1883", publisherId)) {
            // new MqttClient -> MQTT 서버와 통신하는 데 사용할 수 있는 MqttClient를 만든다
            // client가 server에 연결하는 방법을 제어하는 ​​옵션 세트를 보유
            MqttConnectOptions options = new MqttConnectOptions();

            options.setAutomaticReconnect(true);
            options.setCleanSession(true);
            options.setConnectionTimeout(10);

            // client에게 연결하기 -> 기본 옵션을 사용하여 MQTT 서버에 연결
            client.connect(options);

            // topic = "test/a/b/c"로, msg = "Hello"로 보낸다
            client.publish("test/a/b/c", new MqttMessage("Hello".getBytes()));

            // subscribe는 topic이 "test/a/b/d"로 topic과 msg을 가지고 여기에 "Message received :" topic으로 출력하겠다
            client.subscribe("test/a/b/d", (topic, msg) -> {
                System.out.println("Message received : " + topic);
            });

            // 현재 thread가 멈추지 않았다면 thread.sleep(100)
            while (!Thread.currentThread().isInterrupted()) {
                Thread.sleep(100);
            }

            // server와 연결을 끊는다
            client.disconnect();
        } catch (MqttException e) {
            e.printStackTrace();
        }

    }
}