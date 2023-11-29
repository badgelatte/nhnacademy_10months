package com.nhnacademy;

import java.util.UUID;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class Main {
    public static void main(String[] args) {
        // UUID(Universally unique identifier) = 정보 식별을 위하여 사용되는 식별자, 아이디 생성하는거
        String publisherId = UUID.randomUUID().toString();
        try (IMqttClient client = new MqttClient("tcp://localhost:1883", publisherId)) {
            MqttConnectOptions options = new MqttConnectOptions();
            // setKeepAliveInterval()
            // connect을 계속 살리기 위해서, keepalive를 사용하면 특정 시간만큼 시간을 재는데, 계속 끊는 시간까지의 카운트를 초기화한다

            // password - SSL, TLS 같은 거, 인증 관련, 보안 관련된 거

            // setServerURIs -> URI 설정 가능

            // setWill last will 끊긴 시점에서 메시지를 날릴 수 있다 -> publisher가 끊어졌을때 알릴 수 있는 방법이 없다
            // client가 주기적으로 pub하는데 끊겼을때 바로 연결되야할때가 있다
            // (ex. 화제 센서->주기적으로 살아있음을 확인한다, 이상이 생기면 메세지를 보냄, 안 보내면 살아있다 생각함)
            // 끊어지면 아무것도 모른채로 살아있다 생각한다 -> 끊어지면 알려줘야함 연결하게끔 -> 끊어져서 메세지 오면 행동을 취할 수 있게끔 할 수 있다



            // client가 연결이 끊어진 경우 자동으로 서버에 재연결 시도할지의 여부
            // true -> 연결이 끊어진 경우 client는 서버에 재연결 시도, 재연결 시도전에 초기에 1초 대기 후,
            // 연결 실패 시 지연 2배 증가하며 최종적으로 2분 도달 시 해당 지연이 2분으로 유지된다
            // false -> 연결이 끊어진 경우 client는 server에 자동으로 재연결 시도X
            options.setAutomaticReconnect(true);

            // client와 server가 다시 시작 및 재연결 간에 상태를 기억할지 여부 설정
            // false => client와 server는 client,server, 연결이 다시 시작해도 상태 유지
            // 상태 유지되는 동안 -> 1. client, server, 연결이 다시 시작되더라도 지정된 QoS 충족하여 msg 전달이 안정적으로 이루어진다
            // 2. server = subscribe가 지속 가능한 subscribe로 처리한다
            // true => client, server = client, server또는 연결을 다시 시작해도 상태 유지 X
            // mean -> 1. client, server 또는 연결이 다시 시작되면 지정된 QoS에 대한 msg 전달 유지 X
            // 2. server = subscribe가 지속되지 않게, 연결을 끊은 걸로 처리한다
            options.setCleanSession(true);

            // 연결 시간초과 값 설정 -> client가 MQTT server에 대한 네트워크 연결이 설정될 때까지 기다리는 최대 시간 간격 의미
            // 기본은 30초로 값 0 = 시간초과 처리 비활성화 => 즉 client = network 연결이 성공하거나 실패할 때까지 기다림
            options.setConnectionTimeout(10);

            // client에게 연결하기
            client.connect(options);

            // topic = "test/a/b/c"로, msg = "Hello"로 보낸다
            client.publish("test/a/b/c", new MqttMessage("Hello".getBytes()));
            client.disconnect();
        } catch (MqttException e) {
            e.printStackTrace();
        }

    }
}