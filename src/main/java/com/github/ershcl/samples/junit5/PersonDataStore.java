/*
 * Copyright 2017 HCL Technologies.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.ershcl.samples.junit5;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

/**
 * @author Jayant Chaudhury
 *
 */
@Component
public class PersonDataStore {
	Person person;
	private List<Person> personList;
	private static PersonDataStore instance = null;

	/**
	 * Empty constructor
	 */
	private PersonDataStore() {
		personList = new ArrayList<Person>();
		setup();
	}
	
	public static final PersonDataStore getInstance(){
		if(instance == null){
			instance = new PersonDataStore();
		}
		return instance;
	}

	/**
	 * Store All Persons.
	 */
	private void setup() {
		
		person = new Person("Makhija", "Sid", 23, "black", "M");
		person.withId(1l);
		personList.add(person);
		person = new Person("Mukherjee", "Kate", 55, "black", "F");
		person.withId(2l);
		personList.add(person);
		person = new Person("Chowdhary", "Arinjay", 4, "black", "M");
		person.withId(3l);
		personList.add(person);
		person = new Person("Shastri", "Vikram", 40, "brown", "M");
		person.withId(4l);
		personList.add(person);
		person = new Person("Iyer", "Ramesh", 63, "black", "M");
		person.withId(5l);
		personList.add(person);
		person = new Person("Thakur", "Soma", 28, "black", "F");
		person.withId(6l);
		personList.add(person);
		person = new Person("Mathur", "Varsha", 35, "black", "F");
		person.withId(7l);
		personList.add(person);
	}

	/**
	 * Return the PersonList.
	 * 
	 * @return
	 */
	public List<Person> getAllPerson() {
		return personList;
	}

	/**
	 * Modify/Delete the List<Person> with the Person object. If found , modify/delete the
	 * list else do nothing.
	 * 
	 * @param person
	 * @return isModified
	 */
	public boolean modifyDeletePersonList(Person person,String modifyOrDelete) {
		boolean isModified = false;
		int indx = 0;
		
		for (Person p : personList) {
			if (p.getId().equals(person.getId())) {
				if(modifyOrDelete.equals("M"))
				{
					personList.set(indx, person);
				} else {
					personList.remove(indx);
				}
				isModified=true;
			}
			indx++;
		}
		return isModified;
	}



}
