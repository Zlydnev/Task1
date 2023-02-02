package anton.back.rabbitmq;

import anton.back.database.JsonClientConverter;
import anton.back.database.model.Client;
import anton.back.database.service.ClientServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@EnableRabbit
public class RabbitMQConsumer {
    private final ClientServiceImpl clientService;

    @Autowired
    public RabbitMQConsumer(ClientServiceImpl clientService) {
        this.clientService = clientService;
    }

    @RabbitListener(queues = "queue")
    public String processMyQueue(Client client) {
        clientService.addClient(client);
        return "client registered";
    }

    @RabbitListener(queues = "queue1")
    public String processMyQueue(String message) throws JsonProcessingException {
        String json = null;
        List<Client> clients = clientService.getAllClients();
        JsonClientConverter jsonClientConverter = new JsonClientConverter();
        json = jsonClientConverter.getJson(clients);


        return json;
    }
}