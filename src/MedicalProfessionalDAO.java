import java.sql.*;
import java.util.ArrayList;

public class MedicalProfessionalDAO {

    public void save(MedicalProfessional m) {
        String sql = "INSERT INTO MedicalProfessional (id, name, age, specialization) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseContext.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, m.getId());
            pstmt.setString(2, m.getName());
            pstmt.setInt(3, m.getAge());
            pstmt.setString(4, m.getSpecialization());

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

    public void update(MedicalProfessional m) {
        String sql = "UPDATE MedicalProfessional SET name = ?, age = ?, specialization = ? WHERE id = ?";
        try (Connection conn = DatabaseContext.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, m.getName());
            pstmt.setInt(2, m.getAge());
            pstmt.setString(3, m.getSpecialization());
            pstmt.setInt(4, m.getId());

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Данные врача обновлены!");
            }
        } catch (SQLException e) {
            System.err.println("Ошибка при обновлении: " + e.getMessage());
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM MedicalProfessional WHERE id = ?";
        try (Connection conn = DatabaseContext.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Врач с ID " + id + " успешно удален.");
            } else {
                System.out.println("Врач с таким ID не найден.");
            }
        } catch (SQLException e) {
            System.err.println("Ошибка при удалении: " + e.getMessage());
        }
    }
}