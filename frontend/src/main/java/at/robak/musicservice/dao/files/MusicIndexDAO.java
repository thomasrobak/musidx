/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package at.robak.musicservice.dao.files;

import at.robak.musicservice.data.SearchResult;
import at.robak.musicservice.data.Song;


/**
 *
 * @author sensei
 */
public interface MusicIndexDAO {
    SearchResult search(String term, Integer offset, Integer count);
    Song get(String id);
}
