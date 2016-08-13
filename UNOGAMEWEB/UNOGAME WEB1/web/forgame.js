$(function () {

  $("#create").on("singletap", function () {
     var promise =$.getJSON("api/game/start1");
     promise.done(function(result){
        var gid=result.gameID.toString();
         var gname=result.gameName.toString();
          $("#gid").empty();
           $("#gname").empty();
          // $("#gname")[0].value=gname.text();
        //  $("#gid")[0].value=gid.text();
          $("#gid").val(gid);
          $("#gname").val(gname);
         
     });
       $.UIGoToArticle("#startgame");  
 });
    
    $("#backBtn").on("singletap", function () {        
        $.UIGoBack();
    });
    
    
});


