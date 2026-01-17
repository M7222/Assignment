import java.util.ArrayList;

public class Data_pool {
    private PatientDAO patientDAO = new PatientDAO();

    public void addPatient(Patient p) {
        patientDAO.save(p);
    }

    public ArrayList<Patient> getPatients() {
        return patientDAO.getAll();
    }

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

    public ArrayList<MedicalProfessional> getDoctorsBySpecialization(String spec) {
        ArrayList<MedicalProfessional> result = new ArrayList<>();
        for (MedicalProfessional m : getDoctors()) {
            if (m.getSpecialization().equalsIgnoreCase(spec)) result.add(m);
        }
        return result;
    }

    private HospitalDAO hospitalDAO = new HospitalDAO();

    public void addHospital(Hospital h) {
        hospitalDAO.save(h);
    }

    public ArrayList<Hospital> getHospitals() {
        return hospitalDAO.getAll();
    }

    public ArrayList<Hospital> getHospitalsByDepartment(String dept) {
        ArrayList<Hospital> allHospitals = getHospitals();
        ArrayList<Hospital> result = new ArrayList<>();

        for (Hospital h : allHospitals) {
            for (String d : h.getDepartments()) {
                if (d.equalsIgnoreCase(dept)) {
                    result.add(h);
                    break;
                }
            }
        }
        return result;
    }
}