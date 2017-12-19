package com.scientific.publications.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scientific.publications.model.Leader;

/**
 * @author <a href="mailto:mauricionrgarcia@gmail.com">Mauricio Garcia</a>
 * @version
 * @sinse 18/12/2017 21:24:03
 */
@Repository
public interface LeaderRepository extends JpaRepository<Leader, Long> {}