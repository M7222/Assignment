import java.sql.*;
import java.util.ArrayList;

public class PatientDAO {

    public void save(Patient p) {
        String sql = "INSERT INTO patient (id, name, surname, age) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseContext.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, p.getId());
            pstmt.setString(2, p.getName());
            pstmt.setString(3, p.getSurname());
            pstmt.setInt(4, p.getAge());

            pstmt.executeUpdate();
            System.out.println("Пациент " + p.getName() + " сохранен в PostgreSQL!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Patient> getAll() {
        ArrayList<Patient> patients = new ArrayList<>();
        String sql = "SELECT * FROM patient";

        try (Connection conn = DatabaseContext.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Patient p = new Patient(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getInt("age")
                );
                patients.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patients;
    }

    public void update(Patient p) {
        String sql = "UPDATE patient SET name = ?, surname = ?, age = ? WHERE id = ?";

        try (Connection conn = DatabaseContext.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, p.getName());
            pstmt.setString(2, p.getSurname());
            pstmt.setInt(3, p.getAge());
            pstmt.setInt(4, p.getId()); // ID используется в условии WHERE

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Данные пациента с ID " + p.getId() + " успешно обновлены!");
            }
        } catch (SQLException e) {
            System.err.println("Ошибка при обновлении пациента: " + e.getMessage());
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM patient WHERE id = ?";

        try (Connection conn = DatabaseContext.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Пациент с ID " + id + " удален из базы.");
            } else {
                System.out.println("Пациент с ID " + id + " не найден.");
            }
        } catch (SQLException e) {
            System.err.println("Ошибка при удалении пациента: " + e.getMessage());
        }
    }
}