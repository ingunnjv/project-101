package no.acntech.project101.web.employee.resources;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
//TODO This is a REST controler and should receive request on path employees
@RestController
@RequestMapping("employees")
public class EmployeeResource {

    //TODO The constructor needs to accept the required dependencies and assign them to class variables
    public EmployeeResource() {
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> findAll() {
        //TODO create a GET endpoint find all employees in the database and return them
        EmployeeDto employeeDto1 = new EmployeeDto(1L, "first", "last", LocalDate.now(), 1L);
        EmployeeDto employeeDto2 = new EmployeeDto(2L, "Ingunn", "Vallestad", LocalDate.now().minusDays(2), 2L);
        final List<EmployeeDto> collect = new ArrayList<EmployeeDto>();
        collect.add(employeeDto1);
        collect.add(employeeDto2);
        return ResponseEntity.ok(collect);
    }

    @GetMapping("{id}")
    public ResponseEntity<EmployeeDto> findById(@PathVariable final Long id) {
        // TODO create a GET endpoint that fetches a spesific employee based on its ID
        EmployeeDto employeeDto = new EmployeeDto(id, "Ingunn", "Vallestad", LocalDate.now().minusDays(2), 2L);
        if (id % 2 == 0) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(employeeDto);
        }
    }

    @PostMapping
    public ResponseEntity createEmployee(@RequestBody final EmployeeDto employeeDto) {
        //TODO Create a POST endpoint that accepts an employeeDTO and saves it in the database

        return ResponseEntity.created(null).build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteEmployee(@PathVariable final Long id) {
        // TODO Create a DELETE endpoint that deletes a specific employee
        if (id % 2 == 0) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.accepted().build();
        }
    }

    @PatchMapping("{id}")
    public ResponseEntity updateEmployee(@PathVariable final Long id, @RequestBody final EmployeeDto employeeDto) {
        //TODO Create a PATCH endpoint that updates an employee with new values
        if (id % 2 == 0) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(employeeDto);
        }
    }
}
