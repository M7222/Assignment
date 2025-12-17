public class Main {
    public static void main(String[] args){
        Patient p1 = new Patient(1, "Miras", 17);
        Patient p2 = new Patient(2, "Akylzhan", 18);

        String[] departments1 = {"Surgery", "Cardiology", "Therapy"};
        String[] departments2 = {"Neurology", "Psychology"};

        MedicalProfessional Doctor1 = new MedicalProfessional(1, "Dr. Stone", 22, "Surgeon");
        Hospital hospital1 = new Hospital(1, "Kabanbay Batyr 1", "Dr. Stone", departments1, "Ishigami clinic");
        Hospital hospital2 = new Hospital(2, "Mangilik el 1", "Dr. Askar", departments2, "Aurora clinic");

        System.out.println("--Patients--\n");
        p1.getInfo();
        p2.getInfo();
        System.out.println("\n");

        System.out.println("--Medical Professional--\n");
        Doctor1.getInfo();
        System.out.println("\n");

        System.out.println("--Hospital--\n");
        hospital1.getInf0();
        hospital2.getInf0();
        System.out.println("\n");
    }
}