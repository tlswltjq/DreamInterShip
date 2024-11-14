package dubaichamber.dreamintership.supplierApplication.service;

import dubaichamber.dreamintership.supplierApplication.entity.Company;
import dubaichamber.dreamintership.supplierApplication.entity.CompanyId;
import dubaichamber.dreamintership.supplierApplication.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;

    public Company createCompany(String companyName, String contactPerson) {
        return companyRepository.save(Company.builder()
                .id(new CompanyId(companyName, contactPerson))
                .build()
        );
    }

    public Company retrieveCompany(String companyName, String contactPerson) {
        Company company = companyRepository.findById(new CompanyId(companyName, contactPerson)).orElseThrow(NoSuchElementException::new);
        return company;
    }
    public List<Company> retrieveCompanyList(String companyName){
        List<Company> companyList = companyRepository.findById_CompanyName(companyName);
        return companyList;
    }
}
