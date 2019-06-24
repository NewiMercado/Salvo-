package com.mindhub.salvo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private double score;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "game_id")
    private Game game;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "player_id")
    private Player player;

    public Score() {}

    public Score(Game game, Player player, double score) {
        this.game = game;
        this.player = player;
        this.score = score;
    }

    // getters and setters

    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public Player getPlayer() { return player; }

    public void setPlayer(Player player) { this.player = player; }

    public Game getGame() { return game; }

    public void setGame(Game game) { this.game = game; }

    public double getScore() { return score; }

    public void setScore(double scores) { this.score = scores; }

    // DTO

    public double toScore(){
        return score;
    }


}
