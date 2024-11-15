package dubaichamber.dreamintership.supplierApplication.repository;

import dubaichamber.dreamintership.supplierApplication.entity.ProductApplication;
import dubaichamber.dreamintership.supplierApplication.entity.ProductApplicationId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductApplicationRepository extends JpaRepository<ProductApplication, ProductApplicationId> {
}
