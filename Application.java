package aCourseProject;

public class Application {
	
	/* DRIVER (MAIN) */
	public static void main(String[] args) {
		// Set up courses classes
		CourseCRUD courseCRUD = new CourseCRUD();
		courseCRUD.generateCourses();
//		CourseRepository courseRepo = new CourseRepository(courseCRUD);
		
		// Display results
		int size = courseCRUD.getCoursesSize();
		for(int i = 1; i <= size; i++) {
			courseCRUD.getDetails(i);
		}
		
		// Write to file
//		String file = "courses.dat";
//		CourseRepository.writeToFile(file);
		// Read in
//		List<Course> courses = CourseRepository.readFile(file);
//		for(Course c : courses) {
//			System.out.println(c.toString());
//		}
		
		// Remove some courses
		System.out.println('\n'+"Removing 2 and 3");
		courseCRUD.deleteCourse(2);
		courseCRUD.deleteCourse(3);
		size = courseCRUD.getCoursesSize();
		Object[] temp = courseCRUD.getCourses();
		for(Object c: temp) {
			//c = (Course)c;
			System.out.println(c.toString());
		}
	}
}
