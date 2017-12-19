package com.scientific.publications.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scientific.publications.model.Student;

/**
 * @author <a href="mailto:mauricionrgarcia@gmail.com">Mauricio Garcia</a>
 * @version
 * @sinse 18/12/2017 21:25:19
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {}