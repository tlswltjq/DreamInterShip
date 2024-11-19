package dubaichamber.dreamintership.supplierApplication.dto;

import dubaichamber.dreamintership.supplierApplication.entity.Application;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApplicationDetailResponse {
    private Long applicationId;
    private String contactPerson;
    private String email;
    private String address;
    private String phoneNumber;
    private String website;
    private String productCatalogue;
    private String comment;
    private String submittingDate;

    public ApplicationDetailResponse(Application application) {
        this.applicationId = application.getApplicationId();
        this.contactPerson = application.getContactPerson();
        this.email = application.getEmail();
        this.address = application.getAddress();
        this.phoneNumber = application.getPhoneNumber();
        this.website = application.getWebsite();
        this.productCatalogue = application.getProductCatalogue();
        this.comment = application.getComment();
        this.submittingDate = application.getCreatedAt().toString();
    }
}
