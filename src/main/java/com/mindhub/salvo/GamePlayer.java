package com.mindhub.salvo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

@Entity
public class GamePlayer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "player_id")
    private Player player;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "game_id")
    private Game game;

    @OneToMany(mappedBy = "gameplayer", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    Set<Ship> ships = new HashSet<>();

    @OneToMany(mappedBy = "gameplayer", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    Set<Salvo> salvoes = new HashSet<>();

    public GamePlayer() { }

    public GamePlayer(Game game, Player player, Set<Ship> ships, Set<Salvo> salvoes) {
        this.game = game;
        this.player = player;
        this.addShips(ships);
        this.addSalvo(salvoes);
    }

    // getters and setters

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @JsonIgnore
    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void addShip(Ship ship) {
        ship.setGameplayer(this);
        ships.add(ship);
    }

    public void addShips(Set<Ship> ships){
        ships.forEach(this::addShip);
    }

    public Set<Salvo> getSalvo() {
        return salvoes;
    }

    public void addSalvo(Salvo salvo) {
        salvo.setGamePlayer(this);
        salvoes.add(salvo);
    }

    public void addSalvo(Set<Salvo> salvo){
        salvo.forEach(this::addSalvo);
    }

    public Score getScore() {
        return player.getScore(game);
    }

    // DTO

    public Map<String, Object> toDTO() {
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("id", this.id);
        dto.put("player", this.player.toDTO());
        if (this.getScore() != null)
            dto.put("score", this.getScore().toScore());
        else
            dto.put("score", null);
        return dto;
    }

    public Map<String, Object> toGameView() {
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("id", this.id);
        dto.put("created", game.getCreationDate());
        dto.put("gamePlayer", this.game.getGameplayers().stream().map(GamePlayer::toDTO));
        dto.put("ships", this.ships.stream().map(Ship::shipsDTO).collect(toList()));
        dto.put("salvoes", this.game.getGameplayers().stream().flatMap(gamePlayer -> gamePlayer.getSalvo().stream().map(Salvo::salvoesDTO)));
        return dto;
    }

}



