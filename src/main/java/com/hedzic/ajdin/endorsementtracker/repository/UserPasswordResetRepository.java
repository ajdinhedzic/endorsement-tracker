package com.hedzic.ajdin.endorsementtracker.repository;

import com.hedzic.ajdin.endorsementtracker.entity.UserPasswordReset;
import org.springframework.data.repository.CrudRepository;

public interface UserPasswordResetRepository extends CrudRepository<UserPasswordReset, Long> {
}
