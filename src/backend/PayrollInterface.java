package backend;

public interface PayrollInterface {
    double netFromBrute(double bruteSalary, double deductions);
    void deductionsReport(double netSalary, double deductions);
}
