package pl.lodz.p.it.bges.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lodz.p.it.bges.shop.dto.ClientDto;
import pl.lodz.p.it.bges.shop.entity.Client;
import pl.lodz.p.it.bges.shop.repository.ClientRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional(rollbackOn = {RuntimeException.class})
public class ClientService {


    ClientRepository clientRepository;

    @Autowired
    ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client getClient(String username) {
        Optional<Client> clientOpt = clientRepository.findClientByUsername(username);
        if (clientOpt.isPresent()) {
            return clientOpt.get();
        } else {
            return clientRepository.saveAndFlush(new Client(username));
        }
    }

    public void updateClient(ClientDto clientDto, String username) {
        System.out.println("hello");
        Client client = getClient(username);
        clientDto.patchProperties(client);
    }


}
