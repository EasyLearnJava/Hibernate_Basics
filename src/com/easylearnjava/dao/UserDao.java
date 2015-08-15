package com.easylearnjava.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.easylearnjava.dto.UserDto;
import com.easylearnjava.util.HibernateUtil;

public class UserDao {
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		
		HibernateUtil util = new HibernateUtil();
		Session session = util.getSession();		
		
		List<UserDto> usersList = null;
		
		System.out.println("Using SQLQuery in hibernate...");
		SQLQuery query = session.createSQLQuery("select * from user");
		query.addEntity(UserDto.class);
		usersList = query.list(); 
		printDetails(usersList);
		
		System.out.println("Using HQLQuery in hibernate...");
		usersList = session.createQuery("from UserDto").list();
		//select userdto0_.user_id as user1_0_, userdto0_.user_name as user2_0_, userdto0_.user_password as user3_0_ from user userdto0_
		printDetails(usersList);
		
		usersList = session.createQuery("select usr from UserDto usr").list();
		//select userdto0_.user_id as user1_0_, userdto0_.user_name as user2_0_, userdto0_.user_password as user3_0_ from user userdto0_
		printDetails(usersList);
		
		List<String> userNamesList = session.createQuery("select usr.uname from UserDto usr").list();
		//select userdto0_.user_name as col_0_0_ from user userdto0_
		if(userNamesList != null){
			for(String str : userNamesList)
			System.out.println("User name : " + str);
		}
		System.out.println();
		
		List<String> userpwdList = session.createQuery("select usr.password from UserDto usr where usr.uid = 2").list();
		//select userdto0_.user_password as col_0_0_ from user userdto0_ where userdto0_.user_id=2
		if(userpwdList != null){
			for(String str : userpwdList)
			System.out.println("User password : " + str);
		}
		System.out.println();
		
		String usrPassword = (String) session.createQuery("select usr.password from UserDto usr where usr.uid = 2").uniqueResult();
		//select userdto0_.user_password as col_0_0_ from user userdto0_ where userdto0_.user_id=2
		System.out.println("User password : " + usrPassword);
		System.out.println();
		
		Integer usrIdInTable = (Integer) session.createQuery("select usr.uid from UserDto usr where usr.uname = 'raghu'").uniqueResult();
		//select userdto0_.user_id as col_0_0_ from user userdto0_ where userdto0_.user_name='raghu'
		System.out.println("User id : " + usrIdInTable);
		System.out.println();
		
		Query myQuery = session.createQuery("select usr.password from UserDto usr where usr.uid = :dynamicUserId");
		myQuery.setParameter("dynamicUserId", 1);
		//select userdto0_.user_password as col_0_0_ from user userdto0_ where userdto0_.user_id=?
		List<String> userPasswords = myQuery.list();
		//String userPwd = (String)myQuery.uniqueResult(); //To fetch unique results
		if(userPasswords != null){
			for(String str : userPasswords)
			System.out.println("User password : " + str);
		}
		System.out.println();
		
		System.out.println("Using Criteria in hibernate...");
		Criteria cr = session.createCriteria(UserDto.class);
		//select this_.user_id as user1_0_0_, this_.user_name as user2_0_0_, this_.user_password as user3_0_0_ from user this_
		List<UserDto> DtoObjs = cr.list();
		printDetails(DtoObjs);
		System.out.println();
		
		System.out.println("Using Criteria with restrictions in hibernate...");
		Criteria crt = session.createCriteria(UserDto.class);
		crt.add(Restrictions.eq("uid", 2));
		//select this_.user_id as user1_0_0_, this_.user_name as user2_0_0_, this_.user_password as user3_0_0_ from user this_ where this_.user_id=?
		List<UserDto> results = crt.list();
		printDetails(results);
		System.out.println();
		
		Criteria crit = session.createCriteria(UserDto.class);
		crit.add(Restrictions.eq("uid", 2));
		//select this_.user_id as user1_0_0_, this_.user_name as user2_0_0_, this_.user_password as user3_0_0_ from user this_ where this_.user_id=?
		UserDto userObj = (UserDto)crit.uniqueResult();
		System.out.println("user name : " + userObj.getUname());
		System.out.println();
		
		Criteria crta = session.createCriteria(UserDto.class);
		crta.add(Restrictions.like("uname", "%a%"));
		crta.add(Restrictions.le("uid", 10));
		//select this_.user_id as user1_0_0_, this_.user_name as user2_0_0_, this_.user_password as user3_0_0_ from user this_ where this_.user_name like ? and this_.user_id<=?
		List<UserDto> userList = crta.list();
		printDetails(userList);
		System.out.println();
		
	}
	
	public static void printDetails(List<UserDto> lst){
		if(lst != null){
			for(UserDto userObj : lst){
				System.out.println("user id : " + userObj.getUid());
				System.out.println("user name : " + userObj.getUname());
				System.out.println("user password : " + userObj.getPassword());
			}
		}
		System.out.println();
	}

}
