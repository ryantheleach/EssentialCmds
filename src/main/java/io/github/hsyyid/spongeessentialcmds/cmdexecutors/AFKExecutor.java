package io.github.hsyyid.spongeessentialcmds.cmdexecutors;

import org.spongepowered.api.entity.player.Player;
import org.spongepowered.api.text.Texts;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.util.command.CommandException;
import org.spongepowered.api.util.command.CommandResult;
import org.spongepowered.api.util.command.CommandSource;
import org.spongepowered.api.util.command.args.CommandContext;
import org.spongepowered.api.util.command.source.CommandBlockSource;
import org.spongepowered.api.util.command.source.ConsoleSource;
import org.spongepowered.api.util.command.spec.CommandExecutor;

import io.github.hsyyid.spongeessentialcmds.Main;
import io.github.hsyyid.spongeessentialcmds.utils.AFK;
import io.github.hsyyid.spongeessentialcmds.utils.Utils;

public class AFKExecutor implements CommandExecutor
{
	public CommandResult execute(CommandSource src, CommandContext ctx) throws CommandException
	{
		if(src instanceof Player)
		{
			Player player = (Player) src;
			if(Main.movementList.contains(player))
			{
				for(AFK afk : Main.movementList)
				{
					if(afk.getPlayer() == player)
					{
						afk.lastMovementTime = -1000000000;
					}
				}
			}
			else
			{
				int afkTime = (int) Utils.getAFK();
				long afkTimer = afkTime + 1000;
				AFK afk = new AFK(player, afkTimer);
				Main.movementList.add(afk);
			}
		}
		else if(src instanceof ConsoleSource) {
			src.sendMessage(Texts.of(TextColors.DARK_RED,"Error! ", TextColors.RED, "Must be an in-game player to use /afk!"));
		}
		else if(src instanceof CommandBlockSource) {
			src.sendMessage(Texts.of(TextColors.DARK_RED,"Error! ", TextColors.RED, "Must be an in-game player to use /afk!"));
		}
		return CommandResult.success();
	}
}
