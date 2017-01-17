package domain;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import mappers.IMapResultSetToEntity;
import mappers.RoleMapper;
import mappers.UserMapper;

import org.junit.Test;

import domain.Repositories.RoleRepository;
import domain.Repositories.UserRepository;
import domain.uow.IUnitOfWork;
import domain.uow.UnitOfWork;

public class RoleCRUDTest {

	@Test
	public void test() {
		try{
			Connection connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/workdb");
			IMapResultSetToEntity<Role> mapper = new RoleMapper();
			IUnitOfWork uow = new UnitOfWork(connection);
			RoleRepository repo = new RoleRepository(connection, mapper, uow);
			Role r = new Role();
			r.setName("test");
			r.setUser_id(0);
			
			repo.add(r);
			
			r.setName("test2");
			r.setId(0);
			repo.update(r);
			repo.delete(r);
			
			uow.saveChanges();
			
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

}
