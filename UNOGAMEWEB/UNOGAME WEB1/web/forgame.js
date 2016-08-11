$(function () {
    $("#create").on("singletap", function () {
        var promise = $.getJSON("api/game/start");
        promise.done(function(result) {
            var idl=$("<td>").text("Game ID:      ");
            var id=$("<td>").text(result.gameID);
            var trId=$("<tr>").append(idl).append(id);
            var titlel=$("<td>").text("Title:      ");
            var title=$("<td>").text(result.gameName);
            var trtitle=$("<tr>").append(titlel).append(title);
            var stal=$("<td>").text("Game Status:      ");
            var sta=$("<td>").text(result.gameStatus);
            var trsta=$("<tr>").append(stal).append(sta);
            $("#start").empty();
             $("#start").append(trtitle).append(trsta).append(trId);
         $.UIGoToArticle("#startgame");   
        });       
    });
    
    $("#backBtn").on("singletap", function () {        
        $.UIGoBack();
    });
});


