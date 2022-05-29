package com.solution.lab1.repository;
import com.solution.lab1.entity.DatabaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DatabaseEntityRepository extends JpaRepository<DatabaseEntity, Integer> {

}
