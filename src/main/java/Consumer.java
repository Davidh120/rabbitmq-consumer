import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class Consumer {
    public static void main(String[] args) {
        // Configura la conexión a RabbitMQ
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        try {
            // Establece la conexión y crea un canal
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            // Declara la cola que el consumidor utilizará
            channel.queueDeclare("queue1", true, false, true, null);

            // Define la acción a realizar cuando se reciba un mensaje
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), "UTF-8");
                System.out.println("Location: " + message);
            };

            // Inicia el consumo de mensajes de la cola
            channel.basicConsume("queue1", true, deliverCallback, consumerTag -> {});

            System.out.println(" [x] Waiting for messages. To exit press CTRL+C");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
