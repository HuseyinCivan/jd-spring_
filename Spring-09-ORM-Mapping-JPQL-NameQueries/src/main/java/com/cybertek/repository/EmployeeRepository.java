package com.cybertek.repository;

import com.cybertek.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("Select e from Employee e where e.email = 'dtrail8@tamu.edu'")
    Employee getEmployeeDetail();

    @Query("select e.salary from Employee e where e.email = 'dtrail8@tamu.edu'")
    Integer getEmployeeSalary();


    //single bind parameter
    @Query("select e from Employee e where e.email=?1")
    Optional<Employee> getEmployeeByEmail(String email); //optional i eger return u null gelirse hata vermesin diye ekledik, onsuz da olabilir

    //multible bind parameter
    @Query("select e from Employee e where e.email=?1 and e.salary=?2")
    Employee getEmployeeByEmailAndSalary(String email, int salary);


    //single named parameter
    @Query("select e from Employee e where e.salary=:salary")
    Employee getEmployeeBySalary(@Param("salary") int salary);

    //multible name paramaters
    @Query("select e from Employee e where e.firstName=:name or e.salary=:salary")
    List<Employee> getEmployeeByFirstNameOrSalary(@Param("name") String name, @Param("salary") int salary);

    //not equal
    @Query("SELECT e from Employee e where e.salary <> ?1")
    List<Employee> getEmployeeBySalaryNotEqual(int salary);

    //like /contains /startwith / ends with
    @Query("select e from Employee e where e.firstName like ?1")
    List<Employee> getEmployeeByFirstNameLike(String patter);

    @Query("select e from Employee e where e.salary < ?1")
    List<Employee> getEmployeeBySalaryLessThan(int salary);

    //greater than
    @Query("select e from Employee e where e.salary > ?1")
    List<Employee> getEmployeeBySalaryGreaterThan(int salary);

    //between
    @Query("select e from Employee e where e.salary between ?1 and ?2")
    List<Employee> getEmployeeBySalaryBetween(int salary1, int salary2);

    //before
    @Query("select e from Employee e where e.hireDate >?1")
    List<Employee> getEmployeeByHireDateBefore(LocalDate date);

    //null
    @Query("select e from Employee e where e.email is NULL ")
    List<Employee> getEmployeeByEmailIsNull();

    //not null
    @Query("select e from Employee e where e.email is not NULL")
    List<Employee> getEmployeeByEmailIsNotNull();

    //sort salary in ascending order
    @Query("select e from Employee e order by e.salary asc")
    List<Employee> getEmployeeBySalaryOrderByAsc();

    //sort salary in decending order
    @Query("select e from Employee e order by e.salary desc ")
    List<Employee> getEmployeeBySalaryOrderByDec();

    //native query
    @Query(value = "select * from employees where salary = ?1" ,nativeQuery = true)
    List<Employee> readEmployeeBySalary(int salary);

    @Modifying
    @Transactional
    @Query("update Employee e set e.email ='admin@email.com'  where e.id =:id")
    void updateEmployeeJPQL(@Param("id") Integer id);


    @Modifying
    @Transactional
    @Query(value = "UPDATE employees SET email='admin@email.com' WHERE id=:id",nativeQuery = true)
    void updateEmployeeNativeQuery(@Param("id") Integer id);

    //named Query
    List<Employee> retrieveEmployeesSalaryGreaterThan(Integer salary);






}
