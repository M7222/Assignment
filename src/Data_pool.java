import java.util.ArrayList;

public class Data_pool {
    private PatientDAO patientDAO = new PatientDAO();

    public void addPatient(Patient p) {
        patientDAO.save(p); // Теперь данные летят в pgAdmin
    }

    public ArrayList<Patient> getPatients() {
        return patientDAO.getAll(); // Данные берутся из таблицы БД
    }

    // Твой фильтр для несовершеннолетних
    public ArrayList<Patient> getMinorPatients() {
        ArrayList<Patient> all = getPatients();
        ArrayList<Patient> minors = new ArrayList<>();
        for (Patient p : all) {
            if (p.getAge() < 18) minors.add(p);
        }
        return minors;
    }

    private MedicalProfessionalDAO doctorDAO = new MedicalProfessionalDAO();

    public void addDoctor(MedicalProfessional m) {
        doctorDAO.save(m);
    }

    public ArrayList<MedicalProfessional> getDoctors() {
        return doctorDAO.getAll();
    }

    // Твой фильтр из Main
    public ArrayList<MedicalProfessional> getDoctorsBySpecialization(String spec) {
        ArrayList<MedicalProfessional> result = new ArrayList<>();
        for (MedicalProfessional m : getDoctors()) {
            if (m.getSpecialization().equalsIgnoreCase(spec)) result.add(m);
        }
        return result;
    }

    // 1. Добавляем экземпляр DAO для больниц
    private HospitalDAO hospitalDAO = new HospitalDAO();

    // 2. Метод для добавления больницы
    public void addHospital(Hospital h) {
        hospitalDAO.save(h);
    }

    // 3. Метод для получения списка всех больниц
    public ArrayList<Hospital> getHospitals() {
        return hospitalDAO.getAll();
    }

    // 4. Метод фильтрации по отделениям (для Main)
    public ArrayList<Hospital> getHospitalsByDepartment(String dept) {
        ArrayList<Hospital> allHospitals = getHospitals();
        ArrayList<Hospital> result = new ArrayList<>();

        for (Hospital h : allHospitals) {
            // Проверяем каждое отделение в массиве больницы
            for (String d : h.getDepartments()) {
                if (d.equalsIgnoreCase(dept)) {
                    result.add(h);
                    break; // Нашли нужное отделение — добавляем больницу и переходим к следующей
                }
            }
        }
        return result;
    }
}