package com.hedzic.ajdin.endorsementtracker.repository;

import com.hedzic.ajdin.endorsementtracker.entity.UserAccount;
import org.springframework.data.repository.CrudRepository;

public interface UserAccountRepository extends CrudRepository<UserAccount, Long> {
    public UserAccount findByEmail(String emailAddress);
}
