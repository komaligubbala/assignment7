import java.util.*;

class EmployeeWorkHours {
    private String employeeName;
    private int[] hoursWorkedPerDay;

    public EmployeeWorkHours(String employeeName, int[] hoursWorkedPerDay) {
        this.employeeName = employeeName;
        this.hoursWorkedPerDay = hoursWorkedPerDay;
    }

    public int getTotalHoursWorked() {
        int total = 0;
        for (int hours : hoursWorkedPerDay) {
            total += hours;
        }
        return total;
    }
}

public class EmployeeWorkHoursAnalyzer {
    public static void main(String[] args) {
        // Sample employee work hours data
        List<EmployeeWorkHours> workHoursList = Arrays.asList(
            new EmployeeWorkHours("Employee1", new int[]{8, 8, 8, 8, 8}),
            new EmployeeWorkHours("Employee2", new int[]{9, 8, 7, 8, 9}),
            new EmployeeWorkHours("Employee3", new int[]{8, 8, 9, 8, 8}),
            new EmployeeWorkHours("Employee4", new int[]{7, 7, 6, 8, 8}),
            new EmployeeWorkHours("Employee5", new int[]{10, 10, 10, 10, 10})
        );

        // Define thresholds for categorizing work hours
        int threshold = 40;

        // Initialize maps to store counts and total hours worked per group
        Map<String, Integer> employeeCountByGroup = new LinkedHashMap<>();
        Map<String, Integer> totalHoursWorkedByGroup = new LinkedHashMap<>();

        // Initialize counts and total hours worked to zero for each group
        employeeCountByGroup.put("Less than 40 hours", 0);
        employeeCountByGroup.put("Exactly 40 hours", 0);
        employeeCountByGroup.put("More than 40 hours", 0);

        totalHoursWorkedByGroup.put("Less than 40 hours", 0);
        totalHoursWorkedByGroup.put("Exactly 40 hours", 0);
        totalHoursWorkedByGroup.put("More than 40 hours", 0);

        // Iterate through employee work hours and categorize them
        for (EmployeeWorkHours workHours : workHoursList) {
            int totalHours = workHours.getTotalHoursWorked();
            String group;
            if (totalHours < threshold) {
                group = "Less than 40 hours";
            } else if (totalHours == threshold) {
                group = "Exactly 40 hours";
            } else {
                group = "More than 40 hours";
            }
            employeeCountByGroup.put(group, employeeCountByGroup.get(group) + 1);
            totalHoursWorkedByGroup.put(group, totalHoursWorkedByGroup.get(group) + totalHours);
        }

        // Calculate average hours worked per day for each group and display results
        System.out.println("Group                 | Employee Count | Avg Hours per Day");
        System.out.println("-----------------------------------------------");
        for (Map.Entry<String, Integer> entry : employeeCountByGroup.entrySet()) {
            String group = entry.getKey();
            int count = entry.getValue();
            int totalHours = totalHoursWorkedByGroup.get(group);
            double avgHoursPerDay = totalHours / (double) (count * 5); // Assuming 5 working days in a week
            System.out.printf("%-20s | %-14d | %.2f\n", group, count, avgHoursPerDay);
        }
    }
}
