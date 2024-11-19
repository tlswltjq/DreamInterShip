package dubaichamber.dreamintership.supplierApplication.service;

import dubaichamber.dreamintership.supplierApplication.entity.Application;
import dubaichamber.dreamintership.supplierApplication.entity.Company;
import dubaichamber.dreamintership.supplierApplication.repository.ApplicationRepository;
import dubaichamber.dreamintership.util.FixtureFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ApplicationServiceTest {
    private final FixtureFactory factory = new FixtureFactory();
    @Mock
    ApplicationRepository applicationRepository;
    @InjectMocks
    ApplicationService applicationService;

    @DisplayName("Can create application entity")
    @Test
    void canCreateApplication() {
        String companyName = "SAMSUNG";
        String contactPerson = "Lee";
        String email = "wltjq1203@icloud.com";
        String address = "30 Churchill Ave, 2135";
        String phoneNumber = "0401377916";
        String webSite = "https://github.com/tlswltjq";
        String fileUrl = "https://placehold.co/600x400";
        String comment = ":D";
        Company company = factory.createCompany(companyName);
        Application application = factory.createApplication(company, contactPerson, email, address, phoneNumber, webSite, fileUrl, comment);

        when(applicationRepository.save(any(Application.class))).thenReturn(application);

        Application createdApplication = applicationService.createApplication(company, contactPerson, email, address, phoneNumber, webSite, fileUrl, comment);
        verify(applicationRepository).save(any(Application.class));
        assertThat(application).isSameAs(createdApplication);
    }

    @DisplayName("Can find application using applicationId")
    @Test
    void retrieveApplicationTest() {
        String companyName = "SAMSUNG";
        String contactPerson = "Lee";
        String email = "wltjq1203@icloud.com";
        String address = "30 Churchill Ave, 2135";
        String phoneNumber = "0401377916";
        String webSite = "https://github.com/tlswltjq";
        String fileUrl = "https://placehold.co/600x400";
        String comment = ":D";
        Company company = factory.createCompany(companyName);
        Application application = factory.createApplication(company, contactPerson, email, address, phoneNumber, webSite, fileUrl, comment);

        when(applicationRepository.findById(any(Long.class))).thenReturn(Optional.ofNullable(application));

        Application retrieveApplication = applicationService.retrieveApplication(1L);

        verify(applicationRepository).findById(any(Long.class));
        assertThat(application).isSameAs(retrieveApplication);
    }

    @DisplayName("Can find application using company entities")
    @Test
    void retrieveApplicationUsingCompanyTest() {
        String companyName = "SAMSUNG";
        String contactPerson = "Lee";
        String email = "wltjq1203@icloud.com";
        String address = "30 Churchill Ave, 2135";
        String phoneNumber = "0401377916";
        String webSite = "https://github.com/tlswltjq";
        String fileUrl = "https://placehold.co/600x400";
        String comment = ":D";
        Company company = factory.createCompany(companyName);
        Application application = factory.createApplication(company, contactPerson, email, address, phoneNumber, webSite, fileUrl, comment);
        List<Application> applicationList = List.of(application);

        when(applicationRepository.findByCompany(company)).thenReturn(applicationList);

        List<Application> retrievedApplicationList = applicationService.retrieveApplicationList(company);
        verify(applicationRepository).findByCompany(company);
        assertThat(retrievedApplicationList.size()).isEqualTo(1);
        assertThat(retrievedApplicationList).contains(application);
    }
    @DisplayName("Can find application using company_name")
    @Test
    void retrieveApplicationUsingCompanyNameTest(){
        String companyName = "SAMSUNG";
        String contactPerson1 = "PARK";
        String contactPerson2 = "KIM";
        String email = "wltjq1203@icloud.com";
        String address = "30 Churchill Ave, 2135";
        String phoneNumber = "0401377916";
        String webSite = "https://github.com/tlswltjq";
        String fileUrl = "https://placehold.co/600x400";
        String comment = ":D";
        Company company = factory.createCompany(companyName);
        Application application1 = factory.createApplication(company, contactPerson1, email, address, phoneNumber, webSite, fileUrl, comment);
        Application application2 = factory.createApplication(company, contactPerson2, email, address, phoneNumber, webSite, fileUrl, comment);

        List<Application> applicationList = List.of(application1, application2);

        when(applicationRepository.findByCompany(company)).thenReturn(applicationList);

        List<Application> retrievedApplicationList = applicationService.retrieveApplicationList(company);
        verify(applicationRepository).findByCompany(company);
        assertThat(retrievedApplicationList).isSameAs(applicationList);
        assertThat(applicationList.size()).isEqualTo(2);
        assertThat(retrievedApplicationList).contains(application1, application2);
    }
}