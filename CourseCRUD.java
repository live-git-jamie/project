package aCourseProject;

import java.util.HashMap;

public class CourseCRUD {
	private HashMap<Integer, Course> courses;
	
	public void generateCourses() {
		// Names for courses
		String[] courseNames = {"Java","C","C++",
				"Web Design","Databases","Python","Calculus I",
				"Calculus II", "PHY 103", "PHY 160"};
		float[] startTimes = {08.30f,08.30f,09.00f,09.00f,
							  10.45f,11.00f,13.15f,13.15f,
							  14.30f,15.00f};
		float[] endTimes   = {10.00f,10.00f,10.30f,10.30f,
							  12.05f,12.30f,14.45f,14.45f,
							  16.00f,16.45f};
		char[][] days	   = {{'M','W','F'},
							{'M','T','W','H','F'},
							{'T','H'}};
		// Set up collection
		courses = new HashMap<>();
		double rand;
		for(int i = 0; i < courseNames.length; i++) {
			courses.put(i+1,new Course(courseNames[i], "",startTimes[i],
					endTimes[i], (int)(Math.random()*10+12)));
			// Set the days
			rand = Math.random();
			if(rand < 0.3) {
				courses.get(i+1).setDays(days[0]);
			} else if (rand < 0.6) {
				courses.get(i+1).setDays(days[1]);
			} else {
				courses.get(i+1).setDays(days[2]);
			}
		}
	}
	
	public void getDetails(int courseID) {
		if(!courseExists(courseID)) {
			System.out.println("Doesn't exist.");
			return;
		}
		System.out.println(courses.get(courseID).toString());
	}
	
	public void deleteCourse(int courseID) {
		if(!courseExists(courseID)) {
			System.out.println("Doesn't exist.");
			return;
		}
		// Remove from list
		courses.remove(courseID);
//		Course courseDeleted = courses.remove(courseID);
		// Remove from database
		
	}
	
	/* HELPER METHODS */
	private boolean courseExists(int courseID) {
		if(courseID > courses.size() || courseID < 0) {
			return false;
		}
		return true;
	}
	
	/* GETTER */
	public Object[] getCourses() {
		return courses.values().toArray();
	}
	public int getCoursesSize() {
		System.out.println("@GETCOURSESIZE::size="+courses.size());
		return courses.size();
	}
}
