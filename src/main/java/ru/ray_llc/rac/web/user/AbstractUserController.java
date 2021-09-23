package ru.ray_llc.rac.web.user;

import static ru.ray_llc.rac.util.UserUtil.fromTo;
import static ru.ray_llc.rac.util.ValidationUtil.assureIdConsistent;
import static ru.ray_llc.rac.util.ValidationUtil.checkNew;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.ray_llc.rac.model.Task;
import ru.ray_llc.rac.model.User;
import ru.ray_llc.rac.service.TaskService;
import ru.ray_llc.rac.service.UserService;
import ru.ray_llc.rac.to.TaskTo;
import ru.ray_llc.rac.to.UserTo;
import ru.ray_llc.rac.util.UserUtil;
import ru.ray_llc.rac.web.SecurityUtil;

public abstract class AbstractUserController {

  protected final Logger log = LoggerFactory.getLogger(getClass());

  @Autowired
  private UserService service;

  @Autowired
  private TaskService serviceTo;

  public List<User> getAll() {
    log.info("getAll");
    return service.getAll();
  }

  public User get(int id) {
    log.info("get {}", id);
    return service.get(id);
  }

  public User create(UserTo userTo) {
    log.info("create {}", userTo);
    checkNew(userTo);
    return service.create(UserUtil.createNewFromTo(userTo));
  }

  public User create(User user) {
    log.info("create {}", user);
    checkNew(user);
    return service.create(user);
  }

  public void delete(int id) {
    log.info("delete {}", id);
    service.delete(id);
  }

  public void update(User user, int id) {
    log.info("update {} with id={}", user, id);
    assureIdConsistent(user, id);
    service.update(user);
  }

  public void update(UserTo userTo, int id) {
    log.info("update {} with id={}", userTo, id);
    assureIdConsistent(userTo, id);
    service.update(userTo);
  }

  public User getByMail(String email) {
    log.info("getByEmail {}", email);
    return service.getByEmail(email);
  }

  public void enable(int id, boolean enabled) {
    log.info(enabled ? "enable {}" : "disable {}", id);
    service.enable(id, enabled);
  }

  public Task create(TaskTo task) {
    int userId = SecurityUtil.authUserId();
    log.info("create {} for user {}", task, userId);
    checkNew(task);
    return serviceTo.create(fromTo(task));
  }

  public void update(TaskTo task, int id) {
    int userId = SecurityUtil.authUserId();
    log.info("update {} for user {}", task, userId);
    assureIdConsistent(task, id);
    serviceTo.update(fromTo(task));
  }
}