package net.llgava;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.JavaPluginLoader;

import java.io.File;
import java.util.logging.Logger;

/**
 * <a href="https://github.com/llgava/neelix">Source code</a> <br />
 * Authors: <br />
 * ㅤ- Leonardo Luiz Gava | <a href="https://twitter.com/llgava">Twitter</a> | <a href="https://github.com/llgava">GitHub</a>
 * */
public class Neelix extends JavaPlugin {
  public static final Logger LOGGER = Bukkit.getServer().getLogger();

  /**
   * <b>UNIT TESTING ONLY</b><br />
   * This constructor is used only for unit tests
   * and should not be used in production.
   */
  public Neelix() { super(); }

  /**
   * <b>UNIT TESTING ONLY</b><br />
   * This constructor is used only for unit tests
   * and should not be used in production.
   */
  protected Neelix(JavaPluginLoader l, PluginDescriptionFile d, File dF, File f) {
    super(l, d, dF, f);
  }

  @Override
  public void onEnable() { /* Nothing to do here :) */ }
}
