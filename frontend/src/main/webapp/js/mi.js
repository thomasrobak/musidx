var mi = {};

mi.songs = [];

mi.base = "/frontend/rest/files/";

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
                    + '</p></div><button class="btn btn-primary" type="button" id="btn_play'
                    + id
                    + '" onclick=mi.play("'
                    + song.id
                    + '")>Play</button></div></div>';
            
    return result;
}

mi.parseData = function(data) {
    $('#rawdata').html(JSON.stringify(data));
    mi.log("Hits: " + data.hits);
    var result = "";
    
    /*
     <div class="panel panel-primary song_container">
                    <div class="panel-heading">
                        <label class="artist">Black Sabbath</label><label class="song">Paranoid</label>
                    </div>
                    <div class="panel-body">
                        <div class="info_block">
                            <p class="info">Hard Rock</p>
                            <p class="info">1972</p>
                        </div>
                        <div class="info_block">
                            <p class="info">160 kbps</p>
                            <p class="info">Paranoid</p>
                        </div>
                        <button class="btn btn-primary" type="button" id="btn_search">Play</button>
                    </div>
                </div>
     */
    var id = 0;
    data.songs.forEach(function(d){
       result += mi.parseSong(id++, d);
    });
    
    $('.result').html(result);
}

mi.search = function() {
    mi.log('searching...');
    
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