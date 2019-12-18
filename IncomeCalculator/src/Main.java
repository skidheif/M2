import java.util.Scanner;

//программа для проверки может ли компания инвестировать, или с ее текущим бюджетом не стоит этого делать
public class Main
{
    //переменные различных типов, int, double
    private static int minIncome = 200000;
    private static int maxIncome = 900000;

    private static int officeRentCharge = 140000;
    private static int telephonyCharge = 12000;
    private static int internetAccessCharge = 7200;

    private static int assistantSalary = 45000;
    private static int financeManagerSalary = 90000;

    private static double mainTaxPercent = 0.24;
    private static double managerPercent = 0.15;

    private static double minInvestmentsAmount = 100000;

    public static void main(String[] args)
    {
        // вечный цикл
        while(true)
        {
            //вывод на консоль
            System.out.println("Введите сумму доходов компании за месяц " +
                "(от 200 до 900 тысяч рублей): ");
            //ввод значения
            int income = (new Scanner(System.in)).nextInt();

            //проверка введенного значения (вызов метода checkIncomeRange)
            if(!checkIncomeRange(income)) {
                continue;
            }

            //расчет зарплаты менеджера
            double managerSalary = income * managerPercent;
            //расчет бюджета
            double pureIncome = income - managerSalary - calculateFixedCharges();
            //расчет налогов
            double taxAmount = mainTaxPercent * pureIncome;
            //расчет возможности инвестировании, должно быть больше 100к для  boolean canMakeInvestments = true
            double pureIncomeAfterTax = pureIncome - taxAmount;

            // boolean переменная для проверки можно ли инвестировать или нет
            boolean canMakeInvestments = pureIncomeAfterTax >=
                minInvestmentsAmount;

            //вывод на экран (тут есть тернарный, проверка бюджета и прочая
            System.out.println("Зарплата менеджера: " + managerSalary);
            System.out.println("Общая сумма налогов: " +
                (taxAmount > 0 ? taxAmount : 0));
            System.out.println("Компания может инвестировать: " +
                (canMakeInvestments ? "да" : "нет"));
            if(pureIncome < 0) {
                System.out.println("Бюджет в минусе! Нужно срочно зарабатывать!");
            }
        }
    }

    //метод для проверки входит ли введенное пользователем число в нужные границы
    private static boolean checkIncomeRange(int income)
    {
        if(income < minIncome)
        {
            System.out.println("Доход меньше нижней границы");
            return false;
        }
        if(income > maxIncome)
        {
            System.out.println("Доход выше верхней границы");
            return false;
        }
        return true;
    }

    //метод расчета фиксированных расходов
    private static int calculateFixedCharges()
    {
        return officeRentCharge +
                telephonyCharge +
                internetAccessCharge +
                assistantSalary +
                financeManagerSalary;
    }
}
