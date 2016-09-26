package net.ukr.geka3;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

public class Group implements Serializable {
	String name;

	// Student[] array = new Student[10];

	private ArrayList<Student> array = new ArrayList<>();

	public Group(String name) {
		super();
		this.name = name;
	}

	public static Group getGroup(String address) {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(address))) {
			return (Group) ois.readObject();

		} catch (IOException e) {
			System.out.println("IO error" + e);
		} catch (ClassNotFoundException e) {
			System.out.println("error no found class");
		} catch (ClassCastException e) {
			System.out.println("error mismatch class");
		}

		return null;
	}

	public void saveGroup(String address) {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(address))) {
			oos.writeObject(this);
			oos.flush();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addStudent(Student std)  {

		array.add(std);

		// for (int i = 0; i < array.length; i++) {
		// if (array[i] == null) {
		// array[i] = std;
		// System.out.println(std.name + " is added to group " + this.name);
		// return;
		// }
		// }
		// throw new FullGroupException("Group " + name + " is full Exception
		// for " + std.name);
	}

	public void addStudent() throws InputMismatchException {
		Scanner sc = new Scanner(System.in);

		System.out.println("Input name");
		String name = sc.nextLine();

		System.out.println("Input surName");
		String surName = sc.nextLine();

		System.out.println("Input age");
		int age = sc.nextInt();
		sc.nextLine();
		System.out.println("Input sex M / other W");
		boolean sex;
		String temp = sc.nextLine();

		if (temp.equalsIgnoreCase("M")) {
			sex = true;
			System.out.println("you input M");
		} else {
			sex = false;
			System.out.println("you input W");
		}

		System.out.println("Input course");
		int course = sc.nextInt();

		System.out.println("Input departmant");
		String departmant = sc.nextLine();
		departmant = sc.nextLine();

		this.addStudent(new Student(name, surName, age, sex, course, departmant));

		SortStudentsArrya(array);

	}

	
	
	public String getSortedListOfStudents() {
		StringBuilder studList = new StringBuilder();
		SortStudentsArrya(array);
		
		for (int i = 0;i < array.size() ; i++) {
			Student temp = array.get(i);
			if (temp != null) {
				studList.append(i + " " + temp.surName + " " + temp.name + System.lineSeparator());
			} else {
				 studList.append(i + " null" + System.lineSeparator());
			}

		}
		return studList.toString();
	}

	static public void SortStudentsArrya(ArrayList array) {
		Collections.sort(array,sortBySurName.thenComparing(sortByName));
		
		
//		Student temp = null;
//		for (int i = 0; i < stdArray.length; i++) {
//			for (int j = i; j < stdArray.length; j++) {
//				if (stdArray[i] == null) {
//					temp = stdArray[i];
//					stdArray[i] = stdArray[j];
//					stdArray[j] = temp;
//				} else if (stdArray[j] == null) {
//				}
//
//				else if ((stdArray[i].surName + stdArray[i].name)
//						.compareToIgnoreCase(stdArray[j].surName + stdArray[j].name) > 0) {
//					temp = stdArray[i];
//					stdArray[i] = stdArray[j];
//					stdArray[j] = temp;
//				}
//
//			}
//		}

	}

	public Student getStudent(String surName) {
		for (Student temp : array) {
			if (temp != null && temp.surName.equals(surName)) {
				return temp;
			}
		}
		return null;
	}

	public Student delStudent(int index) {
		if (array.get(index) == null) {
			System.out.println("student is null");
			return null;
		}
		System.out.println("index " + index + " was deleted " + array.get(index).surName);
		Student temp = array.get(index);
		array.remove(index);
		//SortStudentsArrya(array);
		return temp;

	}

	static Comparator<Student> sortByName = new Comparator<Student>() {
		public int compare(Student st1, Student st2) {
			return st1.name.compareToIgnoreCase(st2.name);

		}
	};
	
	static Comparator<Student> sortBySurName = new Comparator<Student>(){
		public int compare(Student st1, Student st2) {
			return st1.surName.compareToIgnoreCase(st2.surName);

		}
	};

}
