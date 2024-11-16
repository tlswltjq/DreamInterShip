package dubaichamber.dreamintership.supplierApplication.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class SupplierForm {
    private String companyName;
    private String contactPerson;
    private String email;
    private String address;
    private String phoneNumber;
    private String website;
    private List<String> products;
    private MultipartFile productCatalogue;
    private String comment;
}