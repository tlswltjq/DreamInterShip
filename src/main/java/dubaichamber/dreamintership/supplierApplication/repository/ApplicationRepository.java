package dubaichamber.dreamintership.supplierApplication.repository;

import dubaichamber.dreamintership.supplierApplication.entity.Application;
import dubaichamber.dreamintership.supplierApplication.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findByCompany(Company company);
}
