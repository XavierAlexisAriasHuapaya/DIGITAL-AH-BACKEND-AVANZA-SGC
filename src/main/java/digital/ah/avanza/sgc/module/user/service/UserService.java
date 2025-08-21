package digital.ah.avanza.sgc.module.user.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import digital.ah.avanza.sgc.module.branch.entity.BranchEntity;
import digital.ah.avanza.sgc.module.branch.service.BranchService;
import digital.ah.avanza.sgc.module.rol.dto.RolOneDTO;
import digital.ah.avanza.sgc.module.rol.entity.RolEntity;
import digital.ah.avanza.sgc.module.rol.service.RolService;
import digital.ah.avanza.sgc.module.user.dto.UserCreateDTO;
import digital.ah.avanza.sgc.module.user.dto.UserOneDTO;
import digital.ah.avanza.sgc.module.user.dto.UserUpdateDTO;
import digital.ah.avanza.sgc.module.user.entity.UserEntity;
import digital.ah.avanza.sgc.module.user.interfaces.UserImplement;
import digital.ah.avanza.sgc.module.user.repository.UserRepository;
import digital.ah.avanza.sgc.utils.exception.MessageException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserService implements UserImplement {

    private final UserRepository userRepository;

    private final RolService rolService;

    private final BranchService branchService;

    @Transactional
    @Override
    public UserEntity create(UserCreateDTO user) {
        RolOneDTO rolDTO = this.rolService.findOne(user.getRol());
        RolEntity rolEntity = RolEntity.builder()
                .id(rolDTO.getId())
                .build();

        Set<BranchEntity> branches = new HashSet<>();
        if (user.getBranches() != null && !user.getBranches().isEmpty()) {
            branches = this.branchService.findAllByIds(user.getBranches())
                    .stream().collect(Collectors.toSet());
        }

        UserEntity userEntity = UserEntity.builder()
                .rol(rolEntity)
                .username(user.getUsername())
                .password(user.getPassword())
                .branches(branches)
                .build();

        userEntity = this.userRepository.save(userEntity);
        return userEntity;
    }

    @Transactional
    @Override
    public UserEntity update(UserUpdateDTO user) {
        RolOneDTO rolDTO = this.rolService.findOne(user.getRol());
        RolEntity rolEntity = RolEntity.builder()
                .id(rolDTO.getId())
                .build();

        Boolean userExists = this.userRepository.findById(user.getId()).isPresent();

        if (!userExists) {
            throw new MessageException("User not found");
        }

        Set<BranchEntity> branches = new HashSet<>();
        if (user.getBranches() != null && !user.getBranches().isEmpty()) {
            branches = this.branchService.findAllByIds(user.getBranches())
                    .stream().collect(Collectors.toSet());
        }

        UserEntity userEntity = UserEntity.builder()
                .id(user.getId())
                .rol(rolEntity)
                .username(user.getUsername())
                .password(user.getPassword())
                .branches(branches)
                .build();

        return this.userRepository.save(userEntity);
    }

    @Transactional(readOnly = true)
    @Override
    public UserOneDTO findOne(Long id) {
        Optional<UserEntity> userOpt = this.userRepository.findById(id);

        if (userOpt.isEmpty()) {
            throw new MessageException("User not found");
        }

        UserOneDTO dto = new UserOneDTO(userOpt.get());
        return dto;
    }

}
