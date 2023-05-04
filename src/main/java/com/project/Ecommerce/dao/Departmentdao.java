package com.project.Ecommerce.dao;
import com.project.Ecommerce.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Departmentdao extends JpaRepository<Department, Integer> {
}