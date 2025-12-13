public class Patient{
    private int id;
    private String name;
    private int age;

    public Patient(int id, String name, int age){
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public int getAge() {
        return age;
    }
    public String getName() {
        return name;
    }
    public int getID(){
        return id;
    }

    public void setAge(int age){
        this.age = age;
    }
    public void setId(int id){
        this.id = id;
    }
    public void setName(String name){
        this.name = name;
    }

    public void information(){
        System.out.println("This patients name: " + name + ", age of this patient: " + age + ", id of this patient: " + id);
    }
}