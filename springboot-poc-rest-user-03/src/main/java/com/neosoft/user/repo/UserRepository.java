package com.neosoft.user.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.neosoft.user.entity.User;
@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	@Query("select u from User u where u.firstName=:firstName or u.surName=:surName")
	User findUserBy(@Param("firstName") String firstName,@Param("surName") String lastname);
	
	@Query("select u from User u where u.firstName=:firstname")
	User featchUserByName(@Param("firstname") String firstname);
	
	@Query(value = "select * from user ORDER BY dob ASC",nativeQuery = true)
	List<User> featchUsersByDob();
	
	@Query(value = "select * from user ORDER BY doj ASC",nativeQuery = true)
	List<User> featchUserByDoj();
	
	@Query("select u from User u where u.firstName=:firstname or u.surName=:surname or u.pincode=:pincode")
	List<User> fetchBy(@Param("firstname") String firstname,@Param("surname") String surname,
			@Param("pincode") Long pincode);

	@Transactional
	@Modifying
	
	@Query(value = "update User set isDeleted=true where userId=:userId")
	int softDelete(@Param("userId") int id);
	
	@Query("select u from User u where u.username=:username")
	User findByusername(@Param("username") String username);
	User findByUsername(String username);

}
