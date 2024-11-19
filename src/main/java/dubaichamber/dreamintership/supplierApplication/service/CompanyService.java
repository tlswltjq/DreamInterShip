package dubaichamber.dreamintership.supplierApplication.service;

import dubaichamber.dreamintership.supplierApplication.entity.Company;
import dubaichamber.dreamintership.supplierApplication.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;

    public Company createCompany(String companyName) {
        return companyRepository.save(Company.builder()
                .companyName(companyName)
                .build()
        );
    }

    public Company retrieveCompany(String companyName) {
        Company company = companyRepository.findById(companyName).orElseThrow(NoSuchElementException::new);
        return company;
    }
}
