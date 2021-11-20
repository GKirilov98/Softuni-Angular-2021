package softuni.angular.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.angular.data.entities.SiteUrlActionsLog;

/**
 * Project: backend
 * Created by: GKirilov
 * On: 11/18/2021
 */
@Repository
public interface SiteUrlActionsLogRepository extends JpaRepository<SiteUrlActionsLog, Long> {
}
