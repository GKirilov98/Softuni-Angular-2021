package softuni.angular.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.angular.data.entities.NInsObjectTypes;

/**
 * Project: backend
 * Created by: GKirilov
 * On: 8/4/2021
 */
@Repository
public interface NInsObjectTypesRepository extends JpaRepository<NInsObjectTypes, Long> {
}
