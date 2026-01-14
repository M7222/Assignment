import java.sql.*;
import java.util.ArrayList;

public class PatientDAO {

    // Шаги 1-4: Сохранение пациента в базу
    public void save(Patient p) {
        String sql = "INSERT INTO patient (name, surname, age) VALUES (?, ?, ?)";

        // Использование try-with-resources автоматически закрывает соединение (Шаг 6)
        try (Connection conn = DatabaseContext.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, p.getName());
            pstmt.setString(2, p.getSurname());
            pstmt.setInt(3, p.getAge());

            pstmt.executeUpdate();
            System.out.println("Пациент " + p.getName() + " сохранен в PostgreSQL!");

        } catch (SQLException e) {
            // Обработка исключений (стр. 5 лекции)
            e.printStackTrace();
        }
    }

    // Шаг 5: Получение всех пациентов из базы
    public ArrayList<Patient> getAll() {
        ArrayList<Patient> patients = new ArrayList<>();
        String sql = "SELECT * FROM patient";

        try (Connection conn = DatabaseContext.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                // Создаем объект Patient на основе данных из строки таблицы
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
}