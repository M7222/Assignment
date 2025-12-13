public class MedicalProfessional {
    private int id;
    private String name;
    private int age;
    private String specialization;

    public MedicalProfessional(int id, String name, int age, String specialization){
        this.id = id;
        this.name = name;
        this.age = age;
        this.specialization = specialization;
    }

    public int getID(){
        return id;
    }
    public String getName(){
        return name;
    }
    public int getAge(){
        return age;
    }
    public String getSpecialization(){
        return specialization;
    }

    public void setId(int id){
        this.id = id;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setAge(int age){
        this.age = age;
    }
    public void setSpecialization(String specialization){
        this.specialization = specialization;
    }

    public void ProfessorInformation(){
        System.out.println("Professor name: " + name + ", \n age of professor is:" + age );
    }
    public void ProfessorSpecialization(){
        System.out.println("Professor specalization:" + specialization);
    }
}
