$.getJSON( "/api/games", function(data) {
  app.games = data;
})

$.post("/api/login", { username: "j.bauer@ctu.gov", password: "123" }).done(function() { console.log("logged in!"); })

var app = new Vue ({
    el: "#games",
    data: {
        games: [],
    }
})

function login(evt) {
  evt.preventDefault();
  var form = evt.target.form;
  $.post("/app/login",
         { name: form["email"].value,
           pwd: form["password"].value })
   .done(...)
   .fail(...);
}

function logout(evt) {
  evt.preventDefault();
  $.post("/app/logout")
   .done(...)
   .fail(...);
}