package com.ioteste.subscriber;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Suscriptor MQTT mínimo — primer componente Java del producto IoTEste.
 *
 * Se conecta al broker Mosquitto, se suscribe a un topic (por defecto
 * "shellies/#") e imprime en consola cada mensaje recibido junto con
 * el topic y un timestamp.
 *
 * Configuración vía variables de entorno (con valores por defecto para
 * ejecución local fuera de Docker):
 *   MQTT_BROKER_HOST  (default: localhost)
 *   MQTT_BROKER_PORT  (default: 1883)
 *   MQTT_TOPIC        (default: shellies/#)
 *   MQTT_CLIENT_ID    (default: ioteste-subscriber)
 */
public class MqttSubscriber {

    private static final DateTimeFormatter TS_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        String host = getEnv("MQTT_BROKER_HOST", "localhost");
        String port = getEnv("MQTT_BROKER_PORT", "1883");
        String topic = getEnv("MQTT_TOPIC", "shellies/#");
        String clientId = getEnv("MQTT_CLIENT_ID", "ioteste-subscriber");

        String brokerUrl = "tcp://" + host + ":" + port;

        System.out.println("=== IoTEste MQTT Subscriber ===");
        System.out.println("Broker: " + brokerUrl);
        System.out.println("Topic : " + topic);
        System.out.println("================================");

        try {
            MqttClient client = new MqttClient(brokerUrl, clientId, new MemoryPersistence());

            MqttConnectOptions options = new MqttConnectOptions();
            options.setCleanSession(true);
            options.setAutomaticReconnect(true);
            options.setConnectionTimeout(10);
            options.setKeepAliveInterval(30);

            client.setCallback(new MqttCallback() {
                @Override
                public void connectionLost(Throwable cause) {
                    System.out.println("[" + now() + "] Conexión perdida: " + cause.getMessage());
                }

                @Override
                public void messageArrived(String receivedTopic, MqttMessage message) {
                    String payload = new String(message.getPayload());
                    System.out.printf("[%s] topic=%s payload=%s%n", now(), receivedTopic, payload);
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken token) {
                    // No aplica: este componente solo consume mensajes, no publica.
                }
            });

            System.out.println("Conectando al broker...");
            client.connect(options);
            System.out.println("Conectado. Suscribiendo a: " + topic);

            client.subscribe(topic);
            System.out.println("Suscripción activa. Esperando mensajes...\n");

            // Mantiene el proceso vivo (el callback maneja los mensajes de forma asíncrona)
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    System.out.println("\nCerrando conexión MQTT...");
                    client.disconnect();
                } catch (MqttException e) {
                    // Ignorado en el shutdown
                }
            }));

            Thread.currentThread().join();

        } catch (MqttException e) {
            System.err.println("Error MQTT: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private static String getEnv(String key, String defaultValue) {
        String value = System.getenv(key);
        return (value == null || value.isBlank()) ? defaultValue : value;
    }

    private static String now() {
        return LocalDateTime.now().format(TS_FORMAT);
    }
}