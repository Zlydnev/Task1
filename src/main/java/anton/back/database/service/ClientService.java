package anton.back.database.service;

import anton.back.database.model.Client;
import org.springframework.stereotype.Service;
import java.util.List;

public interface ClientService {
    List<Client> getAllClients();
    void addClient(Client client);
}
