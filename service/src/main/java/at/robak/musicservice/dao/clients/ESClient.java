package at.robak.musicservice.dao.clients;

import at.robak.musicservice.config.Settings;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
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
        org.elasticsearch.common.settings.Settings _settings = ImmutableSettings.settingsBuilder()
        .put("cluster.name", settings.getCluster()).build();

        client = new TransportClient(_settings)
//                    .addTransportAddress(new InetSocketTransportAddress("master1.clupi", 9300));
 		       .addTransportAddress(new InetSocketTransportAddress(settings.getHost(), settings.getPort()));
    }

    @PreDestroy
    public void cleanup() {
        client.close();
    }
    
    public Client get() {
        return client;
    }

    
}
