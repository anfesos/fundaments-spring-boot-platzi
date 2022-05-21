package com.studyplatzi.springboot.fundaments.repository;

import com.studyplatzi.springboot.fundaments.dto.UserDto;
import com.studyplatzi.springboot.fundaments.entity.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

//public interface UserRepository extends JpaRepository<User, Long> // sin paginación
@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    @Query("Select u from User u WHERE u.email=?1")
    Optional<User> findByUserEmail(String email);

    @Query("SELECT u FROM User u WHERE u.name like ?1%")
    List<User> findAndSort(String name, Sort sort);

    List<User> findByName(String name);

    Optional<User> findByEmailAndName(String email, String name);

    List<User> findByNameLike(String name);

    List<User> findByNameOrEmail(String name, String email);

    List<User> findByBirthDateBetween(LocalDate begin, LocalDate end);

    List<User> findByNameLikeOrderByIdDesc(String name);

    List<User> findByNameContainingOrderByIdDesc(String name);

    @Query("SELECT new com.studyplatzi.springboot.fundaments.dto.UserDto(u.id,u.name,u.birthDate) " +
            "FROM User u " +
            "WHERE u.birthDate=:parameterDate " +
            "and u.email=:parameterEmail ")
    Optional<UserDto> getAllByBirthDateAndEmail(@Param("parameterDate") LocalDate date,
                                                @Param("parameterEmail") String email);

    List<User> findAll();
}
