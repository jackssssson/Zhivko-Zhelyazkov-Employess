package task;

import models.Employee;
import parse.ReadFileImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmployeesDaysOfWorkImpl implements EmployeesDaysOfWork {
    private static final int TWO = 2;
    private static final int FIRST_USER = 0;
    private static final int SECOND_USER = 1;
    private static final int HOURS = 24;
    private static final int SECONDS = 60;
    private static final int THOUSAND = 1000;

    private ReadFileImpl file;
    private List<Employee> employees;
    private long bestDays = 0;

    public EmployeesDaysOfWorkImpl() {
        file = new ReadFileImpl();
        employees = new ArrayList<>();
    }

    @Override
    public void run() throws ParseException {
        file.openFile();

        employees = file.takeEmployees();

        if (employees.size() < TWO) {
            System.out.println("Not enough employees");
            return;
        }

        file.closeFile();

        List<Employee> coupleEmployee = coupleEmployee(employees);

        if (coupleEmployee.size() < TWO) {
            System.out.println("No couples");
            return;
        }

        System.out.println("The couple of employees is with id " +
                coupleEmployee.get(FIRST_USER).getEmployeeId() +
                " and " + coupleEmployee.get(SECOND_USER).getEmployeeId() +
                " with project id " +
                coupleEmployee.get(FIRST_USER).getProjectId() +
                " and " + bestDays + " days of work together!");
    }

    @Override
    public List<Employee> coupleEmployee(List<Employee> employees)
            throws ParseException {
        List<Employee> result = new ArrayList<>();

        for (int i = 0; i < employees.size(); i++) {
            Employee firstEmployee = employees.get(i);

            for (int j = i + 1; j < employees.size(); j++) {
                Employee secondEmployee = employees.get(j);

                if (firstEmployee.getEmployeeId() ==
                        secondEmployee.getEmployeeId()) {
                    continue;
                }

                if (firstEmployee.getProjectId() ==
                        secondEmployee.getProjectId()) {
                    SimpleDateFormat dates = new SimpleDateFormat("yyyy-MM-dd");

                    Date dateFromFirstEmployee = dates.parse
                            (firstEmployee.getDateFrom());
                    Date dateToFirstEmployee = dates.parse
                            (firstEmployee.getDateTo());
                    Date dateFromSecondEmployee = dates.parse
                            (secondEmployee.getDateFrom());
                    Date dateToSecondEmployee = dates.parse
                            (secondEmployee.getDateTo());

                    if (dateToSecondEmployee.getTime() < dateFromFirstEmployee.getTime()
                            || dateFromSecondEmployee.getTime() >
                            dateToFirstEmployee.getTime()) {
                        break;
                    }

                    long firstDate;
                    long secondDate;

                    if (dateFromFirstEmployee.getTime() <= dateFromSecondEmployee.getTime()) {
                        firstDate = dateFromSecondEmployee.getTime() /
                                (HOURS * SECONDS * SECONDS * THOUSAND);
                    } else {
                        firstDate = dateFromFirstEmployee.getTime() /
                                (HOURS * SECONDS * SECONDS * THOUSAND);
                    }

                    if (dateToFirstEmployee.getTime() <= dateToSecondEmployee.getTime()) {
                        secondDate = dateToFirstEmployee.getTime() /
                                (HOURS * SECONDS * SECONDS * THOUSAND);
                    } else {
                        secondDate = dateToSecondEmployee.getTime() /
                                (HOURS * SECONDS * SECONDS * THOUSAND);
                    }

                    long days = firstDate - secondDate;

                    if (days < 0) {
                        days *= -1;
                    }

                    if (bestDays < days) {
                        result.clear();
                        bestDays = days;
                        result.add(firstEmployee);
                        result.add(secondEmployee);
                    }
                }
            }

        }

        return result;
    }
}
