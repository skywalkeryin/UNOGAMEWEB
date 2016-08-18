$(function () {

    $("#create").on("singletap", function () {
        var promise = $.getJSON("api/game/start1");
        promise.done(function (result) {
            var gid = result.gameID.toString();
            var gname = result.gameName.toString();
            $("#gid").empty();
            $("#gname").empty();
            // $("#gname")[0].value=gname.text();
            //  $("#gid")[0].value=gid.text();
            $("#gid").val(gid);
            $("#gname").val(gname);

        });

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
            $("#player").prepend($("<div>").text(msg.aplayer));
        };
        $.UIGoToArticle("#startgame");
    });

    $("#backBtn").on("singletap", function () {
        $.UIGoBack();
    });


});


