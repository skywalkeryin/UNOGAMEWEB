
$(function(){ 
    
    var promise = $.getJSON("api/player/playerid");
	promise.done(function(result) {
             var pid=result.pid.toString();
             var pname=result.name.toString();
		$("#pid1").empty();
                $("#pname1").empty();
                $("#pname1").val(pname);
                $("#pid1").val(pid);
                $("#pid").empty();
                $("#pname").empty();
                $("#pname").val(pname);
                $("#pid").val(pid);
              
        });
   
     $("#start").on("singletap", function () {  
                var fixedpid=$("#pid1").value;
                var card=$.getJSON("api/player/playerhands/"+"92e700a9");
                card.done(function(result){
                for(var i=0;i<result.length;i++){
                var x=result[i];
                var image=$('<img src="Images/'+x.cardImg+'">');
           
                      $("#imagetr").append(image);
                   }
    
                 });
	
         $.UIGoToArticle("#players"); 
    });
        
        
    $("#backBtn").on("singletap", function() {
              
		$.UIGoBack();
	});
         
       
		
                 
                 
          
               
        });
        
          
                  
            
                  
            
         
   



