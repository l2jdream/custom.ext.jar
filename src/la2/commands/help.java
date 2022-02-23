package la2.commands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import l2.gameserver.Config;
import l2.gameserver.handler.voicecommands.IVoicedCommandHandler;
import l2.gameserver.handler.voicecommands.VoicedCommandHandler;
import l2.gameserver.model.Player;
import l2.gameserver.scripts.ScriptFile;
import la2.La2Config;

public class help implements ScriptFile, IVoicedCommandHandler {

	private static final Logger LOG = LoggerFactory.getLogger(help.class);
	private static final help INSTANCE = new help();
	private final String[] _COMAND_LIST = { "help" };

	public static help getInstance() {
		return INSTANCE;
	}

	@Override
	public String[] getVoicedCommandList() {
		return _COMAND_LIST;
	}

	@Override
	public boolean useVoicedCommand(String command, Player player, String arg2) {
		if (command.equalsIgnoreCase(_COMAND_LIST[0]) && La2Config.ACTIVE_HELP_SYSTEM) {

			player.sendMessage("<============== HELP =============>");
			player.sendMessage(".menu : for enables and disable options");
			player.sendMessage(".repair : for repair char, teleport in 5 minutes!");
			player.sendMessage(".time : Current server time!");
			player.sendMessage(".password : User password change!");
			if (Config.SERVICES_CPACP_ENABLE || Config.SERVICES_HPACP_ENABLE || Config.SERVICES_MPACP_ENABLE) {
				player.sendMessage(".acp : for Auto Potions");
			}

			if (Config.SERVICES_OFFLINE_TRADE_ALLOW) {
				player.sendMessage(".offline : Offline shop");
			}

			if (Config.SERVICES_BANKING_ENABLED) {
				player.sendMessage(".deposit : turn adena into gold bar");
				player.sendMessage(".withdraw : turn gold bar into adena");
			}

			if (Config.SERVICES_CLAN_SUMMON_COMMAND_ENABLE && player.isClanLeader()) {
				player.sendMessage(".summon_clan : Clan Leader summon members");
			}

			player.sendMessage("<============== HELP =============>");
			return true;
		}
		return false;
	}

	@Override
	public void onLoad() {
		LOG.info("La2 scripts: Loaded success!");
		La2Config.load();
		VoicedCommandHandler.getInstance().registerVoicedCommandHandler(this); // registre commands.
	}

	@Override
	public void onReload() {
		// TODO Auto-generated method stub
		onLoad();
	}

	@Override
	public void onShutdown() {
		// TODO Auto-generated method stub

	}
}
