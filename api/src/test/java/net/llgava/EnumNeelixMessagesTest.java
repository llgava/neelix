package net.llgava;

import net.llgava.utils.NeelixMessages;
import org.junit.Test;

import static org.junit.Assert.*;

public class EnumNeelixMessagesTest {

  @Test
  public void testGetByIndex() {
    NeelixMessages message = NeelixMessages.findByIndex(0);
    assertEquals(message, NeelixMessages.INVALID_FILE_EXTENSION);
  }

  @Test
  public void testGetMessage() {
    NeelixMessages message = NeelixMessages.INVALID_FILE_EXTENSION;
    assertEquals(message.getMessage(), "The file doesn't have a valid extension. Please, use {1}.");
  }
}
