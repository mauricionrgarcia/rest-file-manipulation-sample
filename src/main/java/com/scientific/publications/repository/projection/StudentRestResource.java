package com.scientific.publications.repository.projection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.scientific.publications.model.Student;
import com.scientific.publications.model.projection.StudentSearchProjection;

/**
 * Spring Data REST <br>
 * repository: students
 *
 * @author <a href="mailto:mauricionrgarcia@gmail.com">Mauricio Garcia</a>
 * @version
 * @sinse 18/12/2017 21:21:49
 */
@RepositoryRestResource(excerptProjection = StudentSearchProjection.class)
public interface StudentRestResource extends CrudRepository<Student, Long> {}