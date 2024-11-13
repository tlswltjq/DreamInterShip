package dubaichamber.dreamintership.supplierApplication.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class CompanyId implements Serializable {
    @Column(name = "company_name")
    private String companyName;

    @Column(name = "contact_person")
    private String contactPerson;
}
