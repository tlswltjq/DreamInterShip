package dubaichamber.dreamintership.supplierApplication.service;

import dubaichamber.dreamintership.supplierApplication.entity.Application;
import dubaichamber.dreamintership.supplierApplication.entity.Company;
import dubaichamber.dreamintership.supplierApplication.repository.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ApplicationService {
    private final ApplicationRepository applicationRepository;

    public Application createApplication(Company company, String contactPerson, String email, String address, String phoneNumber, String webSite, String fileUrl, String comment) {
        return applicationRepository.save(Application.builder()
                .company(company)
                .contactPerson(contactPerson)
                .email(email)
                .address(address)
                .phoneNumber(phoneNumber)
                .website(webSite)
                .productCatalogue(fileUrl)
                .comment(comment)
                .createdAt(LocalDateTime.now())
                .build()
        );
    }

    public Application retrieveApplication(Long applicationId) {
        Application application = applicationRepository.findById(applicationId).orElseThrow(NoSuchElementException::new);
        return application;
    }

    public List<Application> retrieveApplicationList(Company company) {
        List<Application> applicationList = applicationRepository.findByCompany(company);
        return applicationList;
    }
}
