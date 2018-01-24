package ir.minishopping.minishopping.person.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployee(@PathVariable String id){
        return employeeService.getEmployee(id);
    }

    @PostMapping("/employees")
    @ResponseBody
    public Employee insertEmployee(@RequestBody Employee employee){
        employeeService.insertEmployee(employee);
        return employee;
    }

    @PutMapping("/employees/{id}")
    public void updateEmployee(@RequestBody Employee employee,@PathVariable String id){

        if (employee.getEnable()== null)
            employee.setEnable(true);

        employeeService.updateEmployee(employee,id);
    }

    @DeleteMapping("/employees/{id}")
    public void deleteEmployee(@PathVariable String id){
        employeeService.deleteEmployee(id);
    }

}
