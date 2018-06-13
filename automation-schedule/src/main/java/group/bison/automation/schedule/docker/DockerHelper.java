package group.bison.automation.schedule.docker;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DockerClientBuilder;

/**
 * Created by BSONG on 2018/6/13.
 */
public class DockerHelper {

    private static DockerClient dockerClient = DockerClientBuilder.getInstance().build();
}
