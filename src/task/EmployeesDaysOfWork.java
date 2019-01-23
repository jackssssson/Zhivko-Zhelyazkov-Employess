package task;

import models.Employee;

import java.text.ParseException;
import java.util.List;

public interface EmployeesDaysOfWork {
    void run() throws ParseException;

    List<Employee> coupleEmployee(List<Employee> employees)
            throws ParseException;
}
