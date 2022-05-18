package dao.impl;


import dao.adminDao;
import domain.admin;
import utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class adminDaoImpl  implements adminDao {
    @Override
    public admin getadminByidBypassword(admin am) {
        Connection con=null;
        PreparedStatement pst=null;
        ResultSet rs=null;

        admin ad=null;
        String sql="select * from admin where id=? and password=?";


        try {
            con= DBUtil.getConnection();
            pst=con.prepareStatement(sql);
            pst.setInt(1,am.getId());
            pst.setString(2,am.getPassword());
            rs= pst.executeQuery();
            while (rs.next()) {
                ad = ad.builder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("name"))
                        .password(rs.getString("password"))
                        .build();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        DBUtil.close(rs,pst,con);
        return ad;


    }
}
