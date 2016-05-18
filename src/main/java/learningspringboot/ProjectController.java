package learningspringboot;

import com.fasterxml.jackson.databind.JsonNode;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by tm1c14 on 18/05/2016.
 */
@RestController
@RequestMapping(value = "/project")
public class ProjectController {

    private ProjectService projectService;
    private ResourceLoader resourceLoader;
    private GeolocationService geolocationService;

    @Autowired
    public ProjectController(ProjectService projectService, ResourceLoader resourceLoader, GeolocationService geolocationService) {
        this.projectService = projectService;
        this.resourceLoader = resourceLoader;
        this.geolocationService = geolocationService;

    }

    @RequestMapping(value = "group", method={RequestMethod.GET}, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Project[]> getProjectGroups()  {
        ResponseEntity<Project[]> responseEntity = projectService.getAllProjects();
        return responseEntity;
    }

    @RequestMapping(value = "receipt", method = { RequestMethod.GET})
    public ResponseEntity<InputStreamResource> getReceipt() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:learningspringboot/Thando_receipt.jpg");

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.IMAGE_JPEG);

        ResponseEntity<InputStreamResource> responseEntity = null;
        try {
            responseEntity = new ResponseEntity<InputStreamResource>(
                    new InputStreamResource(resource.getInputStream()), httpHeaders, HttpStatus.OK);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }

        return responseEntity;
    }

    @RequestMapping(value = "country", method = RequestMethod.GET, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<JsonNode> getCountry(@RequestParam(defaultValue = "128.101.101.101", value = "address") String address) {
        return geolocationService.getCountryResponseEntity(address);
    }
}
