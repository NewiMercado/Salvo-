var gpId = paramObj(window.location.search).gp;

fetch("http://localhost:8080/api/game_view/" + gpId)
.then(response => response.json())
.then(data => {
   app.players = getPlayer(data.gamePlayer, gpId)
   showShips(data.ships)
   showSalvoes(data.salvoes)
})
.catch(error => console.error(error));

function paramObj(search) {
  var obj = {};
  var reg = /(?:[?&]([^?&#=]+)(?:=([^&#]*))?)(?:#.*)?/g;

  search.replace(reg, function(match, param, val) {
    obj[decodeURIComponent(param)] = val === undefined ? "" : decodeURIComponent(val);
  });

  return obj;
}

function showShips(ships) {
    ships.forEach(function(ship) {
        ship.location.forEach(function(loc) {
            var div = document.getElementById("S" + loc);
            div.className += " loc";
        })
    })
}

function showSalvoes(salvoes) {
    salvoes.forEach(function(salvo) {
        salvo.salvoes.forEach(function(loc) {
            var div = document.getElementById(loc);
            div.className += " shot";
        })
    })
}

function getPlayer(gamePlayers, id) {
    var obj = {
        player: null,
        opponent: null
        };
        gamePlayers.forEach (function(gamePlayer) {
            if(id == gamePlayer.id) {
                obj.player = gamePlayer.player;
            }   else    {
                obj.opponent = gamePlayer.player;
            }
        })
    return obj;
}


var app = new Vue({
    el: "#app",
    data: {
        players: {
            player: {
                id: "",
                email: "",
            },
            opponent: null,
        }
    }
});
