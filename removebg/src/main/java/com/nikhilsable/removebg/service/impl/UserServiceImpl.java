package com.nikhilsable.removebg.service.impl;

import com.nikhilsable.removebg.dto.UserDTO;
import com.nikhilsable.removebg.entity.UserEntity;
import com.nikhilsable.removebg.repository.UserRepository;
import com.nikhilsable.removebg.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDTO saveUser(UserDTO userDTO) {

        Optional<UserEntity> optionalUser =
                userRepository.findByClerkId(userDTO.getClerkId());

        // ✅ If user already exists → update
        if (optionalUser.isPresent()) {
            UserEntity existingUser = optionalUser.get();

            existingUser.setEmail(userDTO.getEmail());
            existingUser.setFirstName(userDTO.getFirstName());
            existingUser.setLastName(userDTO.getLastName());
            existingUser.setPhotoUrl(userDTO.getPhotoUrl());

            // update credits only if provided
            if (userDTO.getCredits() != null) {
                existingUser.setCredits(userDTO.getCredits());
            }

            UserEntity savedUser = userRepository.save(existingUser);
            return mapToDTO(savedUser);
        }

        // ✅ If new user → create
        UserEntity newUser = mapToEntity(userDTO);
        UserEntity savedUser = userRepository.save(newUser);
        return mapToDTO(savedUser);
    }

    // 🔁 DTO → Entity
    private UserEntity mapToEntity(UserDTO userDTO) {
        return UserEntity.builder()
                .clerkId(userDTO.getClerkId())
                .email(userDTO.getEmail())
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .photoUrl(userDTO.getPhotoUrl())
                .credits(
                        userDTO.getCredits() != null
                                ? userDTO.getCredits()
                                : 0   // default credits for new user
                )
                .build();
    }

    // 🔁 Entity → DTO
    private UserDTO mapToDTO(UserEntity userEntity) {
        return UserDTO.builder()
                .clerkId(userEntity.getClerkId())
                .email(userEntity.getEmail())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .photoUrl(userEntity.getPhotoUrl())
                .credits(userEntity.getCredits())
                .build();
    }
}
