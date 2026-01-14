import java.sql.*;
import java.util.ArrayList;

public class HospitalDAO {

    public void save(Hospital h) {
        // ИСПРАВЛЕНО: departments (было departmeents)
        String sql = "INSERT INTO hospital (name, address, head_doctor, departments) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseContext.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, h.getName());
            pstmt.setString(2, h.getAddress());
            pstmt.setString(3, h.getHeadDoctor());

            // Конвертируем массив ["A", "B"] в строку "A, B" для базы
            String deptsString = (h.getDepartments() != null) ? String.join(", ", h.getDepartments()) : "";
            pstmt.setString(4, deptsString);

            pstmt.executeUpdate();
            System.out.println("Больница '" + h.getName() + "' сохранена в БД!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Hospital> getAll() {
        ArrayList<Hospital> hospitals = new ArrayList<>();
        String sql = "SELECT * FROM hospital";

        try (Connection conn = DatabaseContext.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                // Конвертируем строку "A, B" обратно в массив для Java
                String deptsRaw = rs.getString("departments");
                String[] deptsArray = (deptsRaw != null && !deptsRaw.isEmpty()) ? deptsRaw.split(", ") : new String[0];

                hospitals.add(new Hospital(
                        rs.getInt("id"),
                        rs.getString("address"),
                        rs.getString("head_doctor"),
                        deptsArray,
                        rs.getString("name")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hospitals;
    }
}