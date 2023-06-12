package com.app.pojos;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "subjects")
@Getter
@Setter
@NoArgsConstructor
public class Subject extends BaseEntity {
	@Column(name = "subject_name",length = 30, unique = true)
	private String subjectName;
	@ManyToOne
	@JoinColumn(name = "course_id", nullable = false)
	private Course courseId;
	@OneToOne(mappedBy = "subject", cascade = CascadeType.ALL,orphanRemoval = true)
	private Faculty facultyId;
	
	@OneToMany(mappedBy = "subjectId", cascade = CascadeType.ALL,orphanRemoval = true)
	private List<Notes>notes;
	
	public Subject(int id)
	{
		super.setId(id);
	}
	
	public Subject(String subjectName){
		this.subjectName= subjectName;
	}

	public void addNotes(Notes note)
	{
		this.notes.add(note);
		note.setSubjectId(this);
	}
	
	public void removeNotes(Notes note)
	{
		this.notes.remove(note);
		note.setSubjectId(null);
	}
}
