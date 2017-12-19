package com.scientific.publications.service;

import java.util.List;

import com.scientific.publications.dto.PublicationDTO;
import com.scientific.publications.exception.BusinessException;
import com.scientific.publications.model.Publication;

/**
 * @author <a href="mailto:mauricionrgarcia@gmail.com">Mauricio Garcia</a>
 * @version
 * @sinse 18/12/2017 21:30:47
 */
public interface PublicationService {

	/**
	 * @return {@link List} {@link Publication}
	 */
	List<Publication> searchAllPublications();

	/**
	 * Save
	 *
	 * @param publication {@link PublicationDTO}
	 * @param file byte[]
	 * @return {@link Publication}
	 * @throws BusinessException Business Exception
	 */
	Publication save(PublicationDTO publication, byte[] file) throws BusinessException;

	/**
	 * @param code identifier
	 * @return {@link Publication}
	 */
	Publication findOne(Long code);

	/**
	 * @param idPublication identifier of publication
	 * @return {@link PublicationDTO} with the file in bytes
	 * @throws BusinessException Business Exception
	 */
	PublicationDTO loadFile(Long idPublication) throws BusinessException;

}
