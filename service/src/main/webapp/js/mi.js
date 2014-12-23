var mi = {};

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
    $('#rawdata').html(JSON.stringify(data));
    mi.log("Hits: " + data.hits);
    var result = "";
    var id = 0;

    data.songs.forEach(function(d){
       result += mi.parseSong(id++, d);
    });
    
    $('.result').html(result);
}

mi.search = function() {
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
    mi.log('mi loaded');
}

$(document).ready(mi.init);
