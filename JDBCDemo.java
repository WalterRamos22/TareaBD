package TareaBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCDemo {
    public static void main(String[] args) {
        // Datos de conexión
        String url = "jdbc:mysql://localhost:3306/tareadb";
        String user = "root";
        String password = "administrador";

        // Consulta SQL
        String query = "SELECT id, nombre, edad FROM usuarios WHERE edad > ?";

        // Conexión y ejecución de la consulta
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            // Establecemos el valor del parámetro en la consulta
            stmt.setInt(1, 18);

            // Ejecutamos la consulta y obtenemos los resultados
            try (ResultSet rs = stmt.executeQuery()) {
                // Iteramos sobre los resultados
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String nombre = rs.getString("nombre");
                    int edad = rs.getInt("edad");

                    System.out.println("ID: " + id + ", Nombre: " + nombre + ", Edad: " + edad);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}