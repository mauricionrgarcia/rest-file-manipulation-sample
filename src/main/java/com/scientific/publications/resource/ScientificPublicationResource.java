package com.scientific.publications.resource;

import java.io.IOException;
import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.scientific.publications.dto.PublicationDTO;
import com.scientific.publications.exception.BusinessException;
import com.scientific.publications.json.ObjectMapperWrapper;
import com.scientific.publications.model.Publication;
import com.scientific.publications.service.PublicationService;

/**
 * exposes hypermedia-driven HTTP resources for {@link Publication}
 *
 * @author <a href="mailto:mauricionrgarcia@gmail.com">Mauricio Garcia</a>
 * @version
 * @sinse 18/12/2017 21:25:57
 */
@RestController
@RequestMapping("/publications")
public class ScientificPublicationResource {

	/**
	 * publications service
	 */
	@Autowired
	private transient PublicationService publicationService;

	/**
	 * @see ObjectMapperWrapper
	 */
	@Autowired
	private transient ObjectMapperWrapper objectMapper;

	/**
	 * @return Get to return all Publications
	 */
	@GetMapping
	public ResponseEntity<List<Publication>> searchAllPublications() {
		List<Publication> publication = publicationService.searchAllPublications();
		return ResponseEntity.ok().body(publication);
	}

	/**
	 * Upload and save of {@link Publication}
	 *
	 * @param file {@link MultipartFile}
	 * @param json {@link String} to send a json
	 * @return {@link ResponseEntity}
	 * @throws BusinessException Business Exception
	 */
	@PostMapping(headers = { "content-type=multipart/mixed", "content-type=multipart/form-data" })
	public ResponseEntity<Publication> save(@RequestPart("file") MultipartFile file, @RequestPart String json)
			throws BusinessException {
		try {
			PublicationDTO publication = objectMapper.readValue(json, PublicationDTO.class);
			publication.setFileName(file.getOriginalFilename());
			Publication entity = publicationService.save(publication, file.getBytes());
			URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().path("{publication}")
					.buildAndExpand(entity.getIdPublication()).toUri();
			return ResponseEntity.created(location).body(entity);
		} catch (Exception e) {
			throw new BusinessException("error.save.publications", "Error saving publication", e.getMessage(), e);
		}
	}

	/**
	 * Download publications file
	 *
	 * @param code identifier
	 * @param response {@link HttpServletResponse}
	 * @return {@link ResponseEntity}
	 * @throws BusinessException Business Exception
	 * @throws IOException file not found
	 */
	@GetMapping("/{id}/download")
	public ResponseEntity<Void> downloadBytes(@PathVariable("id") Long code, HttpServletResponse response)
			throws BusinessException, IOException {

		PublicationDTO publication = publicationService.loadFile(code);

		response.setContentType("application/x-download");
		response.setHeader("Content-Disposition", "attachment; filename=" + publication.getFileName());
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.getOutputStream().write(publication.getFile());

		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * Find a publications by
	 *
	 * @param code identifier
	 * @return {@link Publication}
	 * @throws BusinessException Business Exception
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Publication> find(@PathVariable("id") Long code) throws BusinessException {
		Publication publication = publicationService.findOne(code);
		return ResponseEntity.ok(publication);
	}
}
