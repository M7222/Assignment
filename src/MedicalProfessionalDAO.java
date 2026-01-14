import java.sql.*;
import java.util.ArrayList;

public class MedicalProfessionalDAO {

    public void save(MedicalProfessional m) {
        String sql = "INSERT INTO MedicalProfessional (name, age, specialization) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseContext.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, m.getName());
            pstmt.setInt(2, m.getAge());
            pstmt.setString(3, m.getSpecialization());

            pstmt.executeUpdate();
            System.out.println("Врач сохранен!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<MedicalProfessional> getAll() {
        ArrayList<MedicalProfessional> doctors = new ArrayList<>();
        String sql = "SELECT * FROM MedicalProfessional";
        try (Connection conn = DatabaseContext.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                doctors.add(new MedicalProfessional(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("specialization")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doctors;
    }
}