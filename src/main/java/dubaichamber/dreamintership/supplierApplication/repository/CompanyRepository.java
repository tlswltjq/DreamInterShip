package dubaichamber.dreamintership.supplierApplication.repository;

import dubaichamber.dreamintership.supplierApplication.entity.Company;
import dubaichamber.dreamintership.supplierApplication.entity.CompanyId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, CompanyId> {
    List<Company> findById_CompanyName(String companyName);
}
