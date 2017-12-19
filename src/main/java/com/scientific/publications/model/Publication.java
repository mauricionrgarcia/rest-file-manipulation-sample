package com.scientific.publications.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table
public class Publication implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id()
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idPublication;
	private LocalDate dtPublication;
	private String title;
	private String summary;
	private String keyWord;
	private String fileName;
	private String fileExtension;
	private String directory;

	@ManyToMany
	@JoinTable(name = "PublicationStudent", joinColumns = @JoinColumn(name = "idPublication"), inverseJoinColumns = @JoinColumn(name = "idStudent"))
	private List<Student> students;

	@ManyToMany
	@JoinTable(name = "PublicationLeader", joinColumns = @JoinColumn(name = "idPublication"), inverseJoinColumns = @JoinColumn(name = "idLeader"))
	private List<Leader> leaders;

	/**
	 * @return the idPublication
	 */
	public Long getIdPublication() {
		return idPublication;
	}

	/**
	 * @param idPublication the idPublication to set
	 */
	public void setIdPublication(Long idPublication) {
		this.idPublication = idPublication;
	}

	/**
	 * @return the dtPublication
	 */
	public LocalDate getDtPublication() {
		return dtPublication;
	}

	/**
	 * @param dtPublication the dtPublication to set
	 */
	public void setDtPublication(LocalDate dtPublication) {
		this.dtPublication = dtPublication;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the summary
	 */
	public String getSummary() {
		return summary;
	}

	/**
	 * @param summary the summary to set
	 */
	public void setSummary(String summary) {
		this.summary = summary;
	}

	/**
	 * @return the keyWord
	 */
	public String getKeyWord() {
		return keyWord;
	}

	/**
	 * @param keyWord the keyWord to set
	 */
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return the directory
	 */
	public String getDirectory() {
		return directory;
	}

	/**
	 * @param directory the directory to set
	 */
	public void setDirectory(String directory) {
		this.directory = directory;
	}

	/**
	 * @return the students
	 */
	public List<Student> getStudents() {
		return students;
	}

	/**
	 * @param students the students to set
	 */
	public void setStudents(List<Student> students) {
		this.students = students;
	}

	/**
	 * @return the leaders
	 */
	public List<Leader> getLeaders() {
		return leaders;
	}

	/**
	 * @param leaders the leaders to set
	 */
	public void setLeaders(List<Leader> leaders) {
		this.leaders = leaders;
	}

	/**
	 * @return the fileExtension
	 */
	public String getFileExtension() {
		return fileExtension;
	}

	/**
	 * @param fileExtension the fileExtension to set
	 */
	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idPublication == null) ? 0 : idPublication.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Publication other = (Publication) obj;
		if (idPublication == null) {
			if (other.idPublication != null)
				return false;
		} else if (!idPublication.equals(other.idPublication))
			return false;
		return true;
	}

}
