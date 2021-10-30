package softuni.angular.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import softuni.angular.data.entities.InsCompany;

import java.util.List;
import java.util.Optional;

/**
 * Project: backend
 * Created by: GKirilov
 * On: 8/4/2021
 */
@Repository
public interface InsCompanyRepository extends JpaRepository<InsCompany, Long> {
    boolean existsByBulstat(String bulstat);

    @Query( " SELECT c FROM InsCompany c WHERE (:name IS NULL OR c.name = :name) " +
            " AND (:bulstat IS NULL OR c.bulstat=:bulstat)")
    List<InsCompany> findAllByOptionalNameAndBulstatCustom(@Param("name") String name, @Param("bulstat") String bulstat);
    Optional<InsCompany> findById(Long id);
}
