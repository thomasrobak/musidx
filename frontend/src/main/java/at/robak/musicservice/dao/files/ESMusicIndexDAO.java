/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package at.robak.musicservice.dao.files;

import at.robak.musicservice.dao.clients.ESClient;
import at.robak.musicservice.data.SearchResult;
import at.robak.musicservice.data.Song;
import at.robak.musicservice.parser.SearchResponseParser;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.node.NodeBuilder;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author sensei
 */
public class ESMusicIndexDAO implements MusicIndexDAO{

    @Autowired
    private ESClient esClient;
    
    
    @Override
    public SearchResult search(String term, Integer offset, Integer count) {
        SearchRequestBuilder req = esClient.get().prepareSearch("media");
        req.setSearchType(SearchType.QUERY_AND_FETCH)
           .setQuery(buildQuery(term));
        
        SearchResponse resp = req.execute().actionGet();
        
        SearchResult result = SearchResponseParser.parseResponse(resp);
        
        return result;
    }

    private QueryBuilder buildQuery(String term) {
        return QueryBuilders.boolQuery()
                .should(QueryBuilders.termQuery("Title", term))
                .should(QueryBuilders.termQuery("Artist", term))
                .should(QueryBuilders.termQuery("SourceFile", term))
                .should(QueryBuilders.termQuery("FileName", term))
                .should(QueryBuilders.termQuery("Genre", term));
    }
    
    public Song get(String id) {
        GetResponse resp = esClient.get().prepareGet("media", "music", id).setOperationThreaded(false).execute().actionGet();
        
        return SearchResponseParser.parseResponse(resp);
    }
    
}
