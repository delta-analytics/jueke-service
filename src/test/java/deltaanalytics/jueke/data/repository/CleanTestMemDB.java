package deltaanalytics.jueke.data.repository;

public class CleanTestMemDB {
    public static void cleanUp(){
        new JuekeStatusRepository().deleteAll();
    }
}
