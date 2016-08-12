$(function(){
   var promise=$.getJSON("api/player/playerid");

     promise.done(function(result){
        var pid=result.pid.toString();
         var pname=result.name.toString();
          $("#pid").empty();
          $("#pname").empty();
          $("#pid").val(pid);
          $("#pname").val(pname);
     
     var card=$.getJSON("api/player/playerhands/"+pid);
     card.done(function(result){
     for(var i=0;i<result.length;i++){
           var x=result[i];
                var image=$('<img src="Images/'+x.cardImg+'">');
           
          $("#imagetr").append(image);
 
   // $("#imagetr").empty().append(image1);
//         var image=$("<h4>").text("asd");
            
        
 }
    
 });
});

// var pidinput=$("#pid").val();
//  var card=$.getJSON("api/player/playerhands/"+pid);
  
});

