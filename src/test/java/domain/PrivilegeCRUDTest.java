package domain;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import mappers.IMapResultSetToEntity;
import mappers.PrivilegeMapper;
import mappers.UserMapper;

import org.junit.Test;

import domain.Repositories.PrivilegeRepository;
import domain.Repositories.UserRepository;
import domain.uow.IUnitOfWork;
import domain.uow.UnitOfWork;

public class PrivilegeCRUDTest {

	@Test
	public void test() {
		try{
			Connection connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/workdb");
			IMapResultSetToEntity<Privilege> mapper = new PrivilegeMapper();
			IUnitOfWork uow = new UnitOfWork(connection);
			PrivilegeRepository repo = new PrivilegeRepository(connection, mapper, uow);
			Privilege p = new Privilege();
			p.setName("test");
			p.setRole_id(0);
			repo.add(p);
			p.setName("test2");
			p.setId(0);
			
			repo.update(p);
			repo.delete(p);
			
			uow.saveChanges();
			
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

}
