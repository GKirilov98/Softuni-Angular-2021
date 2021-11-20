package softuni.angular.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.angular.data.entities.ResErrorLog;

/**
 * Project: backend
 * Created by: GKirilov
 * On: 11/20/2021
 */
@Repository
public interface ResErrorLogRepository extends JpaRepository<ResErrorLog, Long> {
}
