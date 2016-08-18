$(function () {

    var gamesTemplate = Handlebars.compile($("#gamesTemplate").html());

    $("#reloadBtn").on("singletap", function () {
        var promise = $.getJSON("api/game");
        promise.done(function (result) {
            $("#all-games").append(gamesTemplate({games: result}));

        });
    });


    $("#backBtn").on("singletap", function () {
        $.UIGoBack();
    });



    //Event delegation
    $("#all-games").on("singletap", "li", function () {
        connection = new WebSocket("ws://localhost:8080/UNOGAME_WEB1/player");
        connection.onopen = function () {
            console.info("Websocket is connected");
        };
        connection.onclose = function () {
            console.info("Websocket is closed");

        };
        connection.onmessage = function (msg) {
            console.info("incoming: %s", msg.data);
            var msg = JSON.parse(msg.data);
        };
        var gid = $(this).find("h4").text();
        var gname = $(this).find("h3").text();
        var h1 = $("<h3>").text(gname);
        var h2 = $("<h4>").text(gid);
        //  var txtbox = document.createElement('input'); 
        //   txtbox.type = "text"; 
        // var li=$("<li>").append(h1)
        //  .append(h2);
        $("#gid").empty();
        $("#gname").empty();
        $("#gname")[0].value = h1.text();
        $("#gid")[0].value = h2.text();
        $.UIGoToArticle("#players");

    });

    $("#reloadBtn").trigger("singletap");
 

    $("#send").on("click", function () {
        console.info("dddd");
        var message = {
            player: $("#pname").val()
        };
        connection.send(JSON.stringify(message));
    });



});





