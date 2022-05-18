package dao;

import domain.room;

import java.util.List;

public interface roomDao {
    List<room> listRoom();
    int saveRoom(String name);
    int removeRoom(Integer roomId);
    room getRoomByidBypassword(Integer id , String password);
    room getRoomByid(Integer id);
    int updateRoom(room ro);
    int upDateRoomBypassword(Integer id,String password);



}
