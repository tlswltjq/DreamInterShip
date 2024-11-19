package dubaichamber.dreamintership.supplierApplication.service;

import dubaichamber.dreamintership.supplierApplication.entity.Company;
import dubaichamber.dreamintership.supplierApplication.repository.CompanyRepository;
import dubaichamber.dreamintership.util.FixtureFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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

    @DisplayName("Should create a company entity using companyName")
    @Test
    void createCompanyIdTest() {
        String companyName = "happyCompany";
        Company company = factory.createCompany(companyName);

        when(companyRepository.save(any(Company.class))).thenReturn(company);

        Company createdCompany = companyService.createCompany(companyName);

        verify(companyRepository).save(any(Company.class));
        assertThat(company).isSameAs(createdCompany);
    }

    @DisplayName("Should find a company entity using companyName")
    @Test
    void retrieveCompanyTest() {
        String companyName = "happyCompany";
        Company company = factory.createCompany(companyName);

        when(companyRepository.findById(companyName)).thenReturn(Optional.ofNullable(company));

        Company retrievedCompany = companyService.retrieveCompany(companyName);

        verify(companyRepository).findById(companyName);
        assertThat(company).isSameAs(retrievedCompany);
    }
    @DisplayName("Should throw NoSuchElementException when retrieving a non-existing company")
    @Test
    void failToRetrieveCompanyListTest() {
        String companyName = "wrongCompanyName";

        when(companyRepository.findById(companyName)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> companyService.retrieveCompany(companyName));
        verify(companyRepository).findById(companyName);
    }
}