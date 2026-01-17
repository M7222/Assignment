import java.sql.*;
import java.util.ArrayList;

public class HospitalDAO {

    public void save(Hospital h) {
        String sql = "INSERT INTO hospital (id, name, address, head_doctor, departments) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseContext.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, h.getId());
            pstmt.setString(2, h.getName());
            pstmt.setString(3, h.getAddress());
            pstmt.setString(4, h.getHeadDoctor());

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

    public void update(Hospital h) {
        String sql = "UPDATE hospital SET name = ?, address = ?, head_doctor = ?, departments = ? WHERE id = ?";

        try (Connection conn = DatabaseContext.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, h.getName());
            pstmt.setString(2, h.getAddress());
            pstmt.setString(3, h.getHeadDoctor());

            String deptsString = (h.getDepartments() != null) ? String.join(", ", h.getDepartments()) : "";
            pstmt.setString(4, deptsString);

            pstmt.setInt(5, h.getId());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Данные госпиталя с ID " + h.getId() + " успешно обновлены!");
            } else {
                System.out.println("Госпиталь с таким ID не найден.");
            }

        } catch (SQLException e) {
            System.err.println("Ошибка при обновлении: " + e.getMessage());
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM hospital WHERE id = ?";

        try (Connection conn = DatabaseContext.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Госпиталь с ID " + id + " удален.");
            } else {
                System.out.println("Не удалось найти госпиталь с ID " + id);
            }

        } catch (SQLException e) {
            System.err.println("Ошибка при удалении: " + e.getMessage());
        }
    }
}