package org.midasvision.exceptions;

public class DBIntegrityException extends RuntimeException{

  public DBIntegrityException(String msg) {
    super(msg);
  }
}
