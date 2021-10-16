package softuni.angular.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.angular.data.entities.UsersRoles;

import java.util.List;

/**
 * Project: backend
 * Created by: GKirilov
 * On: 10/15/2021
 */
@Repository
public interface UsersRolesRepository extends JpaRepository<UsersRoles, Long> {
    List<UsersRoles> findAllByUserId(long userId);
}
