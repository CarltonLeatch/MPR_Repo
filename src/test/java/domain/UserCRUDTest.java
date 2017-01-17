package domain;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

import mappers.IMapResultSetToEntity;
import mappers.UserMapper;

import org.junit.Test;

import domain.Repositories.UserRepository;
import domain.uow.IUnitOfWork;
import domain.uow.UnitOfWork;

public class UserCRUDTest {

	@Test
	public void test() {
		try{
			Connection connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/workdb");
			IMapResultSetToEntity<User> mapper = new UserMapper();
			IUnitOfWork uow = new UnitOfWork(connection);
			UserRepository repo = new UserRepository(connection, mapper, uow);
			User u = new User();
			u.setLogin("test");
			u.setPassword("test1");
			
			repo.add(u);
			
			u.setLogin("test2");
			u.setId(0);
			repo.update(u);
			repo.delete(u);
			
			uow.saveChanges();
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		
	}

}
