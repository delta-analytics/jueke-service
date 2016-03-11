package deltaanalytics.jueke.data.repository;

import deltaanalytics.jueke.data.entity.JuekeStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface JuekeStatusRepository extends JpaRepository<JuekeStatus, Long> {
    List<JuekeStatus> findAllByStatusDateTimeBetween(LocalDateTime start, LocalDateTime end);
}
