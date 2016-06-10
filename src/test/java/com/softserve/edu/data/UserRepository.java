package com.softserve.edu.data;

public final class UserRepository {
    private static volatile UserRepository instance = null;

	private UserRepository() {
	}
	
    public static UserRepository get() {
        if (instance == null) {
            synchronized (UserRepository.class) {
                if (instance == null) {
                    instance = new UserRepository();
                }
            }
        }
        return instance;
    }

	public IUser getAdmin() {
		return User.get()
		.setFirstname("admin")
		.setLastname("admin")
		.setEmail("adminWork@i.ua")
		.setLogin("admin")
		.setPassword("admin")
		.setCommunity("Україна")
		.setRole("Адміністратор")
		.setStatus("Активний")
		.build();
	}

	public IUser getAdminWork() {
		return User.get()
		.setFirstname("adminWork")
		.setLastname("adminWork")
		.setEmail("adminWork@i.ua")
		.setLogin("adminWork")
		.setPassword("adminWork")
		.setCommunity("Україна")
		.setRole("Адміністратор")
		.setStatus("Активний")
		.build();
	}

//    public List<IUser> getExistUsersCVS() {
//        return new UserUtils().getAllUsers();
//    }
//
//    public List<IUser> getExistUsersExcel() {
//        return new UserUtils("/users.xlsx", new ExcelUtils()).getAllUsers();
//    }

}
