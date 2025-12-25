import java.util.ArrayList;

public class Data_pool {
    private ArrayList<Patient> patients = new ArrayList<>();
    private ArrayList<MedicalProfessional> doctors = new ArrayList<>();
    private ArrayList<Hospital> hospitals = new ArrayList<>();

    // --- ADD ---
    public void addPatient(Patient p) { patients.add(p); }
    public void addDoctor(MedicalProfessional d) { doctors.add(d); }
    public void addHospital(Hospital h) { hospitals.add(h); }

    // --- GET ---
    public ArrayList<Patient> getPatients() { return patients; }
    public ArrayList<MedicalProfessional> getDoctors() { return doctors; }
    public ArrayList<Hospital> getHospitals() { return hospitals; }

    // --- Фильтры с обычными циклами ---
    public ArrayList<Patient> getMinorPatients() {
        ArrayList<Patient> minors = new ArrayList<>();
        for (int i = 0; i < patients.size(); i++) {
            if (patients.get(i).getAge() < 18) minors.add(patients.get(i));
        }
        return minors;
    }

    public ArrayList<MedicalProfessional> getDoctorsBySpecialization(String spec) {
        ArrayList<MedicalProfessional> list = new ArrayList<>();
        for (int i = 0; i < doctors.size(); i++) {
            if (doctors.get(i).getSpecialization().equalsIgnoreCase(spec)) list.add(doctors.get(i));
        }
        return list;
    }

    public ArrayList<Hospital> getHospitalsByDepartment(String department) {
        ArrayList<Hospital> list = new ArrayList<>();
        for (int i = 0; i < hospitals.size(); i++) {
            String[] deps = hospitals.get(i).getDepartments();
            for (int j = 0; j < deps.length; j++) {
                if (deps[j].equalsIgnoreCase(department)) {
                    list.add(hospitals.get(i));
                    break;
                }
            }
        }
        return list;
    }
}
