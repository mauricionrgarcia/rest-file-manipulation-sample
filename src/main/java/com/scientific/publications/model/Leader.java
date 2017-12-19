package com.scientific.publications.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Leader implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id()
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idLeader;

	private String name;

	/**
	 * @return the idLeader
	 */
	public Long getIdLeader() {
		return idLeader;
	}

	/**
	 * @param idLeader the idLeader to set
	 */
	public void setIdLeader(Long idLeader) {
		this.idLeader = idLeader;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/*
	 * * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idLeader == null) ? 0 : idLeader.hashCode());
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
		Leader other = (Leader) obj;
		if (idLeader == null) {
			if (other.idLeader != null)
				return false;
		} else if (!idLeader.equals(other.idLeader))
			return false;
		return true;
	}

}
