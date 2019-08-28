package no.acntech.project101.employee;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "DATE_OF_BIRTH")
    private LocalDate dateOfBirth;

    @Column(name = "COMPANY_ID")
    private Long companyId;

    public Employee() {
        // Hibernate
    }

    public Employee(final String firstName, final String lastName, final LocalDate dateOfBirth, final Long companyId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.companyId = companyId;
    }

    public Long getId() { return id; }

    public String getFirstName() { return firstName; }

    public void setFirstName(final String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }

    public void setLastName(final String lastName) { this.lastName = lastName; }

    public LocalDate getDateOfBirth() { return dateOfBirth; }

    public Long getCompanyId() { return companyId; }

    public void setCompanyId(final Long companyId) {this.companyId = companyId; }
}
