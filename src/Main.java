import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Инициализируем DAO для работы с БД
        PatientDAO patientDAO = new PatientDAO();
        MedicalProfessionalDAO doctorDAO = new MedicalProfessionalDAO();
        HospitalDAO hospitalDAO = new HospitalDAO();

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n======= СИСТЕМА УПРАВЛЕНИЯ КЛИНИКОЙ =======");
            System.out.println("1. Пациенты (Добавить/Показать/Удалить)");
            System.out.println("2. Доктора (Добавить/Показать/Обновить)");
            System.out.println("3. Госпитали (Добавить/Показать)");
            System.out.println("0. Выход");
            System.out.print("Выберите категорию: ");

            int category = scanner.nextInt();
            scanner.nextLine();

            switch (category) {
                case 1: // РАБОТА С ПАЦИЕНТАМИ
                    System.out.println("1. Добавить | 2. Показать всех | 3. Удалить");
                    int pChoice = scanner.nextInt();
                    scanner.nextLine();
                    if (pChoice == 1) {
                        System.out.print("ID: "); int id = scanner.nextInt(); scanner.nextLine();
                        System.out.print("Имя: "); String name = scanner.nextLine();
                        System.out.print("Фамилия: "); String sur = scanner.nextLine();
                        System.out.print("Возраст: "); int age = scanner.nextInt();
                        patientDAO.save(new Patient(id, name, sur, age));
                    } else if (pChoice == 2) {
                        for (Patient p : patientDAO.getAll()) System.out.println(p);
                    } else if (pChoice == 3) {
                        System.out.print("Введите ID для удаления: ");
                        int delId = scanner.nextInt();
                        patientDAO.delete(delId);
                    }
                    break;

                case 2: // РАБОТА С ДОКТОРАМИ
                    System.out.println("1. Добавить | 2. Показать всех | 3. Обновить данные");
                    int dChoice = scanner.nextInt();
                    scanner.nextLine();
                    if (dChoice == 1) {
                        System.out.print("ID: "); int id = scanner.nextInt(); scanner.nextLine();
                        System.out.print("Имя: "); String name = scanner.nextLine();
                        System.out.print("Возраст: "); int age = scanner.nextInt(); scanner.nextLine();
                        System.out.print("Спец-ция: "); String spec = scanner.nextLine();
                        doctorDAO.save(new MedicalProfessional(id, name, age, spec));
                    } else if (dChoice == 2) {
                        for (MedicalProfessional m : doctorDAO.getAll()) System.out.println(m);
                    } else if (dChoice == 3) {
                        System.out.print("ID врача для обновления: "); int id = scanner.nextInt(); scanner.nextLine();
                        System.out.print("Новое имя: "); String name = scanner.nextLine();
                        System.out.print("Новый возраст: "); int age = scanner.nextInt(); scanner.nextLine();
                        System.out.print("Новая спец-ция: "); String spec = scanner.nextLine();
                        doctorDAO.update(new MedicalProfessional(id, name, age, spec));
                    }
                    break;

                case 3: // РАБОТА С ГОСПИТАЛЯМИ
                    System.out.println("1. Добавить | 2. Показать все");
                    int hChoice = scanner.nextInt();
                    scanner.nextLine();
                    if (hChoice == 1) {
                        System.out.print("ID: "); int id = scanner.nextInt(); scanner.nextLine();
                        System.out.print("Адрес: "); String addr = scanner.nextLine();
                        System.out.print("Директор: "); String head = scanner.nextLine();
                        System.out.print("Название: "); String n = scanner.nextLine();
                        System.out.print("Департаменты (через запятую): ");
                        String[] deps = scanner.nextLine().split(",");
                        hospitalDAO.save(new Hospital(id, addr, head, deps, n));
                    } else if (hChoice == 2) {
                        for (Hospital h : hospitalDAO.getAll()) {
                            System.out.println(h + " Департаменты: " + String.join(", ", h.getDepartments()));
                        }
                    }
                    break;

                case 0:
                    running = false;
                    System.out.println("Выход из системы...");
                    break;

                default:
                    System.out.println("Неверный выбор!");
            }
        }
        scanner.close();
    }
}