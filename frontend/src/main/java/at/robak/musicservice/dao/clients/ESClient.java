package at.robak.musicservice.dao.clients;

import at.robak.musicservice.config.Settings;
import java.util.Properties;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author sensei
 */
public class ESClient {

    @Autowired Settings settings;
    
    private Client client;
    
    @PostConstruct
    public void afterPropertiesSet() throws Exception {
        client = new TransportClient()
                    .addTransportAddress(new InetSocketTransportAddress("master1.clupi", settings.getPort()));
//        .addTransportAddress(new InetSocketTransportAddress(settings.getHost(), settings.getPort()));
    }

    @PreDestroy
    public void cleanup() {
        client.close();
    }
    
    public Client get() {
        return client;
    }

    
}
