package learningspringboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Created by tm1c14 on 18/05/2016.
 */
@Service
public class ProjectService {



    @Value("${private_token}")
    private String private_token;

    @Value("${gitlab.url}")
    private String gitLabUrl;


    public ResponseEntity<Project[]> getAllProjects() {
        RestTemplate restTemplate = new RestTemplate();
        String groupsUrl = gitLabUrl + "/groups";
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(groupsUrl);
        builder.queryParam("private_token", private_token);

        ResponseEntity<Project[]> responseEntity = restTemplate.getForEntity(builder.build().encode().toUri(), Project[].class);
        return responseEntity;

    }
}
