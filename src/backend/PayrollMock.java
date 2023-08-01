package backend;

public class PayrollMock implements PayrollInterface{
    @Override
    public double netFromBrute(double bruteSalary, double deductions) {
        // Simulation de la fonction netFromBrute
        // Par exemple, on peut appliquer un pourcentage fixe pour les déductions
        return bruteSalary * (1 - deductions);
    }

    @Override
    public void deductionsReport(double netSalary, double deductions) {
        // Simulation de la fonction deductionsReport
        System.out.println("Net Salary: " + netSalary);
        System.out.println("Deductions: " + deductions);
    }
}
