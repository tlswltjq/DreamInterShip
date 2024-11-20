package dubaichamber.dreamintership.supplierApplication.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "application_details")
public class ApplicationDetails {
    @Id
    @Column(name = "application_id")
    private Long applicationId;
    
    @Column(name = "company_name")
    private String companyName;
    
    @Column(name = "contact_person")
    private String contactPerson;
    
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
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "product_id")
    private Long productId;
    
    @Column(name = "product_name")
    private String productName;
    
    @Column(name = "description")
    private String description;
}