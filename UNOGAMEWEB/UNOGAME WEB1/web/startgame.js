$(function () {


    var connection = null;

    var promise = $.getJSON("api/game/start");
    promise.done(function (result) {
        var idl = $("<td>").text("Game ID:      ");
        var id = $("<td>").text(result.gameID);
        var trId = $("<tr>").append(idl).append(id);
        var titlel = $("<td>").text("Title:      ");
        var title = $("<td>").text(result.gameName);
        var trtitle = $("<tr>").append(titlel).append(title);

        $("#title").empty();
        $("#title").append(trtitle).append(trId);
    });
    var promise = $.getJSON("api/game/startgame");
    promise.done(function (result) {
        var image = $('<img src="Images/' + result.cardImg + '">');
        //   var image1=$('<img src="Images/back.png">');
//            var image=$("<h4>").text("asd");
        $("#imagetr").empty().append(image);
        // $("#backimg").append(image1);
    });

    var promiseplayers = $.getJSON("api/player/playerlistingame");
    promiseplayers.done(function (players) {
        for (i = 0; i < players.length; i++) {
            var player = players[i];
            var nameid = player.pid.toString(); 
            var playername= $('<h5>').text(player.name);
            var name = $('<h4>',{id:nameid}).text(player.pid);
            
            $(document).ready(function () {
                $("body").append('<div id="' + nameid + '"class="players"/>');
            });
             $("#" + nameid).append(playername);
            $("#" + nameid).append(name);
            var accountimg = $('<img src="Images/account.png" width="150" height="100">');
            $("#" + nameid).append(accountimg);
            $(".players").droppable({
                accept: ".dragdeck",
                classes: {
                    "ui-droppable-active": "ui-state-active",
                    "ui-droppable-hover": "ui-state-hover"
                },
                drop: function (event, ui) {
                    console.info("test!!");
                    var dealcardurl = ui.draggable.find("img").attr("src");
                    //var image1 = $('<img src="' + dealcardurl + '">');
                    //$("#testsendimg").append(image1);//获取draggable元素的url
                     var message = {
                        ContentType: "deal",
                        dealcardurl: ui.draggable.find("img").attr("src")

                     };
                    connection = new WebSocket("ws://localhost:8080/UNOGAME_WEB1/chat/" +  $(this).find("h4").val());
                    connection.onopen = function () {
                        console.info("Websocket is connected");
                        connection.send(JSON.stringify(message));
                    };
                    connection.onclose = function () {
                        console.info("Websocket is closed");

                    };
                    connection.onmessage = function (msg) {
                        console.info("incoming: %s", msg.data);
                        // var msg = JSON.parse(msg.data);
                        // var image = $('<img src="' + msg.discradcardurl + '">');
                        // $("#testsendimg").append(image);
                    };
                
                

                    ui.draggable.remove();
                    $(this)
                            .addClass("ui-state-highlight")
                            .find("p")
                            .html("Dealt Card!");

                }
            });

        }
    });
    var deck = $.getJSON("api/game/deck");
    deck.done(function (cards) {
        // var cardimg=$('<img src="Images/'+cards.cardImg+'">');

        for (i = 0; i < cards.length; i++) {
            $(document).ready(function () {
                $("body").append('<div id="div' + i + '" class="dragdeck"/>');
            });
            var card = cards[i];
            var backimg = $('<img src="Images/' + card.cardImg + '">');
            $("#div" + i).append(backimg);

            $('.dragdeck').draggable({
                stack: ".dragdeck"
            });


        }
    });
    $('.dragpile').draggable({
        cursor: 'move'
    });
    $("#connectBtn").on("click", function () {
        connection = new WebSocket("ws://localhost:8080/UNOGAME_WEB1/chat/");
        connection.onopen = function () {
            console.info("Websocket is connected");
        };
        connection.onclose = function () {
            console.info("Websocket is closed");

        };
        connection.onmessage = function (msg) {
            console.info("incoming: %s", msg.data);
            var msg = JSON.parse(msg.data);
            var image = $('<img src="' + msg.discradcardurl + '">');
            $("#testsendimg").append(image);
        };



    });






});

