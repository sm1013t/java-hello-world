package com.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GreeterTest {

  private final Greeter greeter = new Greeter();

  @Test
  void greetReturnsTheExactGreeting() {
    assertEquals("Hello, World!", greeter.greet("World"));
  }

  @Test
  void greetPreservesSpacesAndPunctuationInTheName() {
    assertEquals("Hello, Ada Lovelace?!", greeter.greet("Ada Lovelace?"));
  }

  @Test
  void greetHandlesNullUsingItsStringRepresentation() {
    assertEquals("Hello, null!", greeter.greet(null));
  }
}
