package com.mindhub.salvo;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    @OneToMany(mappedBy = "player", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    Set<GamePlayer> gameplayers;

    @OneToMany(mappedBy = "player", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    Set<Score> scores;

    public Player() { }

    public Player(String firstName, String lastName, String email, String encode) { }

    public Player(long id, String first, String last, String email, String password) {
        this.id = id;
        this.firstName = first;
        this.lastName = last;
        this.email = email;
        this.password = password;
    }

    // getters and setters

    public void addGameplayer(GamePlayer gameplayer) {
        gameplayer.setPlayer(this);
        gameplayers.add(gameplayer);
    }

    public Set<Score> getScores() { return scores; }

    public void setScores(Set<Score> scores) { this.scores = scores; }

    public void addScore(Score score) {
        score.setPlayer(this);
        scores.add(score);
    }

    public String getEmail() { return email; }

    public void setEmail(String email) {this.email = email; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public String getFirstName() { return firstName; }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; }


    // big string

    public String toString() { return id + firstName + lastName + email; }

    // DTO

    public Map<String, Object> toDTO() {
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("id", this.id);
        dto.put("email", this.email);
        return dto;
    }

    public Score getScore(Game game) {
        return scores.stream().filter(s -> s.getGame().getId()==game.getId()).findFirst().orElse(null);
    }

}