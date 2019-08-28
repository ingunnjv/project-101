package no.acntech.project101.employee.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import no.acntech.project101.company.config.CompanyDatabaseConfig;
import no.acntech.project101.employee.Employee;
import no.acntech.project101.employee.config.EmployeeDatabaseConfig;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import({EmployeeDatabaseConfig.class, CompanyDatabaseConfig.class})
@ContextConfiguration(classes = EmployeeRepository.class)
public class EmployeeRepositoryTest {

        @Autowired
        private EmployeeRepository repository;

    @Test
    void save() {
        final LocalDate birthday = LocalDate.of(2014, Month.APRIL, 2);
        final Employee employee = new Employee("Lasse", "Henriksen", birthday, 1L);
                final Employee savedEmployee = repository.save(employee);
                assertThat(savedEmployee.getId()).isNotNull();
                assertThat(savedEmployee.getFirstName()).isEqualTo(employee.getFirstName());
                assertThat(savedEmployee.getLastName()).isEqualTo(employee.getLastName());
                assertThat(savedEmployee.getDateOfBirth()).isEqualTo(employee.getDateOfBirth());
    }
}
