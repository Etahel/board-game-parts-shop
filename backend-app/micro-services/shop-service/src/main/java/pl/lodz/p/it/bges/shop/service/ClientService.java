package pl.lodz.p.it.bges.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.lodz.p.it.bges.shop.criteria.ClientCriteria;
import pl.lodz.p.it.bges.shop.dto.ClientDto;
import pl.lodz.p.it.bges.shop.entity.Client;
import pl.lodz.p.it.bges.shop.exception.ShopException;
import pl.lodz.p.it.bges.shop.exception.client.ClientNotFoundException;
import pl.lodz.p.it.bges.shop.repository.ClientRepository;
import pl.lodz.p.it.bges.shop.repository.specification.ClientSpecification;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional(rollbackOn = {RuntimeException.class, ShopException.class})
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

    public Client getClient(Long clientId) throws ShopException {
        Optional<Client> clientOpt = clientRepository.findById(clientId);
        if (clientOpt.isPresent()) {
            return clientOpt.get();
        } else {
            throw new ClientNotFoundException();
        }
    }

    public void updateClient(ClientDto clientDto, String username) {
        Client client = getClient(username);
        clientDto.patchProperties(client);
    }

    public Page<Client> getClients(Pageable pageable, ClientCriteria clientCriteria) {
        return clientRepository.findAll(ClientSpecification.getCriteriaSpecification(clientCriteria), pageable);
    }


}
