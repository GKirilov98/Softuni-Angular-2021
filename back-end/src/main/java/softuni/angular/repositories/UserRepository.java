package softuni.angular.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.angular.data.entities.User;

import javax.swing.text.html.Option;
import java.util.Optional;

/**
 * Project: backend
 * Created by: GKirilov
 * On: 8/4/2021
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);
}
