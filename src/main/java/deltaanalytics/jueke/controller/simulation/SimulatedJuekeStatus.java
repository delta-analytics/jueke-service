package deltaanalytics.jueke.controller.simulation;

import deltaanalytics.jueke.data.entity.JuekeStatus;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("simulation")
@Component
public class SimulatedJuekeStatus extends JuekeStatus{
}
