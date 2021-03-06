/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.robak.musicservice.config;

import org.springframework.context.annotation.*;
import org.springframework.beans.factory.annotation.Value;

/**
 *
 * @author sensei
 */
@Configuration
@PropertySource("file:///etc/musidx/musidx.properties")
public class Settings {

    @Value("${host:localhost}")         String host;
    @Value("${port:9300}")              String port;
    @Value("${cluster:elasticsearch}")  String cluster;
//    @Value("$(fields:Title^4,Album^3,Artist^3,Genre^2,SourceFile") String fields;
    @Value("$(fields:Title,Album,Artist,Genre,SourceFile,file") String fields;
    
    public String getHost() {
        return host;
    }
    
    public Integer getPort() {
        return new Integer(port);
    }

    public String[] getSearchFields() {
	return fields.split(",");
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getCluster() {
        return cluster;
    }

    public void setCluster(String cluster) {
        this.cluster = cluster;
    }

    public String getFields() {
        return fields;
    }

    public void setFields(String fields) {
        this.fields = fields;
    }
    
    
}
