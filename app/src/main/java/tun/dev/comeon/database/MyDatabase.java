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
            quizDao.addQuiz(new Quiz("Quelle est la superficie approximative de la Tunisie", "63 000 km2", "163 000 km2", "563 000 km2", "730 000 km2", 2, "geographie"));
            quizDao.addQuiz(new Quiz("Laquelle de ces villes d'Afrique du Nord est situ??e en Tunisie", "Sfax", "Agadir", "Tobrouk", "Maroc", 1, "geographie"));
            quizDao.addQuiz(new Quiz("Comment s'appelle le cap situ?? ?? l'extr??me nord de la Tunisie", "Le cap Vert", "Le cap Blanc", "Le cap Bleu", "Le cap Jaune", 2, "geographie"));
            quizDao.addQuiz(new Quiz("Comment se nomme cette vaste sebkha occupant le centre du pays", "Le Chott Meriem", "Le Chott el-Arab", "Le Chott el-Amazigh", "Le Chott el-J??rid", 4, "geographie"));
            quizDao.addQuiz(new Quiz("Lequel de ces golfes ne se situe pas en Tunisie", "Le golfe d'Hammamet", "Le golfe de Gab??s", "Le golfe de Syrte", "Le golfe de Bizerte", 3, "geographie"));
            quizDao.addQuiz(new Quiz("Lequel de ces pays d'Europe est le plus proche de la Tunisie", "L'Italie", "L'Espagne", "La France", "Allemagne", 1, "geographie"));
            quizDao.addQuiz(new Quiz("Comment s'appelle le principal cours d'eau de la Tunisie", "Le Dr??a", "Le Chelif", "l'Ecoule", "La Medjerda", 4, "geographie"));
            quizDao.addQuiz(new Quiz("Comment s'appelle la principale ??le de la Tunisie", "Pantelleria", "Djerba", "Lampedusa", "Bahamas", 2, "geographie"));
            quizDao.addQuiz(new Quiz("Combien de gouvernorats y a-t-il dans la Tunisie", "22", "23", "20", "24", 4, "geographie"));
            quizDao.addQuiz(new Quiz("Quelle est la hauteur de la montagne El chaanbi", "1 640 m", "1 801 m", "1 544 m", "1 944 m", 3, "geographie"));
            quizDao.addQuiz(new Quiz("Quelle est la capitale de fait de la Suisse ?", " B??le", " Berne", " Zurich", " Lausanne",  2, "geographie"));
            quizDao.addQuiz(new Quiz("Dans quel pays peut-on trouver la ville de Tcheliabinsk ?", "En Ukraine ", "En Russie ", "En Pologne", "En Bi??lorussie ", 2, "geographie"));
            quizDao.addQuiz(new Quiz("O?? se situe le parc naturel du Perche ? ", "En Wallonie ", "En Normandie", " En Bretagne ", "En Irlande", 2, "geographie"));
            quizDao.addQuiz(new Quiz("Quel est le plus grand lac d???Am??rique du Nord ? ", "Le lac Ontario", " Le lac Huron", "Le lac Majeur ", "Le lac Sup??rieur ",4, "geographie"));
            quizDao.addQuiz(new Quiz("Quelle ville compte la plus haute densit?? de population au monde ?", " Delhi ", "Le Pr??-Saint-Gervais", " Le Caire", " Manille", 4, "geographie"));

            //histoire
            quizDao.addQuiz(new Quiz("En quelle ann??e a commenc?? la guerre civile d'Espagne ", "1856", "1896", "1906", "1936", 4, "histoire"));
            quizDao.addQuiz(new Quiz("Dans quel pays, la R??volution orange eut-elle lieu en 2004 ", "Ukraine", "Pologne", "Gr??ce", "Turquie", 1, "histoire"));
            quizDao.addQuiz(new Quiz("Qui fut destitu?? par Fidel Castro en 1959", "Marcos P??rez Jim??nez ", "R??mulo Betancourt", "Salvador Allende", "Fulgencio Batista", 4, "histoire"));
            quizDao.addQuiz(new Quiz("Quel est le premier pr??sident de la Tunisie", "Zine el-Abidine Ben Ali", "H??di Nouira", "Mahmoud El Materi", "Habib Bourguiba", 4, "histoire"));
            quizDao.addQuiz(new Quiz("En quelle ann??e fut fond??e la grande mosqu??e de Kairouan par Oqba Ibn Nafi ", "630", "640", "670", "690", 3, "histoire"));
            quizDao.addQuiz(new Quiz("Que repr??sente la date du 12 novembre 1956 en tunisie", "D??c??t de Mohamed Lamine Bey", "Admission de la Tunisie ?? l'ONU", "Fondation de la F??d??ration Tunisienne de football", "ondation de la Ligue tunisienne des droits", 2, "histoire"));
            quizDao.addQuiz(new Quiz("Quelle est la derni??re ville Tunisienne ?? ??tre colonis??e", "Sfax", "Kairouan", "La Goulette", "Sakiet Sidi Youssef", 1, "histoire"));
            quizDao.addQuiz(new Quiz("Quelle est la premi??re population qui a occup?? la Tunisie", "Ph??niciens", "Moust??riens", "Berb??res", "Byzantins", 2, "histoire"));
            quizDao.addQuiz(new Quiz("Qui a gagn?? la bataille de Kasserine en f??vrier 1943", "Allemagne nazie", "??tats-Unis", "France", "Japon", 1, "histoire"));
            quizDao.addQuiz(new Quiz("Quelle est la capitale des Hafsides (1228-1574)", "Kairouan", "Sfax", "Bizerte", "Tunis", 4, "histoire"));
            quizDao.addQuiz(new Quiz("onse histoire ", "1-premi??re reponse", "2-deuxi??me", "3-reponse", 1, "histoire"));
            quizDao.addQuiz(new Quiz("duese histoire ", "1-premi??re reponse", "2-deuxi??me", "3-reponse", 1, "histoire"));
            quizDao.addQuiz(new Quiz("triese histoire ", "1-premi??re reponse", "2-deuxi??me", "3-reponse", 1, "histoire"));
            quizDao.addQuiz(new Quiz("quatorse histoire ", "1-premi??re reponse", "2-deuxi??me", "3-reponse", 1, "histoire"));
            quizDao.addQuiz(new Quiz("qinze histoire ", "1-premi??re reponse", "2-deuxi??me", "3-reponse", 1, "histoire"));



            quizDao.addQuiz(new Quiz("Quel c??l??bre dictateur dirigea l???URSS du milieu des ann??es 1920 ?? 1953 ? ", " Staline ", "Molotov ", "Trotski", " L??nine ", 1, "culture"));
            quizDao.addQuiz(new Quiz("Dans quel pays peut-on trouver la Catalogne, l???Andalousie et la Castille ?", " La France", " La Portugal", " L'Italie ", "L'Espagne", 4, "culture"));
            quizDao.addQuiz(new Quiz("Qui a dit : ?? Le sort en est jet?? ?? (Alea jacta est) ? ", "Vercing??torix ", "Attila", " Auguste", " C??sar", 4, "culture"));
            quizDao.addQuiz(new Quiz("qui doit-on la chanson ?? I Shot the Sheriff ?? ?", " Bob Marley ", "Jim Morrison", " Eric Clapton", " UB40", 1, "culture"));
            quizDao.addQuiz(new Quiz("Quel pays a remport?? la coupe du monde de football en 2014 ?", " L'Allemagne", " L'Italie", " Le Br??sil", " L'Argentine",  1, "culture"));
            quizDao.addQuiz(new Quiz("Dans quelle ville italienne l???action de la pi??ce de Shakespeare ?? Rom??o et Juliette ?? se situe-t-elle ? ", "Venise ", "Rome", " Milan", " V??rone ", 4, "culture"));
            quizDao.addQuiz(new Quiz("Par quel mot d??signe-t-on une belle-m??re cruelle ? ", "Une godiche ", "Une jocrisse", " Une mar??tre", " Une chenapan",  3, "culture"));
            quizDao.addQuiz(new Quiz("Qui ??tait le dieu de la guerre dans la mythologie grecque ?", " Ar??s ", "Had??s ", "Apollon ", "Herm??s",  1, "culture"));
            quizDao.addQuiz(new Quiz("De quel courant philosophique Plotin est-il le grand repr??sentant ?", " Le n??oplatonisme ", "Le scepticisme", " Le sto??cisme ", "L'aristot??lisme",  1, "culture"));
            quizDao.addQuiz(new Quiz("Qui a r??alis?? le film ?? In the mood for love ?? ? ", "Scorsese ", "Wong Kar-Wai", " Zhang Yimou Chan", " Feng Zhao", 2, "culture"));
            quizDao.addQuiz(new Quiz("De l?????uvre de quel ??crivain est tir??e la c??l??bre question ?? Que sais-je ? ?? ", "Montaigne", " Voltaire", " ??tienne de la Bo??tie ", "Diderot",  1, "culture"));
            quizDao.addQuiz(new Quiz("Quelle race d???animal est un briard ? ", "Un chat", " Un chien", " Un canard", " Un cheval", 2, "culture"));
            quizDao.addQuiz(new Quiz("O?? est n?? Mozart ? ", "Salzbourg ", "Turin ", "Vienne ", "Venise",  1, "culture"));
            quizDao.addQuiz(new Quiz("Quelle th??orie doit-on ?? Isaac Newton ? ", "La th??orie des cordes", " La th??orie de la gravitation universelle", " La th??orie atomique", " La th??orie de l'??volution des esp??ces",  2, "culture"));
            quizDao.addQuiz(new Quiz("Parmi les hommes politiques suivants, lequel a succ??d?? ?? Hugo Chavez en tant que Pr??sident du Venezuela ?", " Nicolas Maduro ", "Evo Morales ", "Rafael Correa", "Lula",  1, "culture"));
            quizDao.addQuiz(new Quiz("Que signifie ?? procrastiner ?? ? ", "Parler dans un langage particuli??rement vulgaire ", "Contredire syst??matiquement son interlocuteur", " Remettre ?? plus tard quelque chose ", "??tudier beaucoup en vue d'un examen", 3, "culture"));


            return null;
        }
    }


}
