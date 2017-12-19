package com.scientific.publications.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scientific.publications.model.Publication;

/**
 * @author <a href="mailto:mauricionrgarcia@gmail.com">Mauricio Garcia</a>
 * @version
 * @sinse 18/12/2017 21:24:35
 */
@Repository
public interface PublicationRepository extends JpaRepository<Publication, Long> {

	/**
	 * {@link Publication} by id
	 *
	 * @param code
	 * @return {@link Publication}
	 */
	Optional<Publication> findByIdPublication(Long code);

}
