package service;

public interface touristService {
    void getAllTourist(Integer id);
    void save(Integer id);
    void getTouristById(Integer touristid);
    void updateTourist(Integer touristid);
    void deleteTourist(Integer touristid);
}
