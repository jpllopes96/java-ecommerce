package tech.jplopes.ecommerce.services;

import org.springframework.stereotype.Service;
import tech.jplopes.ecommerce.controller.dto.CreateUserDto;
import tech.jplopes.ecommerce.entities.BillingAddressEntity;
import tech.jplopes.ecommerce.entities.UserEntity;
import tech.jplopes.ecommerce.repository.BillingAddressRepository;
import tech.jplopes.ecommerce.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final BillingAddressRepository billingAddressRepository;

    public UserService(UserRepository userRepository, BillingAddressRepository billingAddressRepository) {
        this.userRepository = userRepository;
        this.billingAddressRepository = billingAddressRepository;
    }

    public UserEntity createUser(CreateUserDto dto){

        var billingAddress = new BillingAddressEntity();
        billingAddress.setAddress(dto.address());
        billingAddress.setNumber(dto.number());
        billingAddress.setComplement(dto.complement());

        var savedBillingAddress = billingAddressRepository.save(billingAddress);

        var user = new UserEntity();
        user.setFullName(dto.fullName());
        user.setBillingAddress(savedBillingAddress);

        return userRepository.save(user);

    }
}
