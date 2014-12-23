/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package at.robak.musicservice.parser;

import at.robak.musicservice.data.SearchResult;
import at.robak.musicservice.data.Song;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHitField;

/**
 *
 * @author sensei
 */
public class SearchResponseParser {

    private static class FieldParser {
        
        private Map<String, Object> fields;
        
        public FieldParser(Map<String, Object> fields) {
            this.fields = fields;
        }
        
        public String get(String key) {
            Object o = this.fields.get(key);
            
            return o == null ? "" : o.toString();
            
        }
    } 
    
    public static Song parseSource(Map<String, Object> source) {
        Song song = new Song();
        
        
        FieldParser p = new FieldParser(source);
        
        song.setArtist(p.get("Artist"));
        song.setTitle(p.get("Title"));
        song.setBitrate(p.get("AudioBitrate"));
        song.setAlbum(p.get("Album"));
        song.setGenre(p.get("Genre"));
        song.setYear(p.get("Year"));
        song.setFile(p.get("SourceFile"));
        
        return song;
    }
    
    
    public static SearchResult parseResponse(SearchResponse resp) {
        SearchResult result = new SearchResult();
        
        result.setHits(resp.getHits().totalHits());
        
        for(SearchHit hit : resp.getHits().getHits()) {
            Song s = parseSource(hit.getSource());
            s.setId(hit.getId());
	    s.setScore(hit.getScore());
            if(s != null) {
                result.getSongs().add(s);
            }
        }
        
        return result;
    }
    
    public static Song parseResponse(GetResponse resp) {
        return parseSource(resp.getSource());
    }
}
