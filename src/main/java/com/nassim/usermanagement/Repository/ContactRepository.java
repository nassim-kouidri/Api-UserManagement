package com.nassim.usermanagement.Repository;

import com.nassim.usermanagement.Model.ContactModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<ContactModel, Long> {
}
