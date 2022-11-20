package bdd.demo.appjava.role;

import bdd.demo.appjava.base.BaseController;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static bdd.demo.appjava.role.Constants.*;

@Tag(name = TAG_ROLES, description = DESC_ROLES_API)
@RestController
@RequestMapping(path = APIPATH_ROLES)
@Slf4j
public class RoleController extends BaseController<Role, Long, RoleRepository> {
}