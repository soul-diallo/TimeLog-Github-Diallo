package backend;

public class Payroll implements PayrollInterface{
    @Override
    public double netFromBrute(double bruteSalary, double deductions) {
        return bruteSalary - deductions;
    }

    @Override
    public void deductionsReport(double netSalary, double deductions) {
        System.out.println("Net Salary: " + netSalary);
        System.out.println("Deductions: " + deductions);
    }
}
