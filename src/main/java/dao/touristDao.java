package dao;


import domain.tourist;


import java.util.List;

public interface touristDao {
     List<tourist> ListTouristByBusinessid(Integer id);
     int saveTourist(tourist tourist );
     tourist getTouristByid(Integer touristid);
     int  upDateMenu(tourist tourist);
     int  removeTourist(Integer touristid);
}
