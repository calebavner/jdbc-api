package org.midasvision;

import org.midasvision.db.DB;
import org.midasvision.exceptions.DbException;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class InserindoDados {
  public static void main(String[] args) {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    Connection conn = null;
    PreparedStatement ps = null;

    try {
      conn = DB.getConnection();
      ps = conn.prepareStatement(
        """
          INSERT INTO seller (
            Name, Email, BirthDate, BaseSalary, DepartmentID
          ) VALUES (
            ?, ?, ?, ?, ?
          )            
          """, Statement.RETURN_GENERATED_KEYS);

      ps.setString(1, "Avner Caleb");
      ps.setString(2, "avner@email.com");
      ps.setDate(3, new java.sql.Date(sdf.parse("18/06/1988").getTime()));
      ps.setDouble(4, 3000D);
      ps.setInt(5, 4);

      int rowsAffected = ps.executeUpdate();

      if(rowsAffected > 0) {
        ResultSet rs = ps.getGeneratedKeys();
        while (rs.next()){
          int id = rs.getInt(1);
          System.out.println("Done! ID = " + id);
        }
      } else {
        System.out.println("No rows affected");
      }

    } catch (SQLException e) {
      throw new DbException(e.getMessage());

    } catch (ParseException e) {
      e.printStackTrace();

    }finally {
      DB.closeStatement(ps);
      DB.closeConnection();
    }
  }
}
