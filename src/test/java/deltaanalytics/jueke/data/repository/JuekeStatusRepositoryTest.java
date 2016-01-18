package deltaanalytics.jueke.data.repository;

import deltaanalytics.jueke.data.entity.JuekeStatus;
import org.junit.After;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class JuekeStatusRepositoryTest {

    @After
    public void tearDown() {
        CleanTestMemDB.cleanUp();
    }

    @Test
    public void create() {
        JuekeStatus juekeStatus = new JuekeStatus();
        juekeStatus.setErrorCode(1);

        JuekeStatusRepository juekeStatusRepository = new JuekeStatusRepository();

        juekeStatusRepository.createOrUpdate(juekeStatus);

        assertThat(juekeStatusRepository.exists(juekeStatus), is(equalTo(true)));
    }

    @Test
    public void update() {
        JuekeStatus juekeStatus = new JuekeStatus();

        JuekeStatusRepository juekeStatusRepository = new JuekeStatusRepository();

        juekeStatusRepository.createOrUpdate(juekeStatus);

        juekeStatus.setErrorCode(111);
        juekeStatusRepository.createOrUpdate(juekeStatus);

        assertThat(juekeStatusRepository.read(juekeStatus.getId()).getErrorCode(), is(equalTo(111)));
        assertThat(juekeStatusRepository.read(juekeStatus.getId()).getStatusDateTime(), is(not(nullValue())));
    }

    @Test
    public void findAllBetweenDates() {
        JuekeStatusRepository juekeStatusRepository = new JuekeStatusRepository();
        JuekeStatus juekeStatus = new JuekeStatus();
        LocalDateTime localDateTimeNow = LocalDateTime.now();
        LocalDateTime localDateTimeOneMonthAgo = localDateTimeNow.minusMonths(1);
        juekeStatus.setStatusDateTime(localDateTimeNow);
        juekeStatusRepository.createOrUpdate(juekeStatus);

        assertThat(juekeStatusRepository.findAllBetweenDates(localDateTimeOneMonthAgo, localDateTimeNow.plusDays(2)).size(), is(equalTo(1)));
    }
}
