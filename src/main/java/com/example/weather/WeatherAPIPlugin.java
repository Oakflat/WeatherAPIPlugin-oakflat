package com.example.weather;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class WeatherAPIPlugin extends JavaPlugin {
    private HttpServer server;

    @Override
    public void onEnable() {
        getLogger().info("WeatherAPIPlugin has been enabled!");

        try {
            // 启动 HTTP 服务器，监听端口 8881
            server = HttpServer.create(new InetSocketAddress(8881), 0);
            server.createContext("/weather", new WeatherHandler());
            server.setExecutor(null);
            server.start();
            getLogger().info("HTTP server is running on port 8881");
        } catch (IOException e) {
            getLogger().severe("Failed to start HTTP server: " + e.getMessage());
        }
    }

    @Override
    public void onDisable() {
        if (server != null) {
            server.stop(0);
            getLogger().info("HTTP server has been stopped.");
        }
        getLogger().info("WeatherAPIPlugin has been disabled!");
    }

    /**
     * 模拟季节计算算法，根据 Minecraft 世界时间确定当前季节。
     */
    private String calculateSeason(long worldTime) {
        int days = (int) (worldTime / 24000); // 将时间转换为天数
        int seasonIndex = (days / 90) % 4; // 每 90 天为一个季节，共四季

        switch (seasonIndex) {
            case 0:
                return "Spring";
            case 1:
                return "Summer";
            case 2:
                return "Autumn";
            case 3:
                return "Winter";
            default:
                return "Unknown";
        }
    }

    /**
     * 处理 HTTP 请求的内部类，返回 JSON 格式的天气和季节信息。
     */
    class WeatherHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            World world = Bukkit.getWorld("world");
            if (world != null) {
                long worldTime = world.getTime();
                boolean isRaining = world.hasStorm();
                boolean isThundering = world.isThundering();
                String currentSeason = calculateSeason(worldTime);

                // 构建 JSON 响应
                String response = "{ \"isRaining\": " + isRaining + ", \"isThundering\": " + isThundering + ", \"currentSeason\": \"" + currentSeason + "\" }";

                exchange.getResponseHeaders().set("Content-Type", "application/json");
                exchange.sendResponseHeaders(200, response.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            } else {
                String response = "{ \"error\": \"World 'world' does not exist or is not loaded.\" }";
                exchange.sendResponseHeaders(500, response.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
        }
    }
}
