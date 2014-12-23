var mi = {};

mi.searchstart = 0;

mi.songs = [];

mi.base = "/musidx/rest/files/";

mi.log = function(msg) {
    $('#info').html(msg);
}

mi.play = function(song) {
    $.post(mi.base + "play/?id=" + song);
}

mi.parseSong = function(id,song) {
    
    var result = '<div class=\"panel panel-primary song_container"><div class="panel-heading"><label class="artist">'
                    + song.artist
                    + '</label><label class="song">'
                    + song.title
                    + '</label></div><div class="panel-body"><div class="info_block"><p class="info">'
                    + song.genre    
                    + '</p><p class="info">'
                    + song.year
                    + '</p></div><div class="info_block"><p class="info">'
                    + song.bitrate
                    + '</p><p class="info">'
                    + song.album
                    + '</p></div><div class="info_block_r"><p class="info">'
                    + song.file
                    + '</p><p class="info">'
		    + song.score	
                    + '</p></div></div></div>';
            
    return result;
}

mi.parseData = function(data) {
    var searchend = new Date().getTime() - mi.searchstart;
    $('#rawdata').html(JSON.stringify(data));
    var result = "";
    var id = 0;

    data.songs.forEach(function(d){
       result += mi.parseSong(id++, d);
    });
    
    $('.result').html(result);
    var renderend = new Date().getTime() - searchend;
    mi.log("Found " + data.hits + " hits,  search took " + searchend + "ms, rendering took " + renderend + "ms");
    
}

mi.search = function() {
    mi.searchstart = new Date().getTime();
    mi.log('searching...');
    $('.result').html('<div class="progress"><div class="progress-bar progress-bar-info progress-bar-striped active" role="progressbar" aria-valuenow="20" style="width: 100%"/></div>');
    
    var term = $('#txt_term').val();
    $.get(mi.base + "search/?term=" + term,mi.parseData);
    $('#txt_term').select();
}

mi.init = function() {
    $('#formsearch').submit(mi.search);
    $('#btn_search').click(mi.search);
    $('#txt_term').select();
    mi.log('Happy Searching!');
}

$(document).ready(mi.init);
