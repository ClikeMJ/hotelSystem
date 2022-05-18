package dao;

import domain.admin;

import java.sql.SQLException;

public interface adminDao {
     admin getadminByidBypassword(admin am) throws SQLException;
}
