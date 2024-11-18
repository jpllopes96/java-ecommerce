package tech.jplopes.ecommerce.services;

import org.springframework.stereotype.Service;
import tech.jplopes.ecommerce.controller.dto.CreateUserDto;
import tech.jplopes.ecommerce.entities.BillingAddressEntity;
import tech.jplopes.ecommerce.entities.UserEntity;
import tech.jplopes.ecommerce.repository.BillingAddressRepository;
import tech.jplopes.ecommerce.repository.UserRepository;

import java.util.Optional;
import java.util.UUID;

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
      //  We are using cascade All, so we don't need to do it, cascade will do it
     //   var savedBillingAddress = billingAddressRepository.save(billingAddress);

        var user = new UserEntity();
        user.setFullName(dto.fullName());
        user.setBillingAddress(billingAddress);

        return userRepository.save(user);

    }

    public Optional<UserEntity> findById(UUID userId) {

        return userRepository.findById(userId);
    }

    public Boolean deleteById(UUID userId) {
        var user = userRepository.findById(userId);
        if(user.isPresent()){
            userRepository.deleteById(userId);
           // Cascade all we dont need to delete the BillingAddess, cascade will do it.
            // billingAddressRepository.deleteById(user.get().getBillingAddress().getBillingAddressId());
        }
        return user.isPresent();

    }
}
