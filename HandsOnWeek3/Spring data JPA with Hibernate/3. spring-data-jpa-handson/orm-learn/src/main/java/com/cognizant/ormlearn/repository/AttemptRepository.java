package com.cognizant.ormlearn.repository;

import com.cognizant.ormlearn.model.Attempt;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.CrudRepository;

public interface AttemptRepository extends CrudRepository<Attempt, Integer> {

	@Query("SELECT a FROM Attempt a " +
		       "JOIN FETCH a.user " +
		       "JOIN FETCH a.attemptQuestions aq " +
		       "JOIN FETCH aq.question q " +
		       "JOIN FETCH q.options " +
		       "JOIN FETCH aq.attemptOptions ao " +
		       "JOIN FETCH ao.option " +
		       "WHERE a.id = :attemptId AND a.user.id = :userId")
		Attempt getAttempt(@Param("userId") int userId, @Param("attemptId") int attemptId);
}
