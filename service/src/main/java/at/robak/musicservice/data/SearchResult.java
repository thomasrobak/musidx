/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package at.robak.musicservice.data;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author sensei
 */
public class SearchResult {

    private Long        hits    = 0l;
    private List<Song>  songs   = new LinkedList<Song>();

    /**
     * @return the songs
     */
    public List<Song> getSongs() {
        return songs;
    }

    /**
     * @param songs the songs to set
     */
    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    /**
     * @return the hits
     */
    public Long getHits() {
        return hits;
    }

    /**
     * @param hits the hits to set
     */
    public void setHits(Long hits) {
        this.hits = hits;
    }
    
    
}
