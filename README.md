# WeatherAPIPlugin

## 🌦️ Overview
**WeatherAPIPlugin** is a Minecraft server plugin that provides an HTTP API to query weather conditions and seasonal information in the Minecraft world. This plugin simulates seasonal calculations based on in-game time and provides a JSON response for easy integration into external tools or dashboards.

---

## 🚀 Features
- **HTTP Server**: Listens on port `8881` for incoming requests.
- **Weather Data**: Fetches the current weather status (`isRaining`, `isThundering`) of the world.
- **Season Simulation**: Calculates the current season based on Minecraft world time.
- **JSON Response**: Returns weather and season information in a standard JSON format.

---

## 📖 API Endpoint
### `/weather`
- **Method**: `GET`
- **Description**: Fetches weather and season information of the Minecraft world.
- **Response Example**:
  ```json
  {
    "isRaining": true,
    "isThundering": false,
    "currentSeason": "Summer"
  }
  ```
- **Error Example**:
  ```json
  {
    "error": "World 'world' does not exist or is not loaded."
  }
  ```

---

## 🛠️ Installation
1. **Download the Plugin**: Get the JAR file from the [Releases](#).
2. **Add to Plugins Folder**: Place the JAR file into your Minecraft server's `plugins` directory.
3. **Restart the Server**: Start or restart your server to activate the plugin.

---

## ⚙️ Configuration
- No additional configuration is required.
- The HTTP server runs on port `8881` by default.

---

## 📋 Dependencies
- **Bukkit API**: Used to interact with the Minecraft server.
- **Java HTTP Server**: Built-in Java HTTP server for lightweight HTTP handling.

---

## 📡 Usage
1. **Run Your Server**: Start your Minecraft server with the plugin installed.
2. **Access the API**: Visit:
   ```
   http://<server-ip>:8881/weather
   ```
3. **View JSON Response**: See the weather and season details of the Minecraft world.

---

## 🌱 Seasonal Calculation
The plugin calculates seasons based on in-game time:
- **Spring**: Days 0–89
- **Summer**: Days 90–179
- **Autumn**: Days 180–269
- **Winter**: Days 270–359

Seasons repeat every 360 days.

---

## 👨‍💻 Development
### Building from Source
1. Clone the repository:
   ```bash
   git clone https://github.com/Oakflat/WeatherAPIPlugin.git
   ```
2. Open the project in your IDE.
3. Build the project using your preferred build tool (e.g., Maven or Gradle).

### Contributing
Contributions are welcome! Feel free to open issues or submit pull requests to improve this plugin.

---

## 📜 License
This project is licensed under the **MIT License**. See the `LICENSE` file for details.

---

## 📧 Contact
For questions or support, contact:
- **Email**: lianheliping@gmail.com
- **GitHub Issues**: Open an issue in the [repository](https://github.com/Oakflat/WeatherAPIPlugin).

---

_🌤️ Make your Minecraft world weather-aware!_

