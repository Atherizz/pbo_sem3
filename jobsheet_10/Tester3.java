public class Tester3 {
    public static void main(String[] args) {
        PermanentEmployee pEmp = new PermanentEmployee("Dedik", 500);
        InternshipEmployee iEmp = new InternshipEmployee("Sunarto", 5);
        ElectricityBill eBill = new ElectricityBill(5, "A-1");
        Employee e[] = {pEmp, iEmp};
        Payable p[] = {pEmp, eBill};
        // Demonstrasi polymorphism sederhana
        System.out.println("-- Employee Polymorphism --");
        for(Employee emp : e){
            System.out.println(emp.getEmployeeInfo());
        }
        System.out.println("-- Payable Polymorphism --");
        for(Payable pay : p){
            System.out.println("Payment = "+pay.getPaymentAmount());
        }
    }
}
