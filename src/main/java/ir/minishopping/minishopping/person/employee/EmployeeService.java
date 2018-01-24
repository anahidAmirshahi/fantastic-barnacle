package ir.minishopping.minishopping.person.employee;


import ir.minishopping.minishopping.common.CodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {


    @Autowired
    private EmployeeRepository employeeRepository;


    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        employeeRepository.findAll().forEach(employees::add);
        return employees;
    }

    public Employee getEmployee(String id) {
        return employeeRepository.findOne(id);
    }

    public void insertEmployee(Employee employee) {


        if (employee.getEnable() == null)
            employee.setEnable(true);


        CodeGenerator codeGenerator = new CodeGenerator();
        employee.setEmployeeCode(codeGenerator.randomUUID(4));


        employeeRepository.save(employee);
    }

    public void updateEmployee(Employee employee, String id) {

        Employee employeeDb = employeeRepository.findOne(id);

        if (employee.getEnable() != null)
            employeeDb.setEnable(employee.getEnable());
        if (employee.getAge() != 0)
            employeeDb.setAge(employee.getAge());
        if (employee.getFirstName() != null)
            employeeDb.setFirstName(employee.getFirstName());
        if (employee.getLastName() != null)
            employeeDb.setLastName(employee.getLastName());
        if (employee.getTelNumber() != 0)
            employeeDb.setTelNumber(employee.getTelNumber());
        if (employee.getAge() != 0)
            employeeDb.setAge(employee.getAge());

        employeeRepository.save(employeeDb);
    }

    public void deleteEmployee(String id) {
        employeeRepository.delete(id);
    }

}
