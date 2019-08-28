package no.acntech.project101.web.employee.resources;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import no.acntech.project101.employee.Employee;
import no.acntech.project101.employee.service.EmployeeService;
import no.acntech.project101.web.employee.resources.converter.EmployeeConverter;
import no.acntech.project101.web.employee.resources.converter.EmployeeDtoConverter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("employees")
public class EmployeeResource {

    private final EmployeeService employeeService;
    private final EmployeeDtoConverter employeeDtoConverter;
    private final EmployeeConverter employeeConverter;

    public EmployeeResource(final EmployeeService employeeService,
                            final EmployeeDtoConverter employeeDtoConverter,
                            final EmployeeConverter employeeConverter) {
        this.employeeService = employeeService;
        this.employeeDtoConverter = employeeDtoConverter;
        this.employeeConverter = employeeConverter;
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> findAll() {
        final List<Employee> employees = employeeService.findAll();
        final List<EmployeeDto> employees_collection = employees.stream()
                .map(employeeDtoConverter::convert)
                .collect(Collectors.toList());

        return ResponseEntity.ok(employees_collection);
    }

    @GetMapping("{id}")
    public ResponseEntity<EmployeeDto> findById(@PathVariable final Long id) {
        final Optional<Employee> employee = employeeService.findById(id);

        if (employee.isPresent()) {
            final EmployeeDto convert = employeeDtoConverter.convert(employee.get());
            return ResponseEntity.ok(convert);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping
    public ResponseEntity createEmployee(@RequestBody final EmployeeDto employeeDto) {
        final Employee employee = employeeConverter.convert(employeeDto);
        final Employee saved = employeeService.save(employee);
        final URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteEmployee(@PathVariable final Long id) {
        final Optional<Employee> employee = employeeService.findById(id);

        if (employee.isPresent()) {
            employeeService.delete(id);
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @PatchMapping("{id}")
    public ResponseEntity updateEmployee(@PathVariable final Long id, @RequestBody final EmployeeDto employeeDto) {
        final Optional<Employee> optionalEmployee = employeeService.findById(id);

        if (optionalEmployee.isPresent()) {
            Employee existingEmployee = optionalEmployee.get();
            existingEmployee.setFirstName(employeeDto.getFirstName());
            existingEmployee.setLastName(employeeDto.getLastName());
            existingEmployee.setCompanyId(employeeDto.getCompanyId());

            Employee saved = employeeService.save(existingEmployee);
            final EmployeeDto convert = employeeDtoConverter.convert(saved);

            return ResponseEntity.ok(convert);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
