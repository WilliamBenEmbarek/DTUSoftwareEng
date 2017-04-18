import org.junit.Test;

/**
 * Created by Emil on 27/03/2017.
 */
public class ProjectLeaderTest extends SampleDataSetup{

	@Test
	public void ProjectLeaderTest() {
		ProjectLeader x = STM.getProjectLeaderByID("201701");
	}
}
