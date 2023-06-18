package ru.clevertec.UserManager.common.service;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.clevertec.UserManager.common.extension.user.ValidParameterResolverUser;
import ru.clevertec.UserManager.dto.UserRequestDto;
import ru.clevertec.UserManager.entity.Role;
import ru.clevertec.UserManager.repository.RoleRepository;
import ru.clevertec.UserManager.service.role.RoleApiService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static ru.clevertec.UserManager.common.utill.UserBuild.getRole;

@DisplayName("Role Service Test")
public class RoleServiceImplTest {

    @InjectMocks
    private RoleApiService roleApiService;


    @Mock
    private RoleRepository roleRepository;


    @Nested
    @ExtendWith({MockitoExtension.class, ValidParameterResolverUser.class})
    public class ValidData {

        @Test
        void shouldFindByNameUserWhenUserValid(UserRequestDto userDto) {
            Role role = getRole();
            when(roleRepository.findRoleByName(userDto.getRole()))
                    .thenReturn(role);
            assertEquals(role, roleApiService.findRoleByName(userDto.getRole()));
            verify(roleRepository, times(1)).findRoleByName(userDto.getRole());
        }

    }

    @Nested
    @ExtendWith({MockitoExtension.class, ValidParameterResolverUser.class})
    public class InvalidData {

        @InjectMocks
        private RoleApiService roleApiService;


        @Mock
        private RoleRepository roleRepository;

        @Test
        void shouldFindByNameUserWhenUserValid(UserRequestDto userDto) {
            when(roleRepository.findRoleByName(userDto.getRole()))
                    .thenReturn(null);
            assertThrows(IllegalArgumentException.class,
                    () -> roleApiService.findRoleByName(userDto.getRole()));
        }

    }
}