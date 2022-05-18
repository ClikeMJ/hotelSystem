package dao.impl;

import dao.touristDao;
import domain.tourist;
import utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class touristDaoImpl implements touristDao {
    @Override
    public List<tourist> ListTouristByBusinessid(Integer id) {
        Connection con=null;
        PreparedStatement pst=null;
        ResultSet rs=null;

        List<tourist> list = new ArrayList<>();
        String sql = "select * from tourist where room_id=?";
        try {
            tourist tourist = null;
            con = DBUtil.getConnection();
            pst = con.prepareStatement(sql);
            pst.setInt(1,id);
            rs = pst.executeQuery();
            while (rs.next()) {
                tourist = tourist.builder()
                        .id(rs.getInt("id"))
                        .roomId(rs.getInt("room_id"))
                        .introduction(rs.getString("introduction"))
                        .name(rs.getString("name"))
                        .build();

                list.add(tourist);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, pst, con);
        }
        return list;
    }

    @Override
    public int saveTourist(tourist tourist) {

        Connection con=null;
        PreparedStatement pst=null;
        ResultSet rs=null;

        int touristId = 0;
        String sql = "insert into tourist(name,introduction,room_id) values (?,?,?)";
        try {
            con = DBUtil.getConnection();
            //返回自增长列值
            pst = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            pst.setString(1, tourist.getName());
            pst.setString(2, tourist.getIntroduction());
            pst.setInt(3, tourist.getRoomId());
            pst.executeUpdate();
            //获取自增长列值（一行一列）
            rs = pst.getGeneratedKeys();
            if (rs.next()) {
                touristId = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, pst, con);
        }
        return touristId;
    }

    @Override
    public tourist getTouristByid(Integer touristid) {
        Connection con=null;
        PreparedStatement pst=null;
        ResultSet rs=null;
        String sql = "select * from tourist where id=?";
        tourist tourist = null;
        try {
            con = DBUtil.getConnection();
            pst = con.prepareStatement(sql);
            pst.setInt(1, touristid);
            rs = pst.executeQuery();
            while (rs.next()) {
                tourist = tourist.builder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("name"))
                        .roomId(rs.getInt("room_id"))
                        .introduction(rs.getString("introduction"))
                        .build();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, pst, con);
        }
        return tourist;
    }

    @Override
    public int upDateMenu(tourist tourist) {
        Connection con=null;
        PreparedStatement pst=null;
        ResultSet rs=null;

        int result =0;
        String sql = "update tourist set name=?,introduction=? where id=?";
        try {
            con = DBUtil.getConnection();
            pst = con.prepareStatement(sql);
            pst.setString(1,tourist.getName());
            pst.setString(2,tourist.getIntroduction());
            pst.setInt(3, tourist.getId());


            result = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(null, pst, con);
        }
        return result;
    }

    @Override
    public int removeTourist(Integer touristid) {

        Connection con=null;
        PreparedStatement pst=null;
        ResultSet rs=null;
        int result =0;
        String sql = "delete from tourist where id=?";
        try {
            con = DBUtil.getConnection();
            pst = con.prepareStatement(sql);
            pst.setInt(1,touristid);

            result = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(null, pst, con);
        }
        return result;
    }
}
