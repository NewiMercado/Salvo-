package com.mindhub.salvo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class SalvoApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(SalvoApplication.class, args);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Bean
	public CommandLineRunner initData(PlayerRepository playerRepository, GameRepository gameRepository, GamePlayerRepository gameplayerRepository, ShipRepository
									  shipRepository, ScoreRepository scoreRepository) {
		return (args) -> {
			// save a couple of players
			Player player1 = new Player(1, "Jack", "Bauer", "j.bauer@ctu.gov", "1");
			playerRepository.save(player1);
			Player player2 = new Player(2, "Chloe", "O'Brian", "c.obrian@ctu.gov", "2");
			playerRepository.save(player2);
			Player player3 = new Player(3, "Kim", "Bauer", "kim_bauer@gmail.com", "3");
			playerRepository.save(player3);
			Player player4 = new Player(4, "Toni", "Almeida", "t.almeida@ctu.gov", "4");
			playerRepository.save(player4);
			// save new games
			Game game1 = new Game(LocalDateTime.now());
			gameRepository.save(game1);
			Game game2 = new Game(LocalDateTime.now().plusHours(1));
			gameRepository.save(game2);
			Game game3 = new Game(LocalDateTime.now().plusHours(2));
			gameRepository.save(game3);
			Game game4 = new Game(LocalDateTime.now());
			gameRepository.save(game4);
			Game game5 = new Game(LocalDateTime.now().plusHours(1));
			gameRepository.save(game5);
			Game game8 = new Game(LocalDateTime.now().plusHours(1));
			gameRepository.save(game8);

			// adding ships to gameplayers
			// GAME1
			Set<Ship> ships = new HashSet<>();
			ships.add(new Ship("Destroyer", Arrays.asList("H2","H3", "H4")));
			ships.add(new Ship("Submarine", Arrays.asList("E1", "F1", "G1")));
			ships.add(new Ship("Patrol Boat", Arrays.asList("B4","B5")));
			Set<Ship> ships2 = new HashSet<>();
			ships2.add(new Ship("Destroyer", Arrays.asList("B5","C5", "D5")));
			ships2.add(new Ship("Patrol Boat", Arrays.asList("F1","F2")));
			// GAME2
			Set<Ship> ships3 = new HashSet<>();
			ships3.add(new Ship("Destroyer", Arrays.asList("B5","C5", "D5")));
			ships3.add(new Ship("Patrol Boat", Arrays.asList("C6", "C7")));
			Set<Ship> ships4 = new HashSet<>();
			ships4.add(new Ship("Submarine", Arrays.asList("A2", "A3", "A4")));
			ships4.add(new Ship("Patrol Boat", Arrays.asList("G6", "H6")));
			// GAME3
			Set<Ship> ships5 = new HashSet<>();
			ships5.add(new Ship("Destroyer", Arrays.asList("B5","C5", "D5")));
			ships5.add(new Ship("Destroyer", Arrays.asList("C6", "C7")));
			Set<Ship> ships6 = new HashSet<>();
			ships6.add(new Ship("Submarine", Arrays.asList("A2", "A3", "A4")));
			ships6.add(new Ship("Patrol Boat", Arrays.asList("G6", "H6")));
			// GAME4
			Set<Ship> ships7 = new HashSet<>();
			ships7.add(new Ship("Destroyer", Arrays.asList("B5", "C5", "D5")));
			ships7.add(new Ship("Patrol Boat", Arrays.asList("G6", "H6")));
			Set<Ship> ships8 = new HashSet<>();
			ships8.add(new Ship("Submarine", Arrays.asList("A2", "A4", "A4")));
			ships8.add(new Ship("Patrol Boat", Arrays.asList("G6", "H6")));
			// GAME5
			Set<Ship> ships9 = new HashSet<>();
			ships9.add(new Ship("Destroyer", Arrays.asList("B5", "C5", "D5")));
			ships9.add(new Ship("Patrol Boat", Arrays.asList("C6", "C7")));
			Set<Ship> ships10 = new HashSet<>();
			ships10.add(new Ship("Submarine", Arrays.asList("A2", "A3", "A4")));
			ships10.add(new Ship("Patrol Boat", Arrays.asList("G6", "H6")));
			// GAME6
			Set<Ship> ships11 = new HashSet<>();
			ships11.add(new Ship("Destroyer", Arrays.asList("B5", "C5", "D5")));
			ships11.add(new Ship("Patrol Boat", Arrays.asList("C6", "C7")));
			// GAME8
			Set<Ship> ships12 = new HashSet<>();
			ships12.add(new Ship("Destroyer", Arrays.asList("B5", "C5", "D5")));
			ships12.add(new Ship("Patrol Boat", Arrays.asList("C6", "C7")));
			Set<Ship> ships13 = new HashSet<>();
			ships13.add(new Ship("Submarine", Arrays.asList("A2", "A3", "A4")));
			ships13.add(new Ship("Patrol Boat", Arrays.asList("G6", "H6")));

			//set salvoes positions
			// GAME1
			Set<Salvo> salvo = new HashSet<>();
			salvo.add(new Salvo(1, new ArrayList<>(Arrays.asList("B5","C5","F1"))));
			salvo.add(new Salvo(2, new ArrayList<>(Arrays.asList("F2","D5"))));
			Set<Salvo> salvo2 = new HashSet<>();
			salvo2.add(new Salvo(1, new ArrayList<>(Arrays.asList("B4","B5","B6"))));
			salvo2.add(new Salvo(2, new ArrayList<>(Arrays.asList("E1","H3","A2"))));
			// GAME2
			Set<Salvo> salvo3 = new HashSet<>();
			salvo3.add(new Salvo(1, new ArrayList<>(Arrays.asList("A2","A4","G6"))));
			salvo3.add(new Salvo(2, new ArrayList<>(Arrays.asList("A3","H6"))));
			Set<Salvo> salvo4 = new HashSet<>();
			salvo4.add(new Salvo(1, new ArrayList<>(Arrays.asList("B5","D5","C7"))));
			salvo4.add(new Salvo(2, new ArrayList<>(Arrays.asList("C5","C6"))));
			// GAME3
			Set<Salvo> salvo5 = new HashSet<>();
			salvo5.add(new Salvo(1, new ArrayList<>(Arrays.asList("G6","H6","A4"))));
			salvo5.add(new Salvo(2, new ArrayList<>(Arrays.asList("A2","A3", "D8"))));
			Set<Salvo> salvo6 = new HashSet<>();
			salvo6.add(new Salvo(1, new ArrayList<>(Arrays.asList("H1","H2","H3"))));
			salvo6.add(new Salvo(2, new ArrayList<>(Arrays.asList("E1","F2", "G3"))));
			// GAME4
			Set<Salvo> salvo7 = new HashSet<>();
			salvo7.add(new Salvo(1, new ArrayList<>(Arrays.asList("A3","A4","F7"))));
			salvo7.add(new Salvo(2, new ArrayList<>(Arrays.asList("A2","G6", "H6"))));
			Set<Salvo> salvo8 = new HashSet<>();
			salvo8.add(new Salvo(1, new ArrayList<>(Arrays.asList("B5","C6","H1"))));
			salvo8.add(new Salvo(2, new ArrayList<>(Arrays.asList("C5","C7", "D5"))));
			// GAME5
			Set<Salvo> salvo9 = new HashSet<>();
			salvo9.add(new Salvo(1, new ArrayList<>(Arrays.asList("A1","A2","A3"))));
			salvo9.add(new Salvo(2, new ArrayList<>(Arrays.asList("G6","G7", "G8"))));
			Set<Salvo> salvo10 = new HashSet<>();
			salvo10.add(new Salvo(1, new ArrayList<>(Arrays.asList("B5","B6","C7"))));
			salvo10.add(new Salvo(2, new ArrayList<>(Arrays.asList("C6","D6", "E6"))));
			Set<Salvo> salvo11 = new HashSet<>();
			salvo11.add(new Salvo(3, new ArrayList<>(Arrays.asList("H1","H8"))));

			//scores
			Set<Score> score = new HashSet<>();
			score.add(new Score(game1, player1, 1.0));
			Set<Score> score3 = new HashSet<>();
			score3.add(new Score(game1, player2, 0.0));
			Set<Score> score4 = new HashSet<>();
			score4.add(new Score(game2, player4, 0.5));

			//save player's games
			gameplayerRepository.save(new GamePlayer(game1, player1, ships, salvo));
			gameplayerRepository.save(new GamePlayer(game1, player2, ships2, salvo2));
			gameplayerRepository.save(new GamePlayer(game2, player1, ships3, salvo3));
			gameplayerRepository.save(new GamePlayer(game2, player2, ships4, salvo4));
			gameplayerRepository.save(new GamePlayer(game3, player2, ships5, salvo5));
			gameplayerRepository.save(new GamePlayer(game3, player4, ships6, salvo6));
			gameplayerRepository.save(new GamePlayer(game4, player2, ships7, salvo7));
			gameplayerRepository.save(new GamePlayer(game4, player1, ships8, salvo8));
			gameplayerRepository.save(new GamePlayer(game5, player4, ships9, salvo9));
			gameplayerRepository.save(new GamePlayer(game5, player1, ships10, salvo10));
			gameplayerRepository.save(new GamePlayer(game8, player3, ships11, salvo11));

			// score repository
		};
	}

}

@Configuration
class WebSecurityConfiguration extends GlobalAuthenticationConfigurerAdapter {

	@Autowired
	PlayerRepository playerRepository;

	@Override
	public void init(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(inputName-> {
			Player player = playerRepository.findByEmail(inputName);
			if (player != null) {
				return new User(player.getEmail(), player.getPassword(),
						AuthorityUtils.createAuthorityList("USER"));
			} else {
				throw new UsernameNotFoundException("Unknown user: " + inputName);
			}
		});
	}
}

@Configuration
@EnableWebSecurity
class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		FormLoginConfigurer<HttpSecurity> httpSecurityFormLoginConfigurer = http.authorizeRequests()
				.antMatchers("/admin/**").hasAuthority("ADMIN")
				.antMatchers("/**").hasAuthority("USER")
				.and()
				.formLogin()
				.loginPage("/web/games");
	}
}