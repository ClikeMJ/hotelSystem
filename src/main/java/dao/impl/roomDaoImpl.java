package dao.impl;

import dao.roomDao;
import domain.room;
import utils.DBUtil;

import java.sql.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.*;

public class roomDaoImpl implements roomDao {
    @Override
    public List<room> listRoom() {
        Connection con=null;
        PreparedStatement pst=null;
        ResultSet rs=null;

        List<room> list = new ArrayList<>();
        String sql="select * from room";


        try {
            con= DBUtil.getConnection();
            pst=con.prepareStatement(sql);
            rs= pst.executeQuery();
            while (rs.next()) {
              room ro = room.builder()
                      .id(rs.getInt("id"))
                      .name(rs.getString("name"))
                      .password(rs.getString("password"))
                      .introduction(rs.getString("introduction"))
                      .checkout_data(rs.getDate("checkout_data"))
                      .build();

              list.add(ro);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        DBUtil.close(rs,pst,con);
        return list;
    }

    @Override
    public int saveRoom(String name) {
        Connection con=null;
        PreparedStatement pst=null;
        ResultSet rs=null;

        int roomId = 0;
        String sql = "insert into room(Name,password) values (?,'123')";
        try {
            con = DBUtil.getConnection();
            //返回自增长列值
            pst = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            pst.setString(1, name);
            pst.executeUpdate();
            //获取自增长列值（一行一列）
            rs = pst.getGeneratedKeys();
            if (rs.next()) {
                roomId = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, pst, con);
        }
        return roomId;
    }

    @Override
    public int removeRoom(Integer roomId) {
        Connection con=null;
        PreparedStatement pst=null;
        ResultSet rs=null;
        int result = 0;
        String delMenusql = "delete from room where id=?";
        String delBusinesssql = "delete from tourist where room_id=?";
        try {
            con = DBUtil.getConnection();
            con.setAutoCommit(false);
            pst = con.prepareStatement(delMenusql);
            pst.setInt(1, roomId);
            pst.executeUpdate();

            pst = con.prepareStatement(delBusinesssql);
            pst.setInt(1, roomId);
            result = pst.executeUpdate();

            con.commit();
        } catch (SQLException ex) {
            result = 0;
            try {
                con.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ex.printStackTrace();
        } finally {
            DBUtil.close(null, pst, con);
        }
        return result;
    }

    @Override
    public room getRoomByidBypassword(Integer id, String password) {
        Connection con=null;
        PreparedStatement pst=null;
        ResultSet rs=null;
        room ro = null;
        String sql="select * from room where id=? and password=?";
        try {
            con= DBUtil.getConnection();
            pst=con.prepareStatement(sql);
            pst.setInt(1,id);
            pst.setString(2,password);

            rs= pst.executeQuery();
            while (rs.next()) {
                ro = ro.builder()
                        .name(rs.getString("name"))
                        .id(rs.getInt("id"))
                        .password(rs.getString("password"))
                        .introduction(rs.getString("introduction"))
                        .checkout_data(rs.getDate("checkout_data"))
                        .build();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ro;
    }

    @Override
    public room getRoomByid(Integer id) {
        Connection con=null;
        PreparedStatement pst=null;
        ResultSet rs=null;

        room au = null;
        String sql="select * from room where id=?";
        try {
            con= DBUtil.getConnection();
            pst=con.prepareStatement(sql);
            pst.setInt(1,id);
            rs= pst.executeQuery();
            while (rs.next()) {
                au = au.builder()
                        .name(rs.getString("name"))
                        .id(rs.getInt("id"))
                        .password(rs.getString("password"))
                        .introduction(rs.getString("introduction"))
                        .checkout_data(rs.getDate("checkout_data"))
                        .build();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return au;
    }

    @Override
    public int updateRoom(room au) {
        Connection con=null;
        PreparedStatement pst=null;
        ResultSet rs=null;

        int result = 0;
        String sql = "update room set name=?,password=?,introduction=?,checkout_data=? where id=?";
        try {
            con = DBUtil.getConnection();
            pst = con.prepareStatement(sql);
            pst.setString(1, au.getName());
            pst.setString(2,au.getPassword());
            pst.setString(3,au.getIntroduction());
            pst.setDate(4, (Date) au.getCheckout_data());

            pst.setInt(5,au.getId());
            result = pst.executeUpdate();



        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DBUtil.close(null, pst, con);
        }
        return result;
    }

    @Override
    public int upDateRoomBypassword(Integer id, String password) {
        Connection con=null;
        PreparedStatement pst=null;
        ResultSet rs=null;

        int result = 0;
        String sql = "update room set password=? where id=?";
        try {
            con = DBUtil.getConnection();
            pst = con.prepareStatement(sql);
            pst.setString(1,password );
            pst.setInt(2,id);
            result = pst.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DBUtil.close(null, pst, con);
        }
        return result;
    }
    }
