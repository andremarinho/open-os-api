package com.openos.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.openos.api.model.Status;

public interface StatusRepository extends JpaRepository<Status, Long> {

}
