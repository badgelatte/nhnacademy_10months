package com.nhnacademy;

import java.util.UUID;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class Options2 {
    public static void main(String[] args) throws InterruptedException {
        // UUID(Universally unique identifier) = 정보 식별을 위하여 사용되는 식별자, 아이디 생성하는거
        String publisherId = UUID.randomUUID().toString();
        try (IMqttClient client = new MqttClient("tcp://localhost:1883", publisherId)) {
            MqttConnectOptions options = new MqttConnectOptions();
            

            options.setAutomaticReconnect(true);
            options.setCleanSession(true);
            options.setConnectionTimeout(10);

            // 끊기면 보낼말 설정 / retained true라 설정하면 will이 retained로 설정됨
            // -> 연결에 대한 최후의 유언장"LWT(Last Will and Testament)"을 설정 : 
            // client가 예기치않게 서버와의 연결이 끊어지면 server는 제공된 세부 정보를 사용하여 자체적으로 msg 게시
            // topic - publish할 topic, payload - msg에 넣은 byte payload, qos - msg 발행할 때의 service 품질(0,1,2), retained - msg 유지해야하는가의 여부
            options.setWill("test/will", "Disconnected".getBytes(), 1, true);

            // server에게 연결하기
            client.connect(options);

            // topic = "test/a/b/c"로, msg = "Hello"로 보낸다
            client.publish("test/a/b/c", new MqttMessage("Hello".getBytes()));
            client.subscribe("test/a/b/d", (topic, msg) -> {
                System.out.println("Message received : " + topic);
            });
            client.subscribe("test/a/e/d", (topic, msg) -> {
                System.out.println("Message received : " + topic);
            });

            while (!Thread.currentThread().isInterrupted()) {
                Thread.sleep(10000);
            }

            client.disconnect();
        } catch (MqttException e) {
            e.printStackTrace();
        }

    }
}