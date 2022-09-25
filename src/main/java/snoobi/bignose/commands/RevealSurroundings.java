package snoobi.bignose.commands;

import emu.grasscutter.command.Command;
import emu.grasscutter.command.CommandHandler;
import emu.grasscutter.data.GameData;
import emu.grasscutter.game.entity.EntityNPC;
import emu.grasscutter.game.entity.EntityGadget;
import emu.grasscutter.game.entity.EntityAvatar;
import emu.grasscutter.game.entity.EntityClientGadget;
import emu.grasscutter.game.entity.EntityItem;
import emu.grasscutter.game.entity.EntityMonster;
import emu.grasscutter.game.entity.EntityVehicle;
import emu.grasscutter.game.entity.EntityRegion;
import emu.grasscutter.game.entity.GameEntity;
import emu.grasscutter.game.player.Player;
import emu.grasscutter.game.world.Scene;
import emu.grasscutter.utils.Position;

import java.util.*;

@Command(label = "reveal", usage = "/r [itemTYPE]", aliases = {"r","show"}, permission = "player.give", permissionTargeted = "player.give.others", targetRequirement = Command.TargetRequirement.NONE)
public final class RevealSurroundings implements CommandHandler {
	@Override
	public void execute(Player sender, Player targetPlayer, List<String> args) {
		// Sanity check
		if (targetPlayer == null) {
			targetPlayer = sender;
		}

		Scene scene = targetPlayer.getScene();
		String type = "";
		float radius = 999f;  //radius of lookup frm self
		
        try {
            switch (args.size()) {
				case 2:
					radius = Float.parseFloat(args.get(1));
                case 1: // [type]
					type = args.get(0).toLowerCase();
                    // = targetPlayer.getWorld().getSceneById(Integer.parseInt(args.get(0)));
                    break;
				case 0: // *No args*
                    break;
                default:
                    sendUsageMessage(sender);
                    return;
			}
        } catch (NumberFormatException ignored) {
            CommandHandler.sendMessage(sender, "invalid parems ew");
        }
		
        if (scene == null) {
            CommandHandler.sendMessage(sender, "not in any scene -_-");
            return;
        }

        // Separate into list to avoid concurrency issue
        final Scene sceneF = scene;
		Position refPos = targetPlayer.getPosition();
		List<GameEntity> allEntities = new ArrayList<GameEntity>();
		List<EntityGadget> gadgetEntities = new ArrayList<EntityGadget>();
		List<EntityAvatar> avatarEntities = new ArrayList<EntityAvatar>();
		List<EntityClientGadget> clientGadgetEntities = new ArrayList<EntityClientGadget>();
		List<EntityItem> itemEntities = new ArrayList<EntityItem>();
		List<EntityMonster> monsterEntities = new ArrayList<EntityMonster>();
		List<EntityNPC> NPCEntities = new ArrayList<EntityNPC>();
		List<EntityRegion> regionEntities = new ArrayList<EntityRegion>();
		List<EntityVehicle> vehicleEntities = new ArrayList<EntityVehicle>();
		
		if (type.equals("gadget") || type.equals("g") || type.equals("gt")) {
			allEntities = sceneF.getEntities().values().stream().filter(entity -> entity instanceof EntityGadget).toList();
			for (GameEntity entity : allEntities) {
				if (Math.abs(((EntityGadget) entity).getPosition().getX() - refPos.getX()) < radius && Math.abs(((EntityGadget) entity).getPosition().getY() - refPos.getY()) < radius && Math.abs(((EntityGadget) entity).getPosition().getZ() - refPos.getZ()) < radius) {
					gadgetEntities.add((EntityGadget) entity);
				}
			}
		} else if (type.equals("avatar") || type.equals("a") || type.equals("ava")) {
			allEntities = sceneF.getEntities().values().stream().filter(entity -> entity instanceof EntityAvatar).toList();
			for (GameEntity entity : allEntities) {
				if (Math.abs(((EntityAvatar) entity).getPosition().getX() - refPos.getX()) < radius && Math.abs(((EntityAvatar) entity).getPosition().getY() - refPos.getY()) < radius && Math.abs(((EntityAvatar) entity).getPosition().getZ() - refPos.getZ()) < radius) {
					avatarEntities.add((EntityAvatar) entity);
				}
			}
		} else if (type.equals("clientgadget") || type.equals("cg") || type.equals("cgadget")) {
			allEntities = sceneF.getEntities().values().stream().filter(entity -> entity instanceof EntityClientGadget).toList();
			for (GameEntity entity : allEntities) {
				if (Math.abs(((EntityClientGadget) entity).getPosition().getX() - refPos.getX()) < radius && Math.abs(((EntityClientGadget) entity).getPosition().getY() - refPos.getY()) < radius && Math.abs(((EntityClientGadget) entity).getPosition().getZ() - refPos.getZ()) < radius) {
					clientGadgetEntities.add((EntityClientGadget) entity);
				}
			}
		} else if (type.equals("item") || type.equals("i") || type.equals("itm")) {
			allEntities = sceneF.getEntities().values().stream().filter(entity -> entity instanceof EntityItem).toList();
			for (GameEntity entity : allEntities) {
				if (Math.abs(((EntityItem) entity).getPosition().getX() - refPos.getX()) < radius && Math.abs(((EntityItem) entity).getPosition().getY() - refPos.getY()) < radius && Math.abs(((EntityItem) entity).getPosition().getZ() - refPos.getZ()) < radius) {
					itemEntities.add((EntityItem) entity);
				}
			}
		} else if (type.equals("monster") || type.equals("mons") || type.equals("m")) {
			allEntities = sceneF.getEntities().values().stream().filter(entity -> entity instanceof EntityMonster).toList();
			for (GameEntity entity : allEntities) {
				if (Math.abs(((EntityMonster) entity).getPosition().getX() - refPos.getX()) < radius && Math.abs(((EntityMonster) entity).getPosition().getY() - refPos.getY()) < radius && Math.abs(((EntityMonster) entity).getPosition().getZ() - refPos.getZ()) < radius) {
					monsterEntities.add((EntityMonster) entity);
				}
			}
		} else if (type.equals("npc")) {
			allEntities = sceneF.getEntities().values().stream().filter(entity -> entity instanceof EntityNPC).toList();
			for (GameEntity entity : allEntities) {
				if (Math.abs(((EntityNPC) entity).getPosition().getX() - refPos.getX()) < radius && Math.abs(((EntityNPC) entity).getPosition().getY() - refPos.getY()) < radius && Math.abs(((EntityNPC) entity).getPosition().getZ() - refPos.getZ()) < radius) {
					NPCEntities.add((EntityNPC) entity);
				}
			}
		} else if (type.equals("region") || type.equals("r") || type.equals("reg")) {
			allEntities = sceneF.getEntities().values().stream().filter(entity -> entity instanceof EntityRegion).toList();
			for (GameEntity entity : allEntities) {
				if (Math.abs(((EntityRegion) entity).getPosition().getX() - refPos.getX()) < radius && Math.abs(((EntityRegion) entity).getPosition().getY() - refPos.getY()) < radius && Math.abs(((EntityRegion) entity).getPosition().getZ() - refPos.getZ()) < radius) {
					regionEntities.add((EntityRegion) entity);
				}
			}
		} else if (type.equals("vehicle") || type.equals("v") || type.equals("vhc")) {
			allEntities = sceneF.getEntities().values().stream().filter(entity -> entity instanceof EntityVehicle).toList();
			for (GameEntity entity : allEntities) {
				if (Math.abs(((EntityVehicle) entity).getPosition().getX() - refPos.getX()) < radius && Math.abs(((EntityVehicle) entity).getPosition().getY() - refPos.getY()) < radius && Math.abs(((EntityVehicle) entity).getPosition().getZ() - refPos.getZ()) < radius) {
					vehicleEntities.add((EntityVehicle) entity);
				}
			}
		} else if (type.equals("all")) {
			allEntities = sceneF.getEntities().values().stream()
					.filter(entity -> entity instanceof GameEntity)
					.toList();
			/*for (GameEntity entity : allEntities) {
				if (Math.abs(((EntityVehicle) entity).getPosition().getX() - refPos.getX()) < radius && Math.abs(((EntityVehicle) entity).getPosition().getY() - refPos.getY()) < radius && Math.abs(((EntityVehicle) entity).getPosition().getZ() - refPos.getZ()) < radius) {
					vehicleEntities.add((EntityVehicle) entity);
				}
			}*/
		} else {
			CommandHandler.sendMessage(sender,"this parem does not exist");
			return;
		}
        
		//format response
		String response = "";
		if (gadgetEntities.size() != 0) {
			for (EntityGadget entity : gadgetEntities) {
				response = "GadgetEntityId = " + entity.getId()
				            + "\n\nGadget Info:"
						    + "\nGadget Id = " + entity.getGadgetId()
							+ "\nGadget Position = " + entity.getPosition()
							+ "\nGadget State = " + entity.getState()
							+ "\n\nGadget Data:"
						    + "\nGadget Name = " + GameData.getGadgetDataMap().get(entity.getGadgetId()).getJsonName()
						    + "\nGadget Item Name = " + GameData.getGadgetDataMap().get(entity.getGadgetId()).getItemJsonName()
						    + "\nGadget Tags = " + GameData.getGadgetDataMap().get(entity.getGadgetId()).getTags()
						    + "\nGadget Entity Type = " + GameData.getGadgetDataMap().get(entity.getGadgetId()).getType();
				CommandHandler.sendMessage(sender, response);
				response = "";
			}
		}
		if (avatarEntities.size() != 0) {
			for (EntityAvatar entity : avatarEntities) {
				response = "AvatarEntityId = " + entity.getId()
						    + "\n\nAvatar Info: "
						    + "\nAvatar Owner UID = @" + entity.getAvatar().getOwnerId()
						    + "\nAvatar Id = " + entity.getAvatar().getLevel()
						    + "\nAvatar CurrentHP = " + entity.getAvatar().getCurrentHp()
						    + "\nAvatar Windglider Id = " + entity.getAvatar().getFlyCloak()
						    + "\nAvatar Costume Id = " + entity.getAvatar().getCostume()
						    + "\nAvatar Friendship Level = " + entity.getAvatar().getFetterLevel()
						    + "\nAvatar Fight Properties = " + entity.getAvatar().getFightProperties()
						    + "\n\nWeapon Info:"
						    + "\nWeapon ItemId= " + entity.getAvatar().getWeapon().getItemId()
						    + "\nWeapon Level = " + entity.getAvatar().getWeapon().getLevel()
						    + "\nWeapon Substats" + entity.getAvatar().getWeapon().getAffixes()
						    + "\nWeapon Refinement" + entity.getAvatar().getWeapon().getRefinement();
				CommandHandler.sendMessage(sender, response);
				response = "";
			}
		}
		if (clientGadgetEntities.size() != 0) {
			for (EntityClientGadget entity : clientGadgetEntities) {
				response = "ClientGadgetEntityId = " + entity.getId()
						  + "\n\nClientGadget Info"
						  + "\nClientGadget Owner UID = @" + entity.getOwner().getUid()
						  + "\nClientGadget Id = " + entity.getGadgetId()
						  + "\nClientGadget Position = " + entity.getPosition()
						  + "\nClientGadget Owner Entity Id = " + entity.getOwnerEntityId()
						  + "\nClientGadget Target Entity Id = " + entity.getTargetEntityId();
				CommandHandler.sendMessage(sender, response);
				response = "";
			}
		}
		if (itemEntities.size() != 0) {
			for (EntityItem entity : itemEntities) {
				response = "ItemEntityId = " + entity.getId()
						  + "\n\nItem Info:"
						  + "\nItem ID = " + entity.getId()
						  + "\nItem Gadget Id = " + entity.getGadgetId()
						  + "\nItem Count = " + entity.getCount()
						  + "\nItem Position = " + entity.getPosition();
				CommandHandler.sendMessage(sender, response);
				response = "";
			}
		}
		if (monsterEntities.size() != 0) {
			for (EntityMonster entity : monsterEntities) {
				response = "MonsterEntityId = " + entity.getId()
					      + "\n\nMonster Info:"
						  + "\nMonster Name = " + entity.getMonsterData().getMonsterName()
						  + "\nMonster ID = " + entity.getMonsterData().getId()
						  + "\nMonster Level = " + entity.getLevel()
						  + "\n\nMonster DATA:"
						  + "\nMonster HP = " + entity.getMonsterData().getBaseHp()
						  + "\nMonster ATK = " + entity.getMonsterData().getBaseAttack()
						  + "\nMonster DEF = " + entity.getMonsterData().getBaseDefense()
						  + "\nMonster AI ID = " + entity.getMonsterData().getAi()
						  + "\nMonster Weapon Gadget Id = " + entity.getMonsterWeaponId()
						  + "\nMonster KillDropID = " + entity.getMonsterData().getKillDropId();
				CommandHandler.sendMessage(sender, response);
				response = "";			  
			}
		}
		if (NPCEntities.size() != 0) {
			for (EntityNPC entity : NPCEntities) {
				response = "NPCEntityId = " + entity.getId()
						  + "\n\nNPC Info:"
						  + "\nNPC Position = " + entity.getPosition()
						  + "\nNPC Suite ID = " + entity.getSuiteId();
				CommandHandler.sendMessage(sender, response);
				response = "";
			}
		}
		if (regionEntities.size() != 0) {
			for (EntityRegion entity : regionEntities) {
				response = "RegionEntityId = " +entity.getId()
						  + "\n\nRegion Info:"
						  + "\nRegion Position = " + entity.getRotation();
				CommandHandler.sendMessage(sender, response);
				response = "";
			}
		}
		if (vehicleEntities.size() != 0) {
			for (EntityVehicle entity : vehicleEntities) {
				response = "VehicleEntityId = " + entity.getId()
						  + "\n\nVehicle Info:"
						  + "\nVehicle Gadget Id = " + entity.getGadgetId()
						  + "\nVehicle Position = " + entity.getPosition()
					      + "\n\nVehicle Data:"
						  + "\nVehicle Gadget ID = " + GameData.getGadgetDataMap().get(entity.getGadgetId()).getId()
						  + "\nVehicle Name = " + GameData.getGadgetDataMap().get(entity.getGadgetId()).getJsonName()
						  + "\nVehicle Item Name = " + GameData.getGadgetDataMap().get(entity.getGadgetId()).getItemJsonName()
						  + "\nVehicle Tags = " + GameData.getGadgetDataMap().get(entity.getGadgetId()).getTags()
						  + "\nVehicle Entity Type = " + GameData.getGadgetDataMap().get(entity.getGadgetId()).getType();
				CommandHandler.sendMessage(sender, response);
				response = "";
			}
		}
		CommandHandler.sendMessage(sender, "this all func later then i implement lol");
	}
}

