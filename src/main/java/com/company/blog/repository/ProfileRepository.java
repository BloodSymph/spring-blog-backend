package com.company.blog.repository;

import com.company.blog.domain.ProfileEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<ProfileEntity, Long> {
   List<ProfileEntity> findByLastNameOrFirstNameOrCityOrCountryIgnoreCase(
           String lastName, String firstName, String city, String country
   );
   Optional<ProfileEntity> findByUser_Username(String username);
   @Transactional
   Optional<ProfileEntity> deleteByUser_Username(String username);
   boolean existsByUser_Username(String username);
}
