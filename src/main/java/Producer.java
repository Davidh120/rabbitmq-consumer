import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Producer {
    public static void main(String[] args) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        try {
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            channel.queueDeclare("queue1", true, false, true, null);

            String message = "{\"latitude\": -63.0556419414418, \"longitude\": 20.81070595581599}";
            channel.basicPublish("", "queue1", null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");

            channel.close();
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
