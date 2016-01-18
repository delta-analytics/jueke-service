package deltaanalytics.jueke.data.repository;


import deltaanalytics.jueke.data.entity.JuekeStatus;

import javax.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class JuekeStatusRepository extends JuekeDataRepository<JuekeStatus> {
    @Override
    public boolean exists(JuekeStatus entity) {
        return read(entity.getId()) != null;
    }

    @Override
    protected Class<JuekeStatus> getEntityClass() {
        return JuekeStatus.class;
    }

    public List<JuekeStatus> findAllBetweenDates(LocalDateTime start, LocalDateTime end) {
        List<JuekeStatus> juekeStatuses;
        startDBOperation();
        TypedQuery<JuekeStatus> query = getEntityManager().createQuery("SELECT j FROM JuekeStatus j WHERE j.statusDateTime BETWEEN :start AND :end", JuekeStatus.class);
        query.setParameter("start", start);
        query.setParameter("end", end);
        juekeStatuses = query.getResultList();
        endDBOperation();
        return juekeStatuses;
    }
}
