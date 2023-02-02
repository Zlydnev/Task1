package anton.application1.controllers;

import anton.application1.model.Client;
import anton.application1.producermq.service.RabbitMQProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClientController {

    private final RabbitMQProducerService rabbitMQProducerService;

    @Autowired
    public ClientController(RabbitMQProducerService rabbitMQProducerService) {
        this.rabbitMQProducerService = rabbitMQProducerService;
    }

    @PostMapping("/addClient")
    public void addClient(@RequestBody Client client) {
        System.out.println(client);
        rabbitMQProducerService.sendMessage(client, "routingKey");
    }

    @GetMapping("/getClients")
    public List<Client> getClients() {
        return rabbitMQProducerService.getMassage("routingKey2");
    }
}
