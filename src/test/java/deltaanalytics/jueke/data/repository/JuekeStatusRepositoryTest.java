package deltaanalytics.jueke.data.repository;

import deltaanalytics.jueke.Application;
import deltaanalytics.jueke.data.entity.JuekeStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
@Transactional
public class JuekeStatusRepositoryTest {

    @Autowired
    private JuekeStatusRepository juekeStatusRepository;

    @Test
    public void create() {
        JuekeStatus juekeStatus = new JuekeStatus();
        juekeStatus.setErrorCode(1);

        juekeStatusRepository.save(juekeStatus);

        assertThat(juekeStatusRepository.exists(juekeStatus.getId()), is(true));
    }

    @Test
    public void update() {
        JuekeStatus juekeStatus = new JuekeStatus();

        juekeStatusRepository.save(juekeStatus);

        juekeStatus.setErrorCode(111);
        juekeStatusRepository.save(juekeStatus);

        JuekeStatus juekeStatusFromDb = juekeStatusRepository.findOne(juekeStatus.getId());
        assertThat(juekeStatusFromDb.getErrorCode(), is(equalTo(111)));
        assertThat(juekeStatusFromDb.getStatusDateTime(), is(not(nullValue())));
    }

    @Test
    public void findAllBetweenDates() {
        JuekeStatus juekeStatus = new JuekeStatus();
        LocalDateTime localDateTimeNow = LocalDateTime.now();
        LocalDateTime localDateTimeOneMonthAgo = localDateTimeNow.minusMonths(1);
        juekeStatus.setStatusDateTime(localDateTimeNow);
        juekeStatusRepository.save(juekeStatus);

        assertThat(juekeStatusRepository.findAllByStatusDateTimeBetween(localDateTimeOneMonthAgo, localDateTimeNow.plusDays(2)).size(), is(equalTo(1)));
    }
}
