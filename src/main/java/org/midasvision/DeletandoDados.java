package org.midasvision;

import org.midasvision.db.DB;
import org.midasvision.exceptions.DBIntegrityException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeletandoDados {
    public static void main(String[] args) {

        Connection conn = null;
        PreparedStatement ps= null;

        try {
            conn = DB.getConnection();
            ps = conn.prepareStatement(
                "DELETE FROM seller WHERE(Id = ?)"
            );

            ps.setInt(1, 8);
            int rowsAffected = ps.executeUpdate();

            System.out.println("Rows affected: " + rowsAffected);
        } catch (SQLException e) {
            throw new DBIntegrityException(e.getMessage());
        } finally {
            DB.closeStatement(ps);
            DB.closeConnection();
        }
    }
}
