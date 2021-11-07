package tun.dev.comeon.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import tun.dev.comeon.DAO.QuizDao;
import tun.dev.comeon.DAO.UserDao;
import tun.dev.comeon.entities.Quiz;
import tun.dev.comeon.entities.User;

@Database(entities = {User.class, Quiz.class}, version = 2, exportSchema = false)
public abstract class MyDatabase extends RoomDatabase {

    private static MyDatabase instance;

    public abstract QuizDao quizDAO();
    public abstract UserDao userDao();


    public static synchronized MyDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    MyDatabase.class, "my_db ")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .addCallback(roomCallBack)
                    .build();
        }
       // context.deleteDatabase("my_db ") ;

        return instance;
    }

    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            System.out.println("blalblablalblalblalblablalblalblalb");
            new PopulateDbAsyncTask(instance).execute();
        }

        /*@Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDbAsyncTask(instance).execute();
        }*/
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {

        private QuizDao quizDao;
        private UserDao userDao;


        PopulateDbAsyncTask(MyDatabase db) {
            quizDao = db.quizDAO();
            userDao = db.userDao();


        }

        @Override
        protected Void doInBackground(Void... voids) {
           quizDao.addQuiz(new Quiz("Quelle est la superficie approximative de la Tunisie", "63 000 km2", "163 000 km2","563 000 km2","730 000 km2",2,"geographie"));
            quizDao.addQuiz(new Quiz("Laquelle de ces villes d'Afrique du Nord est située en Tunisie", "Sfax", "Agadir","Tobrouk","Maroc",1,"geographie"));
            quizDao.addQuiz(new Quiz("Comment s'appelle le cap situé à l'extrême nord de la Tunisie", "Le cap Vert", "Le cap Blanc","Le cap Bleu","Le cap Jaune",2,"geographie"));
            quizDao.addQuiz(new Quiz("Comment se nomme cette vaste sebkha occupant le centre du pays", "Le Chott Meriem", "Le Chott el-Arab","Le Chott el-Amazigh","Le Chott el-Jérid",4,"geographie"));
            quizDao.addQuiz(new Quiz("Lequel de ces golfes ne se situe pas en Tunisie", "Le golfe d'Hammamet", "Le golfe de Gabès","Le golfe de Syrte","Le golfe de Bizerte",3,"geographie"));
            quizDao.addQuiz(new Quiz("Lequel de ces pays d'Europe est le plus proche de la Tunisie", "L'Italie", "L'Espagne","La France","Allemagne",1,"geographie"));
            quizDao.addQuiz(new Quiz("Comment s'appelle le principal cours d'eau de la Tunisie", "Le Drâa", "Le Chelif","l'Ecoule","La Medjerda",4,"geographie"));
            quizDao.addQuiz(new Quiz("Comment s'appelle la principale île de la Tunisie", "Pantelleria", "Djerba","Lampedusa","Bahamas",2,"geographie"));
            quizDao.addQuiz(new Quiz("Combien de gouvernorats y a-t-il dans la Tunisie", "22", "23","20","24",4,"geographie"));
            quizDao.addQuiz(new Quiz("Quelle est la hauteur de la montagne El chaanbi", "1 640 m", "1 801 m","1 544 m","1 944 m",3,"geographie"));
            quizDao.addQuiz(new Quiz("Quelle est la superficie approximative de la Tunisie", "63 000 km2", "163 000 km2","563 000 km2","730 000 km2",2,"geographie"));
            quizDao.addQuiz(new Quiz("Quelle est la superficie approximative de la Tunisie", "63 000 km2", "163 000 km2","563 000 km2","730 000 km2",2,"geographie"));
            quizDao.addQuiz(new Quiz("Quelle est la superficie approximative de la Tunisie", "63 000 km2", "163 000 km2","563 000 km2","730 000 km2",2,"geographie"));
            quizDao.addQuiz(new Quiz("Quelle est la superficie approximative de la Tunisie", "63 000 km2", "163 000 km2","563 000 km2","730 000 km2",2,"geographie"));
            quizDao.addQuiz(new Quiz("Quelle est la superficie approximative de la Tunisie", "63 000 km2", "163 000 km2","563 000 km2","730 000 km2",2,"geographie"));

            //histoire
            quizDao.addQuiz(new Quiz("En quelle année a commencé la guerre civile d'Espagne ", "1856", "1896","1906","1936",4,"histoire"));
            quizDao.addQuiz(new Quiz("Dans quel pays, la Révolution orange eut-elle lieu en 2004 ", "Ukraine", "Pologne","Grèce","Turquie",1,"histoire"));
            quizDao.addQuiz(new Quiz("Qui fut destitué par Fidel Castro en 1959", "Marcos Pérez Jiménez ", "Rómulo Betancourt","Salvador Allende","Fulgencio Batista",4,"histoire"));
            quizDao.addQuiz(new Quiz("Quel est le premier président de la Tunisie", "Zine el-Abidine Ben Ali", "Hédi Nouira","Mahmoud El Materi","Habib Bourguiba",4,"histoire"));
            quizDao.addQuiz(new Quiz("En quelle année fut fondée la grande mosquée de Kairouan par Oqba Ibn Nafi ", "630", "640","670","690",3,"histoire"));
            quizDao.addQuiz(new Quiz("Que représente la date du 12 novembre 1956 en tunisie", "Décét de Mohamed Lamine Bey", "Admission de la Tunisie à l'ONU","Fondation de la Fédération Tunisienne de football","ondation de la Ligue tunisienne des droits",2,"histoire"));
            quizDao.addQuiz(new Quiz("Quelle est la dernière ville Tunisienne à être colonisée", "Sfax", "Kairouan","La Goulette","Sakiet Sidi Youssef",1,"histoire"));
            quizDao.addQuiz(new Quiz("Quelle est la première population qui a occupé la Tunisie", "Phéniciens", "Moustériens","Berbères","Byzantins",2,"histoire"));
            quizDao.addQuiz(new Quiz("Qui a gagné la bataille de Kasserine en février 1943", "Allemagne nazie", "États-Unis","France","Japon",1,"histoire"));
            quizDao.addQuiz(new Quiz("Quelle est la capitale des Hafsides (1228-1574)", "Kairouan", "Sfax","Bizerte","Tunis",4,"histoire"));
            quizDao.addQuiz(new Quiz("onse histoire ", "1-premiére reponse", "2-deuxiéme","3-reponse",1,"histoire"));
            quizDao.addQuiz(new Quiz("duese histoire ", "1-premiére reponse", "2-deuxiéme","3-reponse",1,"histoire"));
            quizDao.addQuiz(new Quiz("triese histoire ", "1-premiére reponse", "2-deuxiéme","3-reponse",1,"histoire"));
            quizDao.addQuiz(new Quiz("quatorse histoire ", "1-premiére reponse", "2-deuxiéme","3-reponse",1,"histoire"));
            quizDao.addQuiz(new Quiz("qinze histoire ", "1-premiére reponse", "2-deuxiéme","3-reponse",1,"histoire"));














            return null;
        }
    }


}
