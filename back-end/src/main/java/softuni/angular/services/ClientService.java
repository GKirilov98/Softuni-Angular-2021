package softuni.angular.services;

import softuni.angular.views.client.ClientCreateInView;
import softuni.angular.views.client.ClientDetailsOutView;

import java.util.List;

/**
 * Project: backend
 * Created by: GKirilov
 * On: 8/4/2021
 */
public interface ClientService {
    List<ClientDetailsOutView> insertOne(ClientCreateInView inView);
}
