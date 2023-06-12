package com.app.pojos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "course")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Course extends BaseEntity {
	@Column(name="course_name",length = 30,unique = true)
	private String courseName;
	private int capacity;
	
	@OneToMany(mappedBy = "course", cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.EAGER)
	//If you we using more than one JOIN FETCH directives:
	//Hibernate will throw the infamous:
	//Hibernate doesn't allow fetching more than one bag because that would generate a Cartesian product.
	//https://stackoverflow.com/questions/4334970/hibernate-throws-multiplebagfetchexception-cannot-simultaneously-fetch-multipl
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Student> students=new ArrayList<Student>();
	
	@OneToMany(mappedBy = "courseId", cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Subject> subjects=new ArrayList<Subject>();
	
	public Course(int id)
	{
		super.setId(id);
	}
	
    public void addStudent(Student student)
    {
      this.students.add(student);
      student.setCourse(this);
    }
    
    public void removeStudent(Student student)
    {
      this.students.remove(student);
      student.setCourse(null);
    }
    
    public void addSubject(Subject subject)
    {
      this.subjects.add(subject);
      subject.setCourseId(this);
    }
    
    public void removeSubject(Subject subject)
    {
      this.subjects.remove(subject);
      subject.setCourseId(null);
    }

	public Course(String courseName, int capacity) {
		super();
		this.courseName = courseName;
		this.capacity = capacity;
	}
	
}
