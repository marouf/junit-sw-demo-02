public class EmployeeSalaryCalculator {
    public double calculateAverageSalary(int[] salaries) {
        if (salaries == null || salaries.length == 0) {
            throw new IllegalArgumentException("Salaries array cannot be null or empty");
        }
        double sum = 0;
        for (int salary : salaries) {
            sum += salary;
        }
        return sum / salaries.length;
    }
}
