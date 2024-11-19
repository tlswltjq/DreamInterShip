package dubaichamber.dreamintership.supplierApplication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ApplicationResponse {
    private String companyName;
    private List<ApplicationDetailResponse> applicationList;
}
