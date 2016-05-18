package learningspringboot;

import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.maxmind.db.Reader;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by tm1c14 on 18/05/2016.
 */
@Service
public class GeolocationService {

    private static final Logger logger = LoggerFactory.getLogger(GeolocationService.class);

    private  Reader countryReader;

    @Autowired
    private ResourceLoader resourceLoader;


    public ResponseEntity<JsonNode> getCountryResponseEntity(String ipAddress){
        JsonNode jsonNode = null;
        if(ipAddress != null) {
            try {
                System.out.println(ipAddress + "---------------------------------------");
                InetAddress address = InetAddress.getByName(ipAddress);
                jsonNode = countryReader.get(address);
            }
            catch (UnknownHostException ex ) {
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        System.out.println(jsonNode + "---------------------------------------");
        return new ResponseEntity<JsonNode>(jsonNode, HttpStatus.OK);
    }

    @PostConstruct
    public void init() {
        try {
            logger.info("GeolocationService: Trying to load GeoLite2-Country database...");
            Resource resource = resourceLoader.getResource("classpath:learningspringboot/GeoLite2-Country.mmdb");
            File dbAsFile = resource.getFile();
//
            countryReader = new Reader(dbAsFile);
            logger.info("GeolocationService: Database was loaded successfully.");
        }
        catch(Exception ex) {
            logger.error("Database reader cound not be initialized. ", ex);
        }
    }

    @PreDestroy
    public void preDestroy() {
        if(countryReader != null) {
            try {
                countryReader.close();
            }
            catch(IOException ex){
                logger.error("Failed to close reader");
            }
        }
    }
}
