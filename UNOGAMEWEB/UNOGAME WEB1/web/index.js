$(function() {

	var gamesTemplate = Handlebars.compile($("#gamesTemplate").html());
        
	$("#reloadBtn").on("singletap", function() {
	var promise = $.getJSON("api/game");
	promise.done(function(result) {
			$("#all-games").append(gamesTemplate({games: result}));

        });
        });
        
        
	$("#backBtn").on("singletap", function() {
		$.UIGoBack();
	});
        
        
        
	//Event delegation
	$("#all-games").on("singletap", "li", function() {
		       var gid =$(this).find("h4").text();
                      var gname=$(this).find("h3").text();
                      var h1=$("<h3>").text(gname);
                      var h2=$("<h4>").text(gid);
                    
                     var li=$("<li>").append(h1)
                             .append(h2);
                     $("#one-play").empty();
                     $("#one-play").append(li);
		     $.UIGoToArticle("#players");
			
	});

	$("#reloadBtn").trigger("singletap");


    });
	




