package com.words.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import com.words.entity.User;


@RestResource(exported = false)
public interface UserRepository extends JpaRepository<User, String> {

}
