package dubaichamber.dreamintership.supplierApplication.repository;

import dubaichamber.dreamintership.supplierApplication.entity.ApplicationDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationDetailsRepository extends JpaRepository<ApplicationDetails, Long> {
}