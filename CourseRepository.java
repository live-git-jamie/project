package aCourseProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class CourseRepository {
	CourseCRUD coursesCRUD;
	
	public CourseRepository(CourseCRUD c) {
		this.coursesCRUD = c;
		
		/* Connect to databse */
	}
	
	/* FILE I/O */
	@Deprecated
	public void writeToFile(String fileName) {
		try {
			// Set things up
			FileOutputStream fileOutStream = new FileOutputStream(fileName);
			ObjectOutputStream objOutStream = new ObjectOutputStream(fileOutStream);
			Object[] courses = coursesCRUD.getCourses();
			// Write out
			for(Object course: courses) {
				objOutStream.writeObject(course);
			}
			
			objOutStream.close();
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: <"+fileName+"> not found");
		} catch (IOException e) {
			System.out.println("ERROR accessing <"+fileName+">");
		}
		
	}
	
	@Deprecated
	public List<Course> readFile(String fileName) {
		List<Course> courses = new ArrayList<>();
		FileInputStream fileInStream = null;
		ObjectInputStream objInStream = null;
		try {
			// Set things up
			fileInStream = new FileInputStream(fileName);
			objInStream = new ObjectInputStream(fileInStream);
			// Read in
			try {
				while(true) {
					courses.add((Course)objInStream.readObject());
				}
			} catch (ClassNotFoundException e) {
				System.out.println("@readFILE:Class not Found");
			} catch (IOException e) {
				objInStream.close();
			}
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: <"+fileName+"> not found");
		} catch (IOException e) {
			System.out.println("ERROR accessing <"+fileName+">");
		}
	
		return courses;
	}

}