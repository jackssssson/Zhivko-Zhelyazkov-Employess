import task.EmployeesDaysOfWork;
import task.EmployeesDaysOfWorkImpl;

import java.text.ParseException;

public class CoupleEmployees {
    public static void main(String[] args) throws ParseException {
        EmployeesDaysOfWork work = new EmployeesDaysOfWorkImpl();
        work.run();
    }
}
