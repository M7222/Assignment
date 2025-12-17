public class Hospital {
    private int id;
    private String Name;
    private String Address;
    private String HeadDoctor;
    private String[] Departments;

    public Hospital(int id, String Address, String HeadDoctor, String[] Departments, String Name){
        this.id = id;
        this.Name = Name;
        this.Address = Address;
        this.HeadDoctor = HeadDoctor;
        this.Departments = Departments;
    }

    public int getId(){
        return id;
    }
    public String getName(){
        return Name;
    }
    public String getAddress(){
        return Address;
    }
    public String getHeadDoctor(){
        return HeadDoctor;
    }
    public String[] getDepartments(){
        return Departments;
    }

    public void setId(int id){
        this.id = id;
    }
    public void setName(String Name){
        this.Name =  Name;
    }
    public void setAddress(String Address){
        this.Address = Address;
    }
    public void setHeadDoctor(String HeadDoctor){
        this.HeadDoctor = HeadDoctor;
    }
    public void setDepartments(String[] Departments){
        this.Departments = Departments;
    }

    public void HospitalInformation(){
        System.out.println("Name of hospital: " + Name + ", \n Address of Hospital: " + Address +"\n");
    }
    public void HospitalHeadDoctor(){
        System.out.println("Head Doctor of hospital: " + HeadDoctor + "\n");
    }
    public void HositalDepartments(){
        for (int i = 0; i < Departments.length; i++){
            System.out.println("Departments: " + Departments[i] + "\n");
        }
    }

    public void getInf0(){
        System.out.println("ID: " + id + ",\nName: " + Name + ",\nAddress: " + Address + ",\nHeadDoctor: " + HeadDoctor + "\n");
        HositalDepartments();
    }
}
