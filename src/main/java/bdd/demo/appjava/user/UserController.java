package bdd.demo.appjava.user;

import bdd.demo.appjava.base.BaseController;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static bdd.demo.appjava.user.Constants.*;

@Tag(name = TAG_USERS, description = DESC_USERS_API)
@RestController
@RequestMapping(path = APIPATH_USERS)
@Slf4j
public class UserController extends BaseController<User, Long, UserRepository> {
}
