package tech.jplopes.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.jplopes.ecommerce.entities.UserEntity;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
}
