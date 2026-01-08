package com.helloWorld;

/**
 * 
 * @author Isaac-1-lang
 * @version 1.0
 * @since 2026-01-08
 */
public class User {
  private String username;
  private String password;

  /**
   * 
   * @param username for username of the person
   * @param password for password of the person
   */
  public User(String username,String password) {
    this.username=username;
    this.password=password;
  }

  /**
   * 
   * @return username of the person
   */
  public String getUsername() {
    return username;
  }
  /**
   * 
   * @return password of the person
   */
  public String getPassword() {
    return password;
  }
}