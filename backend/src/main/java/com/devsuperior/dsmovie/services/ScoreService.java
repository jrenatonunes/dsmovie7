package com.devsuperior.dsmovie.services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dsmovie.dto.MovieDTO;
import com.devsuperior.dsmovie.dto.ScoreDTO;
import com.devsuperior.dsmovie.entities.Movie;
import com.devsuperior.dsmovie.entities.Score;
import com.devsuperior.dsmovie.entities.User;
import com.devsuperior.dsmovie.repositories.MovieRepository;
import com.devsuperior.dsmovie.repositories.ScoreRepository;
import com.devsuperior.dsmovie.repositories.UserRepository;

@Service
public class ScoreService {

	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ScoreRepository scoreRepository;

	@Transactional
	public MovieDTO saveScore(ScoreDTO scoreDTO) {

		User user = userRepository.findByEmail(scoreDTO.getEmail());

		if (user == null) {
			user = new User();
			user.setEmail(scoreDTO.getEmail());

			user = userRepository.saveAndFlush(user);
		}

		Movie movie = movieRepository.findById(scoreDTO.getMovieId()).get();

		Score score = new Score();
		score.setMovie(movie);
		score.setUser(user);
		score.setValue(scoreDTO.getScore());
		score = scoreRepository.saveAndFlush(score);

		Set<Score> scores = movie.getScores();

		double sumScores = 0.0;
		int countScores = scores.size();
		for (Score scoreAtual : scores) {
			sumScores += scoreAtual.getValue();
		}

		double averageScores = sumScores / countScores;

		movie.setScore(averageScores);
		movie.setCount(countScores);

		movie = movieRepository.saveAndFlush(movie);

		MovieDTO movieDTO = new MovieDTO(movie);
		return movieDTO;

	}

}
