package com.nassim.usermanagement.Repository;

import com.nassim.usermanagement.Model.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StorageRepository extends JpaRepository<ImageData,Long> {


     Optional<ImageData> findByName(String fileName);
}
