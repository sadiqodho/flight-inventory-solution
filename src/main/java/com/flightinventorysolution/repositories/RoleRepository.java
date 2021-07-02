package com.flightinventorysolution.repositories;

import com.flightinventorysolution.models.Role;
import com.flightinventorysolution.types.RoleType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findByName(RoleType name);
}
