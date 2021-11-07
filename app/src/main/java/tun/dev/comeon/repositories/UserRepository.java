package tun.dev.comeon.repositories;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import tun.dev.comeon.DAO.UserDao;
import tun.dev.comeon.database.MyDatabase;
import tun.dev.comeon.entities.User;

public class UserRepository {


    private UserDao userDao;
    private List<User> listUsers;

    public UserRepository(Application application) {
        MyDatabase database = MyDatabase.getInstance(application);

        userDao = database.userDao();
        listUsers = userDao.getAllUsers();
    }

    // operations
    public List<User> getListUsers() {
        return listUsers;
    }

    public void insert(User user) {
        new InsertUserAsyncTask(userDao).execute(user);
    }


    public static class InsertUserAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDao userDao;

        private InsertUserAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.InsertUser(users[0]);
            return null;
        }
    }


}
