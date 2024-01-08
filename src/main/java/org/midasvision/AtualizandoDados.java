package org.midasvision;

import org.midasvision.db.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AtualizandoDados {
  public static void main(String[] args) {

    Connection conn = null;
    PreparedStatement ps = null;

    try {
      conn = DB.getConnection();
      ps = conn.prepareStatement(
        "UPDATE seller "
            + "SET BaseSalary = BaseSalary + ?"
            + "WHERE (DepartmentId = ?)"
      );

      ps.setDouble(1, 300D);
      ps.setInt(2, 2);

      int rowsAffected = ps.executeUpdate();
      System.out.println("Success!! " + rowsAffected + " rows affected");

    } catch (SQLException e) {
      e.printStackTrace();

    }finally {
      DB.closeStatement(ps);
      DB.closeConnection();
    }
  }
}
