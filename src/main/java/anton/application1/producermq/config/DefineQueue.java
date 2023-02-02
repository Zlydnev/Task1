package anton.application1.producermq.config;

import jakarta.annotation.PostConstruct;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class DefineQueue {
    @Autowired
    private AmqpAdmin rabbitAdmin;

    @PostConstruct
    public void getQueue(){
        String queue = rabbitAdmin.declareQueue(new Queue("queue1"));
        rabbitAdmin.declareBinding(new Binding(queue, Binding.DestinationType.QUEUE,
                "exchange", "routingKey2", new HashMap<>()));
    }
}
