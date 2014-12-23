/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.robak.musicservice;

import at.robak.musicservice.dao.files.MusicIndexDAO;
import at.robak.musicservice.data.SearchResult;
import at.robak.musicservice.data.Song;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author sensei
 */
@Component
@Path("/files")
public class FileService {

    private Logger log = Logger.getLogger(FileService.class.getName());
    
    @Autowired
    private MusicIndexDAO musicIndexDAO;
    
    @GET
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    public SearchResult search(@QueryParam("term") String term) {
        SearchResult result = null;
        try {
        result = musicIndexDAO.search(term, null, null);
        } catch(Throwable t) {
            log.log(Level.SEVERE, "Search failes for term " + term, t);
        }
        return result;
    }
    
    @POST
    @Path("/play")
    public void play(@QueryParam("id") String id) {
        Song s = musicIndexDAO.get(id);
        
        log.info(s.getFile());
    }
}
