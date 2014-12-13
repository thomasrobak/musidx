var miEs = {};

miEs.init = function() {
        miEs.client = new elasticsearch.Client({
        host:  'http://cw7pf5e77znlewiz.onion:9200',
        keepAlive: false,
        log: 'trace',
        method: 'GET',
    });

    miEs.ping = function() {
        miEs.client.ping({
            requestTimeout: 5000,
            hello: 'elasticsearch'
        }, function(e){
            if(e) {
                console.error(JSON.stringify(e));
                $('#status').html('ping failed');
            } else {
                $('#status').html('ping succeded');
            }
        });
    }

    miEs.handleResult = function(e, resp) {
        $('#status').html('got response');
        console.trace(e);
        console.trace(resp);
    }

    miEs.doSearch = function(e) {
        $('#status').html('starting search');
        var query = 'paranoid black';

        miEs.client.search({
            index: 'media',
            method: 'GET',
            count: 10,
            fields: ['Artist','Title'],
    //        body: {
    //            filtered: {
    //                query: {
    //                    bool: {
    //                        should: [
    //                            {match: {'Title': query}},
    //                            match: {Title: query}
    //                            {match: {'Artist': query}},
    //                            {match: {'SourceFile': query}},
    //                        ]
    //                    }
    //                }
    //            }
    //        }
        },miEs.handleResult);
        $('#status').html('awaiting response');
    }


    $('#btn_search').click(miEs.doSearch);
    $('#status').html('loaded');
    miEs.ping();
}
