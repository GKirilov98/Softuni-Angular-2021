package softuni.angular.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import softuni.angular.data.entities.Policy;

import java.util.List;

/**
 * Project: backend
 * Created by: GKirilov
 * On: 8/4/2021
 */
@Repository
public interface PolicyRepository extends JpaRepository<Policy, Long> {
    @Query(value = " SELECT p FROM Policy p" +
            " WHERE p.insProduct.insCompany.id = :companyId" +
            " GROUP BY p")
    List<Policy> findAllByInsCompanyIdCustom(@Param("companyId") Long id);

    @Query(value = " SELECT p FROM Policy p" +
            " WHERE p.insProduct.id = :id " +
            " GROUP BY p")
    List<Policy> findAllByInsProductIdCustom(@Param("id") Long id);

    @Query("SELECT p FROM Policy p WHERE (:clientId IS NULL OR p.client.id = :clientId)")
    List<Policy> findAllByOptionalClientIdCustom(@Param("clientId") Long clientId);

    boolean existsByIdentityNumber(String identityNumber);
}
