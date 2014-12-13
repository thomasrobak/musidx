package at.robak.musicservice.dao.clients;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.elasticsearch.client.Client;
import org.elasticsearch.node.NodeBuilder;
import org.springframework.beans.factory.InitializingBean;

/**
 *
 * @author sensei
 */
public class ESClient {

    private Client client;
    
    @PostConstruct
    public void afterPropertiesSet() throws Exception {
        client = NodeBuilder.nodeBuilder().clusterName("clupi-dev").node().client();
    }

    @PreDestroy
    public void cleanup() {
        client.close();
    }
    
    public Client get() {
        return client;
    }

    
}
