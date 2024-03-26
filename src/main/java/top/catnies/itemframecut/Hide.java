package top.catnies.itemframecut;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;

public class Hide implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEntityEvent event){
        // 如果玩家没有权限则直接返回
        if (!event.getPlayer().hasPermission("itemframecut.use")) {
            return;
        }

        // 获取右键点击的实体和玩家
        Entity entity = event.getRightClicked();
        Player player = event.getPlayer();

        // 判断是否为ItemFrame + 且玩家是否在潜行状态 + 玩家是否在生存模式,不是则直接返回
        if (!player.isSneaking() || !player.getGameMode().equals(GameMode.SURVIVAL) || !entity.getType().equals(EntityType.ITEM_FRAME)){
            return;
        }

        // 转换为ItemFrame
        ItemFrame itemFrame = (ItemFrame) entity;

        // 展示框为可见状态 + 玩家手里有剪刀 则触发剪刀隐藏展示框
        ItemStack itemInHand = player.getInventory().getItemInHand();
        if (itemFrame.isVisible() && itemInHand.getType() == Material.SHEARS){

            // 检查手上的物品是否可被破坏,可以就减少耐久度。
            if(itemInHand.getItemMeta() instanceof Damageable){
                Damageable damageable = (Damageable)itemInHand.getItemMeta();
                damageable.setDamage(damageable.getDamage() + 1);
                itemInHand.setItemMeta(damageable);
            }

            // 展示框设置为不可见
            itemFrame.setVisible(false);
            // 取消事件
            event.setCancelled(true);
        }

    }
}
