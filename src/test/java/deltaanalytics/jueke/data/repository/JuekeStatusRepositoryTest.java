package deltaanalytics.jueke.data.repository;

import deltaanalytics.jueke.Application;
import deltaanalytics.jueke.data.entity.JuekeStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
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
    public void findBetweenDatesTwoDatesOneMatch() throws InterruptedException {
        JuekeStatus juekeStatus = new JuekeStatus();
        JuekeStatus juekeStatus2 = new JuekeStatus();
        juekeStatus2.setActualTempHeater(99.99);

        juekeStatusRepository.saveAndFlush(juekeStatus);
        Thread.sleep(200);
        LocalDateTime localDateTimeAfterSleep = LocalDateTime.now();
        juekeStatusRepository.save(juekeStatus2);

        List<JuekeStatus> byStatusDateTimeBetween = juekeStatusRepository.findByStatusDateTimeBetween(localDateTimeAfterSleep, LocalDateTime.now().plusDays(2));
        assertThat(byStatusDateTimeBetween.size(), is(1));
        assertThat(byStatusDateTimeBetween.get(0), is(juekeStatus2));
    }

    @Test
    public void findBetweenDatesTwoDatesTwoMatches() throws InterruptedException {
        JuekeStatus juekeStatus = new JuekeStatus();
        JuekeStatus juekeStatus2 = new JuekeStatus();

        juekeStatusRepository.save(juekeStatus);
        juekeStatusRepository.save(juekeStatus2);

        assertThat(juekeStatusRepository.findByStatusDateTimeBetween(LocalDateTime.now().minusSeconds(1), LocalDateTime.now().plusDays(2)).size(), greaterThanOrEqualTo(2));
    }
}
