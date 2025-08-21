package digital.ah.avanza.sgc.module.user.interfaces;

import digital.ah.avanza.sgc.module.user.dto.UserCreateDTO;
import digital.ah.avanza.sgc.module.user.dto.UserOneDTO;
import digital.ah.avanza.sgc.module.user.dto.UserUpdateDTO;
import digital.ah.avanza.sgc.module.user.entity.UserEntity;

public interface UserImplement {

    UserEntity create(UserCreateDTO user);

    UserEntity update(UserUpdateDTO user);

    UserOneDTO findOne(Long id);
}
