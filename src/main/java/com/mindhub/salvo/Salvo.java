package com.mindhub.salvo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Entity
public class Salvo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private int turn;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="gameplayer_id")
    private GamePlayer gameplayer;

    @ElementCollection
    @Column(name = "salvoLocations")
    private List<String> salvoLocations = new ArrayList<>();

    public Salvo() { }

    public Salvo(int turn, List<String> salvoLocations){
        this.turn = turn;
        this.salvoLocations = salvoLocations;
    }

    // getters and setters

    public List<String> getSalvoLocations() {
        return salvoLocations;
    }

    public void setGamePlayer(GamePlayer gameplayer) {
        this.gameplayer = gameplayer;
    }

    public int getTurn() {
        return turn;
    }

    // DTO

    public Map<String, Object> salvoesDTO (){
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("turn", this.getTurn());
        dto.put("salvoes", this.getSalvoLocations());
        dto.put("player", this.gameplayer.getPlayer().toDTO());
        return dto;
    }

}
