package jp.jyn.jecon.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import jp.jyn.jecon.Jecon;
import jp.jyn.jecon.config.ConfigStruct;
import jp.jyn.jecon.db.Database;

public class Login implements Listener {

	private final Database db;
	private final ConfigStruct config;

	public Login(Jecon jecon) {
		db = jecon.getDb();
		config = jecon.getConfigStruct();

		jecon.getServer().getPluginManager().registerEvents(this, jecon);
	}

	@EventHandler(ignoreCancelled = true)
	public void onJoin(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		if (config.isCreateAccountOnJoin() && !db.hasAccount(player)) {
			db.createPlayerAccount(player);
		} else {
			db.nameUpdate(player);
		}
	}
}
