package com.helloworld;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

/**
 * 
 * @author Isaac-1-lang
 * @version 1.0
 * @since 2026-01-08
 */
public class UserStore {
  // Thread-safe in-memory store
  private static final Map<String,User> users=new ConcurrentHashMap<>();

  public static boolean addUser(User user) {
    if(users.containsKey(user.getUsername())) return false;
    users.put(user.getUsername(),user);
    return true;
  }

  public static User getUser(String username) {
    return users.get(username);
  }

}