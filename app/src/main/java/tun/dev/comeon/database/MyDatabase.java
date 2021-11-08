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
            quizDao.addQuiz(new Quiz("Laquelle de ces villes d'Afrique du Nord est située en Tunisie", "Sfax", "Agadir", "Tobrouk", "Maroc", 1, "geographie"));
            quizDao.addQuiz(new Quiz("Comment s'appelle le cap situé à l'extrême nord de la Tunisie", "Le cap Vert", "Le cap Blanc", "Le cap Bleu", "Le cap Jaune", 2, "geographie"));
            quizDao.addQuiz(new Quiz("Comment se nomme cette vaste sebkha occupant le centre du pays", "Le Chott Meriem", "Le Chott el-Arab", "Le Chott el-Amazigh", "Le Chott el-Jérid", 4, "geographie"));
            quizDao.addQuiz(new Quiz("Lequel de ces golfes ne se situe pas en Tunisie", "Le golfe d'Hammamet", "Le golfe de Gabès", "Le golfe de Syrte", "Le golfe de Bizerte", 3, "geographie"));
            quizDao.addQuiz(new Quiz("Lequel de ces pays d'Europe est le plus proche de la Tunisie", "L'Italie", "L'Espagne", "La France", "Allemagne", 1, "geographie"));
            quizDao.addQuiz(new Quiz("Comment s'appelle le principal cours d'eau de la Tunisie", "Le Drâa", "Le Chelif", "l'Ecoule", "La Medjerda", 4, "geographie"));
            quizDao.addQuiz(new Quiz("Comment s'appelle la principale île de la Tunisie", "Pantelleria", "Djerba", "Lampedusa", "Bahamas", 2, "geographie"));
            quizDao.addQuiz(new Quiz("Combien de gouvernorats y a-t-il dans la Tunisie", "22", "23", "20", "24", 4, "geographie"));
            quizDao.addQuiz(new Quiz("Quelle est la hauteur de la montagne El chaanbi", "1 640 m", "1 801 m", "1 544 m", "1 944 m", 3, "geographie"));
            quizDao.addQuiz(new Quiz("Quelle est la capitale de fait de la Suisse ?", " Bâle", " Berne", " Zurich", " Lausanne",  2, "geographie"));
            quizDao.addQuiz(new Quiz("Dans quel pays peut-on trouver la ville de Tcheliabinsk ?", "En Ukraine ", "En Russie ", "En Pologne", "En Biélorussie ", 2, "geographie"));
            quizDao.addQuiz(new Quiz("Où se situe le parc naturel du Perche ? ", "En Wallonie ", "En Normandie", " En Bretagne ", "En Irlande", 2, "geographie"));
            quizDao.addQuiz(new Quiz("Quel est le plus grand lac d’Amérique du Nord ? ", "Le lac Ontario", " Le lac Huron", "Le lac Majeur ", "Le lac Supérieur ",4, "geographie"));
            quizDao.addQuiz(new Quiz("Quelle ville compte la plus haute densité de population au monde ?", " Delhi ", "Le Pré-Saint-Gervais", " Le Caire", " Manille", 4, "geographie"));

            //histoire
            quizDao.addQuiz(new Quiz("En quelle année a commencé la guerre civile d'Espagne ", "1856", "1896", "1906", "1936", 4, "histoire"));
            quizDao.addQuiz(new Quiz("Dans quel pays, la Révolution orange eut-elle lieu en 2004 ", "Ukraine", "Pologne", "Grèce", "Turquie", 1, "histoire"));
            quizDao.addQuiz(new Quiz("Qui fut destitué par Fidel Castro en 1959", "Marcos Pérez Jiménez ", "Rómulo Betancourt", "Salvador Allende", "Fulgencio Batista", 4, "histoire"));
            quizDao.addQuiz(new Quiz("Quel est le premier président de la Tunisie", "Zine el-Abidine Ben Ali", "Hédi Nouira", "Mahmoud El Materi", "Habib Bourguiba", 4, "histoire"));
            quizDao.addQuiz(new Quiz("En quelle année fut fondée la grande mosquée de Kairouan par Oqba Ibn Nafi ", "630", "640", "670", "690", 3, "histoire"));
            quizDao.addQuiz(new Quiz("Que représente la date du 12 novembre 1956 en tunisie", "Décét de Mohamed Lamine Bey", "Admission de la Tunisie à l'ONU", "Fondation de la Fédération Tunisienne de football", "ondation de la Ligue tunisienne des droits", 2, "histoire"));
            quizDao.addQuiz(new Quiz("Quelle est la dernière ville Tunisienne à être colonisée", "Sfax", "Kairouan", "La Goulette", "Sakiet Sidi Youssef", 1, "histoire"));
            quizDao.addQuiz(new Quiz("Quelle est la première population qui a occupé la Tunisie", "Phéniciens", "Moustériens", "Berbères", "Byzantins", 2, "histoire"));
            quizDao.addQuiz(new Quiz("Qui a gagné la bataille de Kasserine en février 1943", "Allemagne nazie", "États-Unis", "France", "Japon", 1, "histoire"));
            quizDao.addQuiz(new Quiz("Quelle est la capitale des Hafsides (1228-1574)", "Kairouan", "Sfax", "Bizerte", "Tunis", 4, "histoire"));
            quizDao.addQuiz(new Quiz("onse histoire ", "1-premiére reponse", "2-deuxiéme", "3-reponse", 1, "histoire"));
            quizDao.addQuiz(new Quiz("duese histoire ", "1-premiére reponse", "2-deuxiéme", "3-reponse", 1, "histoire"));
            quizDao.addQuiz(new Quiz("triese histoire ", "1-premiére reponse", "2-deuxiéme", "3-reponse", 1, "histoire"));
            quizDao.addQuiz(new Quiz("quatorse histoire ", "1-premiére reponse", "2-deuxiéme", "3-reponse", 1, "histoire"));
            quizDao.addQuiz(new Quiz("qinze histoire ", "1-premiére reponse", "2-deuxiéme", "3-reponse", 1, "histoire"));



            quizDao.addQuiz(new Quiz("Quel célèbre dictateur dirigea l’URSS du milieu des années 1920 à 1953 ? ", " Staline ", "Molotov ", "Trotski", " Lénine ", 1, "culture"));
            quizDao.addQuiz(new Quiz("Dans quel pays peut-on trouver la Catalogne, l’Andalousie et la Castille ?", " La France", " La Portugal", " L'Italie ", "L'Espagne", 4, "culture"));
            quizDao.addQuiz(new Quiz("Qui a dit : « Le sort en est jeté » (Alea jacta est) ? ", "Vercingétorix ", "Attila", " Auguste", " César", 4, "culture"));
            quizDao.addQuiz(new Quiz("qui doit-on la chanson « I Shot the Sheriff » ?", " Bob Marley ", "Jim Morrison", " Eric Clapton", " UB40", 1, "culture"));
            quizDao.addQuiz(new Quiz("Quel pays a remporté la coupe du monde de football en 2014 ?", " L'Allemagne", " L'Italie", " Le Brésil", " L'Argentine",  1, "culture"));
            quizDao.addQuiz(new Quiz("Dans quelle ville italienne l’action de la pièce de Shakespeare « Roméo et Juliette » se situe-t-elle ? ", "Venise ", "Rome", " Milan", " Vérone ", 4, "culture"));
            quizDao.addQuiz(new Quiz("Par quel mot désigne-t-on une belle-mère cruelle ? ", "Une godiche ", "Une jocrisse", " Une marâtre", " Une chenapan",  3, "culture"));
            quizDao.addQuiz(new Quiz("Qui était le dieu de la guerre dans la mythologie grecque ?", " Arès ", "Hadès ", "Apollon ", "Hermès",  1, "culture"));
            quizDao.addQuiz(new Quiz("De quel courant philosophique Plotin est-il le grand représentant ?", " Le néoplatonisme ", "Le scepticisme", " Le stoïcisme ", "L'aristotélisme",  1, "culture"));
            quizDao.addQuiz(new Quiz("Qui a réalisé le film « In the mood for love » ? ", "Scorsese ", "Wong Kar-Wai", " Zhang Yimou Chan", " Feng Zhao", 2, "culture"));
            quizDao.addQuiz(new Quiz("De l’œuvre de quel écrivain est tirée la célèbre question « Que sais-je ? » ", "Montaigne", " Voltaire", " Étienne de la Boétie ", "Diderot",  1, "culture"));
            quizDao.addQuiz(new Quiz("Quelle race d’animal est un briard ? ", "Un chat", " Un chien", " Un canard", " Un cheval", 2, "culture"));
            quizDao.addQuiz(new Quiz("Où est né Mozart ? ", "Salzbourg ", "Turin ", "Vienne ", "Venise",  1, "culture"));
            quizDao.addQuiz(new Quiz("Quelle théorie doit-on à Isaac Newton ? ", "La théorie des cordes", " La théorie de la gravitation universelle", " La théorie atomique", " La théorie de l'évolution des espèces",  2, "culture"));
            quizDao.addQuiz(new Quiz("Parmi les hommes politiques suivants, lequel a succédé à Hugo Chavez en tant que Président du Venezuela ?", " Nicolas Maduro ", "Evo Morales ", "Rafael Correa", "Lula",  1, "culture"));
            quizDao.addQuiz(new Quiz("Que signifie « procrastiner » ? ", "Parler dans un langage particulièrement vulgaire ", "Contredire systématiquement son interlocuteur", " Remettre à plus tard quelque chose ", "Étudier beaucoup en vue d'un examen", 3, "culture"));


            return null;
        }
    }


}
