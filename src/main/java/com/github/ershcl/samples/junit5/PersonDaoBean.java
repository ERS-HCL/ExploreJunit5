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

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Configuration
//@ComponentScan(basePackages = "com.github.ershcl.samples.junit5")
@Component
public class PersonDaoBean implements PersonDao {
	//private ApplicationContext ctx;


	private static final Logger log = Logger.getLogger(PersonDaoBean.class);

	@Autowired
	private PersonDataStore dataSource;
	
//	public void PersonDaoBean() throws Exception {
//	    ctx = new AnnotationConfigApplicationContext(PersonDaoBean.class);
//	    //classUnderTest = ctx.getBean(PersonDaoBean.class);
//	  }

	/**
	 * NPE preventer. NEVER use a raw class-level reference. Use this getter
	 * instead.
	 */
	private PersonDataStore getDataSource() {
		if (dataSource == null) {
			//dataSource = new PersonDataStore();
			throw new RuntimeException("PersonDataStore is null");
		}
		return dataSource;
	}

	@Override
	public List<Person> findAll() {
		List<Person> ret = null;
		ret = getDataSource().getAllPerson();
		log.info("Found " + ret.size() + " rows from list");
		return ret;

	}

	@Override
	public Person findById(Long id) {
//		Person person = null;
//		List<Person> pList = getDataSource().getAllPerson();
//		for(Person p :pList){
//			if(p.getId().toString().equals(id.toString())){
//				person = p;
//				log.info("Person Information with ID : " + id + " is " + person.toString());
//				//return person;
//			}
//				
//		}
		List<Person> collect = getDataSource().getAllPerson().stream().filter(p-> p.getId().toString().equals(id.toString())).collect(Collectors.toList());
		Person person = collect.get(0);
		
		return person;
	}

	@Override
	public List<Person> findAllByLastName(String lastName) {
		List<Person> ret = null;
		ret = getDataSource().getAllPerson().stream().filter(p -> p.getLastName().equals(lastName))
				.collect(Collectors.toList());
		return ret;
	}

	@Override
	public Person add(Person person) {
		List<Person> ret = getDataSource().getAllPerson();
		//check for duplicate
		for(Person p: ret){
			if(p.equals(person))
			{
				log.info("Duplicate elements cannot be added " + person);
				return null;
			}
		}
		
		if (person.getId()==null) {
			Long tmpVal = ret.get(ret.size()-1).getId();
			person.withId(++tmpVal);
		} 
		
		getDataSource().getAllPerson().add(person);
		log.info("Added Person : "+ person.getId() + " is added.");
		return person;
	}
	
	/**
	 * Return true/false if Person object is added or not.
	 * @param person
	 * @return
	 */
	public Map<Person,Boolean> duplicateAdd(Person person){
		Map<Person,Boolean> personMap = new HashMap<Person,Boolean>();
		Person tmpPerson = add(person);
		if(tmpPerson!= null){
			personMap.put(person,true);
			return personMap;
		}else {
			personMap.put(person,false);
			return personMap;
		}
	}

	@Override
	public boolean update(Person person) {
		boolean ret = getDataSource().modifyDeletePersonList(person, "M");
		if(ret)
			log.info("Updated Person id is " + person.getId() + " and person is " +person.toString());
		else
			log.info("Person "+ person.toString() + " is not modified");
		return ret;
	}

	@Override
	public Person delete(Person person) {
		boolean ret = getDataSource().modifyDeletePersonList(person, "M");
		if(ret)
			log.info("Deleted Person id is " + person.getId() + " and person is " +person.toString());
		else
			log.info("Person "+ person.toString() + " is not deleted.");
		return person;
	}
	
	public static void main(String args[])
	{
		PersonDao personDao = new PersonDaoBean();
		System.out.println(personDao.findAll());
		// Find the person by id
		System.out.println("Person is " + personDao.findById(2l));
		// find by last name
		System.out.println("Person is " + personDao.findAllByLastName("Shastri"));
		//Adding a new Person
		Person p = new Person("Shastri","Vinay",55,"brown","M");
		personDao.add(p);
		// Edit/Modify the Person from the List
		LocalDate localDate = LocalDate.now();
	    Date date = Date.from(localDate.atStartOfDay().atZone(ZoneId
	            .systemDefault()).toInstant());

		p.setWhenCreated(date);
		System.out.println("Person " + p.toString() + " is modified " + personDao.update(p));
		
		// Delete the Person from the List
		System.out.println("Person " + p.toString() + " is modified " + personDao.delete(p));
		
	}

}
