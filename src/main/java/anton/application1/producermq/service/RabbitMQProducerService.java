package anton.application1.producermq.service;

import anton.application1.model.Client;

import java.util.List;

public interface RabbitMQProducerService {
    void sendMessage(Client client, String routingKey);
    List<Client> getMassage(String routingKey);
}
