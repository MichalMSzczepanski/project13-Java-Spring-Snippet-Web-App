package work.szczepanskimichal.project13snippetapp.user;

import org.mapstruct.Mapper;
import work.szczepanskimichal.project13snippetapp.user.DTO.AdminUpdateUserDTO;
import work.szczepanskimichal.project13snippetapp.user.DTO.CreateUserDTO;

@Mapper
public interface UserMapper {
    User createUserDTOtoUser(CreateUserDTO createUserDTO);
    User AdminUpdateUserDTOtoUser(AdminUpdateUserDTO adminUpdateUserDTO);
    User CreateUserDTOtoUser(CreateUserDTO createUserDTO);

    AdminUpdateUserDTO UsertoAdminUpdateUserDTO(User user);

}
