  $(function () {
      
       var promise = $.getJSON("api/game/start");
        promise.done(function(result) {
            var idl=$("<td>").text("Game ID:      ");
            var id=$("<td>").text(result.gameID);
            var trId=$("<tr>").append(idl).append(id);
            var titlel=$("<td>").text("Title:      ");
            var title=$("<td>").text(result.gameName);
            var trtitle=$("<tr>").append(titlel).append(title);
           
             $("#title").empty();
             $("#title").append(trtitle).append(trId);
         });
       var promise = $.getJSON("api/game/startgame");
        promise.done(function(result){
            var image=$('<img src="Images/'+result.cardImg+'">');
//            var image=$("<h4>").text("asd");
            $("#imagetr").empty().append(image);
        });
       var promiseplayers=$.getJSON("api/player/playerlistingame");
     
       promiseplayers.done( function(players){
           for(i=0;i<players.length;i++){
               var name=$("<td>").text(players.name);
               var tr=$("<tr>").append(name);
               $("#players").append(tr);
           }
           
       });
       
  });


