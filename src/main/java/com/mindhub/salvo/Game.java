package com.mindhub.salvo;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import static java.util.stream.Collectors.toList;

@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private LocalDateTime creationDate;

    @OneToMany(mappedBy = "game", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<GamePlayer> gameplayers;

    @OneToMany(mappedBy = "gameplayer", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Ship> ships;

    @OneToMany(mappedBy = "gameplayer", fetch = FetchType.EAGER, cascade =  CascadeType.ALL)
    private Set<Salvo> salvoes;

    public Game() { }

    public Game(LocalDateTime creationDate) { this.creationDate = creationDate; }

    public void addGamePlayer(long id, GamePlayer gameplayer) {
        this.id = id;
        gameplayer.setGame(this);
        gameplayers.add(gameplayer);
    }

    public long getId() {
        return id;
    }
// getters and setters

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public Set<GamePlayer> getGameplayers() {
        return gameplayers;
    }

    public Set<Salvo> getSalvoes() { return salvoes; }

    // DTO

    public Map<String, Object> toDTO() {
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("id", this.id);
        dto.put("created", this.creationDate);
        dto.put("gamePlayer", this.gameplayers.stream().map(GamePlayer::toDTO).collect(toList()));
        return dto;
    }
}