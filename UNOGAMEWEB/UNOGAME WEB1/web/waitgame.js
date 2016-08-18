$(function () {
    var connection = null;

    var promise = $.getJSON("api/player/playerid");
    promise.done(function (result) {
        var pid = result.pid.toString();
        var pname = result.name.toString();
        $("#pid1").empty();
        $("#pname1").empty();
        $("#pname1").val(pname);
        $("#pid1").val(pid);
        $("#pid").empty();
        $("#pname").empty();
        $("#pname").val(pname);
        $("#pid").val(pid);

    });


    var gamesTemplate = Handlebars.compile($("#gamesTemplate").html());
    $("#start").on("singletap", function () {
        var fixedpid = $("#pname").value;
        var card = $.getJSON("api/player/playerhands/" + fixedpid);
        card.done(function (result) {
            for (var i = 0; i < result.length; i++) {
                var x = result[i];
                var image = $('<img src="Images/' + x.cardImg + '">');
                var li = $("<li>").append(image);
                $("#Imagetr").append(li);
            }
            connection = new WebSocket("ws://localhost:8080/UNOGAME_WEB1/chat/" + $("#pid").val());
            connection.onopen = function () {
                console.info("Websocket is connected");
            };
            connection.onclose = function () {
                console.info("Websocket is closed");

            };
            connection.onmessage = function (msg) {
                console.info("incoming: %s", msg.data);
                var msg1 = JSON.parse(msg.data);
                if (msg1.ContentType === "discardcard") {
                    var image = $('<img src="' + msg1.discradcardurl + '">');
                    $("#testsendimg").append(image);
                } else {
                   var image = $('<img src="' + msg1.dealcardurl + '">');
                   var li = $("<li>").append(image);
                   $("#Imagetr").append(li);
                }
            };


        });
        $.UIGoToArticle("#players");
    });

    $("#Imagetr").on("singletap", "li", function () {
        var message = {
            ContentType: "discardcard",
            discradcardurl: $(this).find("img").attr("src")

        }
        connection.send(JSON.stringify(message));
        $(this).remove();
    });


    $("#backBtn").on("singletap", function () {

        $.UIGoBack();
    });



});










