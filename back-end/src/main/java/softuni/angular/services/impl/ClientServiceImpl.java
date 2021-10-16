package softuni.angular.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import softuni.angular.data.entities.Client;
import softuni.angular.repositories.ClientRepository;
import softuni.angular.services.ClientService;
import softuni.angular.views.client.ClientCreateInView;
import softuni.angular.views.client.ClientDetailsOutView;

import java.util.ArrayList;
import java.util.List;

/**
 * Project: backend
 * Created by: GKirilov
 * On: 8/9/2021
 */
@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<ClientDetailsOutView> insertOne(ClientCreateInView inView) {
        UserDetailsImpl currentUser =
                (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String logId = currentUser.getRequestId();
        // TODO: 8/9/2021 check unique bulstat
        Client entity = this.modelMapper.map(inView, Client.class);
        Client saved = this.clientRepository.save(entity);
        List<ClientDetailsOutView> result = new ArrayList<>();
        result.add(this.modelMapper.map(saved, ClientDetailsOutView.class));
        return result;
    }
}
