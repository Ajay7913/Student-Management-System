package com.app.pojos;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "notes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Notes extends BaseEntity{
	@ManyToOne
	@JoinColumn(name="subject_id") //Child table, owning side
    private Subject subjectId;
	
	@Column(columnDefinition = "MEDIUMBLOB")
	private String notes;
	
	@Column(name="upload_date")
	private LocalDate uploadDate;
	
	@Override
	public String toString() {
		return "Notes [uploadDate=" + uploadDate + "]";
	}
	
}
