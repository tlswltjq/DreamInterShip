package dubaichamber.dreamintership.supplierApplication.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "application")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_id")
    private Long applicationId;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "company_name", referencedColumnName = "company_name"),
            @JoinColumn(name = "contact_person", referencedColumnName = "contact_person")
    })
    private Company company;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "website")
    private String website;

    @Column(name = "product_catalogue")
    private String productCatalogue;

    @Column(name = "comment")
    private String comment;

    @Column(name = "created_ad")
    private LocalDateTime createdAt;
}
