package top.catnies.itemframecut;

import org.bukkit.plugin.java.JavaPlugin;

public final class ItemFrameCut extends JavaPlugin {

    public static ItemFrameCut instance;
    @Override
    public void onEnable() {
        // 记录插件实例
        instance = this;
        // 注册事件监听器
        getServer().getPluginManager().registerEvents(new Hide(), this);
        // 打印插件信息
        this.getLogger().info("[ItemFrameCut] 插件已启动!");
        this.getLogger().info("[ItemFrameCut] 插件作者: Catnies, 版本: 1.0.0, 来源：那谁的资源仓库~");
    }
}
