package tech.jplopes.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.jplopes.ecommerce.entities.BillingAddressEntity;

public interface BillingAddressRepository extends JpaRepository<BillingAddressEntity, Long> {
}
