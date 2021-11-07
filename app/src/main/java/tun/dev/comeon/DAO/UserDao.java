package tun.dev.comeon.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import tun.dev.comeon.entities.User;

@Dao
public interface UserDao {

    @Insert
    void InsertUser(User user) ; 

    @Query("SELECT * FROM user")
    List<User> getAllUsers();

    @Query("SELECT * FROM user u where u.login= :login and u.password = :password ")
    User getOneUser(String login ,String password);

}
