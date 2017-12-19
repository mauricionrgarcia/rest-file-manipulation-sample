package com.scientific.publications.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.scientific.publications.dto.PublicationDTO;
import com.scientific.publications.exception.BusinessException;
import com.scientific.publications.model.Publication;
import com.scientific.publications.repository.PublicationRepository;

/**
 * @author <a href="mailto:mauricionrgarcia@gmail.com">Mauricio Garcia</a>
 * @version
 * @sinse 18/12/2017 21:36:12
 */
@Service
public class PublicationServiceImpl implements PublicationService {

	/**
	 * Repository of {@link Publication}
	 */
	@Autowired
	private transient PublicationRepository repository;

	/**
	 * {@link Environment}
	 */
	@Autowired
	private transient Environment env;

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.scientific.publications.service.PublicationService#searchAllPublications(
	 * )
	 */
	@Override
	public List<Publication> searchAllPublications() {
		List<Publication> publication = repository.findAll();
		return publication;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.scientific.publications.service.PublicationService#save(com.scientific.
	 * publications.dto.PublicationDTO, byte[])
	 */
	@Override
	public Publication save(PublicationDTO publicationSave, byte[] file) throws BusinessException {

		try {

			Publication publication = new Publication();
			publication.setDtPublication(LocalDate.now());
			publication.setKeyWord(publicationSave.getKeyWord());
			publication.setSummary(publicationSave.getSummary());
			publication.setTitle(publicationSave.getTitle());
			publication.setFileName(fileName(FilenameUtils.removeExtension(publication.getTitle())));
			publication.setFileExtension(FilenameUtils.getExtension(publicationSave.getFileName()));

			StringBuilder directory = new StringBuilder();
			directory.append(env.getProperty("publications.directory"));
			directory.append(Calendar.getInstance().getTimeInMillis());
			directory.append("/");
			publication.setDirectory(directory.toString());

			this.writeFile(publication, file);

			return repository.save(publication);
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			throw new BusinessException("error.save.publications", "Error saving publication", e.getMessage(), e);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.scientific.publications.service.findOne#findOne(java.lang.Long)
	 */
	@Override
	public Publication findOne(Long code) {
		Optional<Publication> optional = repository.findByIdPublication(code);
		Publication publication = optional.orElseThrow(() -> new EmptyResultDataAccessException(1));
		return publication;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.scientific.publications.service.PublicationService#loadFile(java.lang.
	 * Long)
	 */
	@Override
	public PublicationDTO loadFile(Long idPublication) throws BusinessException {
		try {
			Publication publication = this.findOne(idPublication);
			PublicationDTO publicationDTO = new PublicationDTO();
			publicationDTO.setFileName(fileName(publication));

			FileInputStream file = new FileInputStream(direrotyPath(publication));
			publicationDTO.setFile(IOUtils.toByteArray(file));
			return publicationDTO;
		} catch (IOException e) {
			throw new BusinessException("error.download.publications", "Error retrieving file from publication",
					e.getMessage(), e);
		}
	}

	/**
	 * @param publication {@link Publication}
	 * @param bytes byte[]
	 * @return path
	 * @throws BusinessException Business Exception
	 */
	private String writeFile(Publication publication, byte[] bytes) throws BusinessException {
		try {
			File file = new File(direrotyPath(publication));
			file.getParentFile().mkdirs();
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
			bos.write(bytes);
			bos.close();
			return file.getPath();
		} catch (IOException e) {
			throw new BusinessException("error.download.publications", "Error retrieving file from publication",
					e.getMessage(), e);
		}
	}

	/**
	 * @param publication {@link Publication}
	 * @return diretoryPath
	 */
	private String direrotyPath(Publication publication) {
		StringBuilder builder = new StringBuilder();
		builder.append(publication.getDirectory());
		builder.append(fileName(publication));
		return builder.toString();
	}

	/**
	 * @param publication {@link Publication}
	 * @return diretoryPath
	 */
	private String fileName(Publication publication) {
		StringBuilder builder = new StringBuilder();
		builder.append(publication.getFileName());
		builder.append(".");
		builder.append(publication.getFileExtension());
		return builder.toString();
	}

	/**
	 * @param name File title
	 * @return File name
	 */
	private String fileName(String name) {
		return StringUtils.lowerCase(name).replace(" ", "-");
	}

}
