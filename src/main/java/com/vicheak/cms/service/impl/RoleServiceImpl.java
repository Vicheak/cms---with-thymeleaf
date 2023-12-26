package com.vicheak.cms.service.impl;

import com.vicheak.cms.model.Role;
import com.vicheak.cms.repository.RoleRepository;
import com.vicheak.cms.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public List<Role> findRoles() {
        return roleRepository.select();
    }
}
