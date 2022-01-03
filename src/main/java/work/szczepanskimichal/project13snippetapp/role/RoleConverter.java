package work.szczepanskimichal.project13snippetapp.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

public class RoleConverter implements Converter<String, Role> {

    @Autowired
    private RoleService roleService;

    @Override
    public Role convert(String s) {
        return roleService.findByName(s);
    }
}

