package bdd.demo.appjava.user;

import bdd.demo.appjava.base.BaseService;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseService<User, Long, UserRepository> {
}
