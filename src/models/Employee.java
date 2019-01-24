package models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Employee {
    private int employeeId;
    private int projectId;
    private String dateFrom;
    private String dateTo;

    public Employee(int employeeId, int projectId, String dateFrom,
                    String dateTo) throws ParseException {
        setEmployeeId(employeeId);
        setProjectId(projectId);
        setDateFrom(dateFrom);
        setDateTo(dateTo);

        checkDates();
    }

    public int getEmployeeId() {
        return employeeId;
    }

    private void setEmployeeId(int employeeId) {
        if (employeeId < 0) {
            throw new RuntimeException();
        }

        this.employeeId = employeeId;
    }

    public int getProjectId() {
        return projectId;
    }

    private void setProjectId(int projectId) {
        if (projectId < 0) {
            throw new RuntimeException();
        }

        this.projectId = projectId;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    private void setDateFrom(String dateFrom) {
        if (dateFrom == null) {
            throw new NullPointerException("Value can`t be null");
        }

        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    private void setDateTo(String dateTo) {
        if (dateTo.equals("NULL")) {
            SimpleDateFormat sdfDate =
                    new SimpleDateFormat("yyyy-MM-dd");
            Date now = new Date();
            dateTo = sdfDate.format(now);
        }

        this.dateTo = dateTo;
    }

    private void checkDates() throws ParseException {
        SimpleDateFormat dates = new SimpleDateFormat("yyyy-MM-dd");

        Date dateFromCheck = dates.parse
                (dateFrom);

        Date dateToCheck = dates.parse
                (dateTo);

        if (dateFromCheck.getTime() > dateToCheck.getTime()) {
            throw new RuntimeException("Not valid date!");
        }
    }
}
