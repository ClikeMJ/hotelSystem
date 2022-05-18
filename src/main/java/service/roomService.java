package service;

import domain.room;

public interface roomService {
    void  getAllRoomList();
    void  save();
    void  delete();
    room roomLogin();
    void  getRoomByid();
    void  updateRoom(Integer id);
    void  updateRoomPassword(Integer id);
}
