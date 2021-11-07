package tun.dev.comeon.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import java.util.List;

import tun.dev.comeon.entities.User;
import tun.dev.comeon.repositories.UserRepository;

public class UserViewModel extends AndroidViewModel {

    private UserRepository userRepository;
    private List<User> allUsers;

    public UserViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
        allUsers = userRepository.getListUsers();

    }

    public List<User> getAllUsers() {
        return allUsers;
    }


    public void InsertUser(User user) {
        userRepository.insert(user);
    }
}
