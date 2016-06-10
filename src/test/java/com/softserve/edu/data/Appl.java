package com.softserve.edu.data;

public class Appl {
	public static void main(String[] args) {
		//User user = new User("aaa","bbb", ...);  // Bad Solution
		//
//		User user = new User();
//		user.setLogin("admin");
//		user.setPassword("admin");
//		user.setLastname("aaaa");
		//
//		User user = new User()
//			.setLogin("admin")
//			.setPassword("admin")
//			.setLastname("aaa");
		//
//		User user = User.get()
//				.setLogin("admin")
//				.setPassword("admin")
//				.setLastname("aaa");
		//
//		User user = User.get()
//				.setFirstname("adminWork")
//				.setLastname("adminWork")
//				.setEmail("adminWork@i.ua")
//				.setLogin("adminWork")
//				.setPassword("adminWork")
//				.setCommunity("Україна")
//				.setRole("Адміністратор")
//				.setStatus("Активний")
//				.build();
//		System.out.println("User Login = " + user.getLogin());
//		System.out.println("User Login = " + user.setLogin("111").toString());
//		System.out.println("User Login = " + user.getLogin());
		//
		IUser user = User.get()
				.setFirstname("adminWork")
				.setLastname("adminWork")
				.setEmail("adminWork@i.ua")
				.setLogin("adminWork")
				.setPassword("adminWork")
				.setCommunity("Україна")
				.setRole("Адміністратор")
				.setStatus("Активний")
				.build();
		System.out.println("User Login = " + user.getLogin());
		//System.out.println("User Login = " + user.setLogin("111").toString()); // Error
		System.out.println("User Login = " + user.getLogin());

	}
}
