package dubaichamber.dreamintership.supplierApplication.service;

import dubaichamber.dreamintership.supplierApplication.entity.Company;
import dubaichamber.dreamintership.supplierApplication.entity.CompanyId;
import dubaichamber.dreamintership.supplierApplication.repository.CompanyRepository;
import dubaichamber.dreamintership.util.FixtureFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CompanyServiceTest {
    private final FixtureFactory factory = new FixtureFactory();
    @Mock
    private CompanyRepository companyRepository;
    @InjectMocks
    private CompanyService companyService;

    @DisplayName("Can create company entity using companyName and contactPerson")
    @Test
    void createCompanyIdTest() {
        String companyName = "happyCompany";
        String contactPerson = "Mr.Shin";
        CompanyId companyId = factory.createCompanyId(companyName, contactPerson);
        Company company = factory.createCompany(companyId);

        when(companyRepository.save(any(Company.class))).thenReturn(company);

        Company createdCompany = companyService.createCompany(companyName, contactPerson);

        verify(companyRepository).save(any(Company.class));
        assertThat(company).isSameAs(createdCompany);
    }

    @DisplayName("Can find company entity using companyName and contactPerson")
    @Test
    void retrieveCompanyTest() {
        String companyName = "happyCompany";
        String contactPerson = "Mr.Shin";
        CompanyId companyId = factory.createCompanyId(companyName, contactPerson);
        Company company = factory.createCompany(companyId);

        when(companyRepository.findById(companyId)).thenReturn(Optional.ofNullable(company));

        Company retrievedCompany = companyService.retrieveCompany(companyName, contactPerson);

        verify(companyRepository).findById(companyId);
        assertThat(company).isSameAs(retrievedCompany);
    }

    @DisplayName("Can't find company entity using non-existing companyName and contactPerson")
    @Test
    void failToRetrieveCompanyTest() {
        String companyName = "paperCompany";
        String contactPerson = "Mr.Nobody";
        CompanyId companyId = factory.createCompanyId(companyName, contactPerson);

        when(companyRepository.findById(companyId)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> companyService.retrieveCompany(companyName, contactPerson));

        verify(companyRepository).findById(companyId);
    }

    @DisplayName("Can find list of company entity using companyName")
    @Test
    void retrieveCompanyListTest() {
        String companyName = "happyCompany1";
        String contactPerson1 = "Mr.Shin";
        CompanyId companyId1 = factory.createCompanyId(companyName, contactPerson1);
        Company company1 = factory.createCompany(companyId1);
        String contactPerson2 = "Mr.LEE";
        CompanyId companyId2 = factory.createCompanyId(companyName, contactPerson2);
        Company company2 = factory.createCompany(companyId2);

        when(companyRepository.findById_CompanyName(companyName)).thenReturn(List.of(company1, company2));

        List<Company> companyList = companyService.retrieveCompanyList(companyName);

        verify(companyRepository).findById_CompanyName(companyName);

        assertThat(companyList).hasSize(2);
        assertThat(companyList).contains(company1, company2);
    }

    @DisplayName("Can't find list of company entity using non-existing companyName")
    @Test
    void failToRetrieveCompanyListTest() {
        String companyName = "paperCompany";

        when(companyRepository.findById_CompanyName(companyName)).thenReturn(List.of());

        List<Company> companyList = companyService.retrieveCompanyList(companyName);

        verify(companyRepository).findById_CompanyName(companyName);
        assertThat(companyList).hasSize(0);
    }
}