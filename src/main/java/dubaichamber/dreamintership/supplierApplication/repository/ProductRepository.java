package dubaichamber.dreamintership.supplierApplication.repository;

import dubaichamber.dreamintership.supplierApplication.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
}
