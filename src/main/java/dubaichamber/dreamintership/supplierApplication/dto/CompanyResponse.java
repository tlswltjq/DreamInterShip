package dubaichamber.dreamintership.supplierApplication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CompanyResponse {
    private List<String> companyNameList;
}
