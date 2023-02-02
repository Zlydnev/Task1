package anton.application1.producermq.service;

import anton.application1.model.Client;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RabbitMQProducerServiceImpl implements RabbitMQProducerService{

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitMQProducerServiceImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(Client client, String routingKey) {
        rabbitTemplate.convertSendAndReceive("exchange", routingKey, client);
    }

    @Override
    public List<Client> getMassage(String routingKey) {

        String json = (String) rabbitTemplate.convertSendAndReceive("exchange", routingKey,
                "getClients");
        ObjectMapper mapper = new ObjectMapper();
        List<Client> clients = null;
        try {
            clients = mapper.readValue(json, new TypeReference<List<Client>>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return clients;

    }

}
