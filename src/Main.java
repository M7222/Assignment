import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        Data_pool db = new Data_pool();

        // --- Пациенты ---
        db.addPatient(new Patient(1, "Miras", "Askar", 17));
        db.addPatient(new Patient(2, "Akylzhan", "Khafiz", 16));
        db.addPatient(new Patient(3, "Timur", "Yukhnovec", 18));
        db.addPatient(new Patient(4, "Adil", "Askarov", 22));

        // --- Врачи ---
        db.addDoctor(new MedicalProfessional(1, "Aibolit", 31, "Surgery"));
        db.addDoctor(new MedicalProfessional(2, "Zharas", 47, "Neurology"));

        // --- Больницы ---
        String[] dep1 = {"Surgery", "Cardiology", "Therapy"};
        String[] dep2 = {"Neurology", "Psychology", "Urology"};
        db.addHospital(new Hospital(1, "Kabanbay Batyr, 112", "Tolepbergen", dep1, "Alatau clinic"));
        db.addHospital(new Hospital(2, "Wall st. 52", "Dr. Dalero", dep2, "Dallero clinic"));

        // --- Вывод всех пациентов ---
        System.out.println("== All Patients ==");
        for (int i = 0; i < db.getPatients().size(); i++) {
            System.out.println(db.getPatients().get(i));
        }

        // --- Вывод врачей ---
        System.out.println("\n== All Doctors ==");
        for (int i = 0; i < db.getDoctors().size(); i++) {
            System.out.println(db.getDoctors().get(i));
        }

        // --- Вывод больниц с департаментами ---
        System.out.println("\n== All Hospitals ==");
        for (int i = 0; i < db.getHospitals().size(); i++) {
            Hospital h = db.getHospitals().get(i);
            System.out.println(h);
            String[] deps = h.getDepartments();
            System.out.println("Departments:");
            for (int j = 0; j < deps.length; j++) {
                System.out.println("- " + deps[j]);
            }
            System.out.println();
        }

        // --- Фильтры ---
        System.out.println("== Minor Patients ==");
        ArrayList<Patient> minors = db.getMinorPatients();
        for (int i = 0; i < minors.size(); i++) System.out.println(minors.get(i));

        System.out.println("\n== Doctors with specialization Surgery ==");
        ArrayList<MedicalProfessional> surgeons = db.getDoctorsBySpecialization("Surgery");
        for (int i = 0; i < surgeons.size(); i++) System.out.println(surgeons.get(i));

        System.out.println("\n== Hospitals with Neurology department ==");
        ArrayList<Hospital> neuroHospitals = db.getHospitalsByDepartment("Neurology");
        for (int i = 0; i < neuroHospitals.size(); i++) System.out.println(neuroHospitals.get(i));
    }
}
