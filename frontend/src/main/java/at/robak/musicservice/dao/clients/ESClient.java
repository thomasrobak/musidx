package at.robak.musicservice.dao.clients;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

/**
 *
 * @author sensei
 */
public class ESClient {

    private Client client;
    
    @PostConstruct
    public void afterPropertiesSet() throws Exception {
        client = new TransportClient()
                    .addTransportAddress(new InetSocketTransportAddress("master1.clupi", 9300));
    }

    @PreDestroy
    public void cleanup() {
        client.close();
    }
    
    public Client get() {
        return client;
    }

    
}
