package dubaichamber.dreamintership.supplierApplication.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@IdClass(ProductApplicationId.class)
@Table(name = "product_application")
public class ProductApplication {
    @Id
    @ManyToOne
    @JoinColumn(name = "application_id", referencedColumnName = "application_id")
    private Application applicationId;

    @Id
    @ManyToOne
    @JoinColumn(name = "product_name", referencedColumnName = "product_name")
    private Product productName;
}