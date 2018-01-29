package com.ef;

import util.Cli;

public class Parser {
  public static void main(String[] args) {

    Cli cli = new Cli(args);
    cli.parse();

  }
}