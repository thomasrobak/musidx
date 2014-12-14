/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.robak.musicservice.config;

import java.util.Properties;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author sensei
 */
public class Settings {

    @Autowired Properties config;
    
    public String getHost() {
        return config.getProperty("host", "localhost");
    }
    
    public Integer getPort() {
        return new Integer(config.getProperty("port", "9300"));
    }
}
