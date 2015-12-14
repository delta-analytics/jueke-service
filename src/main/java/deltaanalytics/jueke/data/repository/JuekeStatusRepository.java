package deltaanalytics.jueke.data.repository;


import deltaanalytics.jueke.data.entity.JuekeStatus;

public class JuekeStatusRepository extends JuekeDataRepository<JuekeStatus> {
    @Override
    public boolean exists(JuekeStatus entity) {
        return read(entity.getId()) != null;
    }

    @Override
    protected Class<JuekeStatus> getEntityClass() {
        return JuekeStatus.class;
    }
}
