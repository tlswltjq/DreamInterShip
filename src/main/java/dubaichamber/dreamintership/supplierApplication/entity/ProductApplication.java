package dubaichamber.dreamintership.supplierApplication.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product_application")
public class ProductApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_application_id")
    private Long ProductApplicationId;

    @ManyToOne
    @JoinColumn(name = "application_id", referencedColumnName = "application_id")
    private Application applicationId;

    @ManyToOne
    @JoinColumn(name = "product_name", referencedColumnName = "product_name")
    private Product productName;
}
