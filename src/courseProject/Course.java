package courseProject;

import java.io.Serializable;

public class Course implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static int idCounter = 0;
	
	private int courseID;
	private String courseName;
	private String courseDescr;
	private float startTime;
	private float endTime;
	private int numStudents;
	private char[] days = {'M','W',};
	
	public Course(String courseName, String courseDescr,
				  float startTime, float endTime, int numStudents) {
		idCounter++;
		courseID = idCounter;
		
		this.courseName = courseName;
		this.courseDescr = courseDescr;
		this.startTime = startTime;
		this.endTime = endTime;
		this.numStudents = numStudents;
	}


	/* GETTERS */
	public int getCourseID() {
		return courseID;
	}
	public String getCourseName() {
		return courseName;
	}
	public String getCourseDescr() {
		return courseDescr;
	}
	public float getStartTime() {
		return startTime;
	}
	public float getEndTime() {
		return endTime;
	}
	public int getNumStudents() {
		return numStudents;
	}
	public char[] getDays() {
		return days;
	}
	
	/* SETTERS */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public void setCourseDescr(String courseDescr) {
		this.courseDescr = courseDescr;
	}
	public void setStartTime(float startTime) {
		this.startTime = startTime;
	}
	public void setEndTime(float endTime) {
		this.endTime = endTime;
	}
	public void setNumStudents(int numStudents) {
		this.numStudents = numStudents;
	}
	public void setDays(char[] days) {
		this.days = days;
	}

	@Override
	public String toString() {
		String temp = "Course [courseID=" + courseID + ", courseName=" + courseName + ", courseDescr=" + courseDescr
				+ ",\n        startTime=" + startTime + ", endTime=" + endTime + ", numStudents=" + numStudents
				+ "\n        days={ " ;
		// Go through char array
		for(char day: days) {
			temp += day + " ";
		}
		return temp += "}" + "]";
	}
}
