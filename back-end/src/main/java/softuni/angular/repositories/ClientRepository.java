package softuni.angular.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.angular.data.entities.Client;

import java.util.List;

/**
 * Project: backend
 * Created by: GKirilov
 * On: 8/4/2021
 */
@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    boolean existsByEgnBulstat(String egnBulstat);
    Client findByEgnBulstat(String egnBusltat);

    @Query( " SELECT c  FROM Client c" +
            " LEFT JOIN Policy p ON c.id = p.client.id" +
            " WHERE p.id IS NULL" +
            " GROUP BY c")
    List<Client> findAllByNoPliciesCustom();
}
