package org.midasvision;

import org.midasvision.db.DB;
import org.midasvision.exceptions.DbException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
  public static void main(String[] args) {

    Connection conn = null;
    Statement query = null;
    ResultSet result = null;

    try {
      conn = DB.getConnection();
      query = conn.createStatement();
      result = query.executeQuery("SELECT * FROM department");

      while(result.next()) {
        System.out.println(result.getInt("Id") + ", " + result.getString("Name"));
      }

    } catch (SQLException e) {
      throw new DbException(e.getMessage());
    } finally {
      DB.closeResultSet(result);
      DB.closeStatement(query);
      DB.closeConnection();
    }
  }
}