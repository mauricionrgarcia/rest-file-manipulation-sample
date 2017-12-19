package com.scientific.publications.model.projection;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import com.scientific.publications.model.Student;

/**
 * projection to simplify the return of student consultation
 *
 * @author <a href="mailto:mauricionrgarcia@gmail.com">Mauricio Garcia</a>
 * @version
 * @sinse 18/12/2017 21:16:24
 */
@Projection(name = "search", types = Student.class)
public interface StudentSearchProjection {

	public String getName();

	@Value("#{target.leader.name}")
	public String getLeader();
}
