$(function() {

	var gamesTemplate = Handlebars.compile($("#gamesTemplate").html());
	
	var promise = $.getJSON("api/game");
	promise.done(function(result) {
			$("#all-games").append(gamesTemplate({games: result}));

        });
    });
	




