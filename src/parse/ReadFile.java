package parse;

import models.Employee;

import java.text.ParseException;
import java.util.List;

public interface ReadFile {
    void openFile();

    List<Employee> takeEmployees() throws ParseException;

    void closeFile();
}
