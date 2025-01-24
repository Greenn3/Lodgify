package com.example.serverside.Repositories;


import com.example.serverside.Entities.AccType;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccTypeRepository extends JpaRepository<AccType, Integer> {
    AccType findAccTypeById(Integer id);
}
