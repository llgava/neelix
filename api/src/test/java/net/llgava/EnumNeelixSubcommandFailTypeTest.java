package net.llgava;

import net.llgava.utils.NeelixSubcommandFailType;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class EnumNeelixSubcommandFailTypeTest {

  @Test
  public void testEnum() {
    assertNotNull(NeelixSubcommandFailType.SUBCOMMAND_NOT_FOUND);
    assertNotNull(NeelixSubcommandFailType.WITHOUT_PERMISSION_FOR_SUBCOMMAND);
  }
}
