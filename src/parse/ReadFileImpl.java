package parse;

import models.Employee;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadFileImpl implements ReadFile {
    private static final int ZERO = 0;
    private static final int ONE = 1;
    private static final int TWO = 2;
    private static final int THREE = 3;

    private Scanner scanner;

    @Override
    public void openFile() {
        try {
            scanner = new Scanner(new File("Employees.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Employee> takeEmployees() throws ParseException {
        List<Employee> employees = new ArrayList<>();

        while (scanner.hasNext()) {
            String infoEmployee = scanner.nextLine();
            String[] parseInfo = infoEmployee.split(", ");
            int employeeId = Integer.parseInt(parseInfo[ZERO]);
            int projectId = Integer.parseInt(parseInfo[ONE]);
            String dateFrom = parseInfo[TWO];
            String dateTo = parseInfo[THREE];

            Employee employee = new Employee(employeeId, projectId,
                    dateFrom, dateTo);

            employees.add(employee);
        }

        return employees;
    }

    @Override
    public void closeFile() {
        scanner.close();
    }
}
