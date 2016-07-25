### Precipitation
#### Minutely Precipitation

| Value         | Precipitation | 中文           |
| ------------- |-------------- | ------------- |
| 0-0.05        | no rain       | 基本认为无降雨   |
| 0.05-0.15     | light rain    | 小雨           |
| 0.15-0.35     | moderate rain | 中雨           |
| &gt;0.35      | heavy rain    | 大雨           |

#### Hourly Precipitation

| Value             | Precipitation | 中文      |
| ----------------- |-------------- | -------- |
| 0-10              | light rain    | 小雨      |
| 10-25             | moderate rain | 中雨      |
| 25-50 &amp; &gt;50| heavy rain    | 大雨      |

### Wind
#### Wind Direction

| Value             | Direction     | 中文      |
| ----------------- |-------------- | -------- |
| [337.5, 22.5)     | North         | 北风      |
| [22.5, 67.5)      | NorthEast     | 东北风    |
| [67.5, 112.5)     | East          | 东风      |
| [112.5, 157.5)    | SouthEast     | 东南风    |
| [157.5, 202.5)    | South         | 南风      |
| [202.5, 247.5)    | SouthWest     | 西南风    |
| [247.5, 292.5)    | West          | 西风      |
| [292.5, 337.5)    | NorthWest     | 西风      |

```java
        if (direction < 0) {
            if (direction < -360) {
                direction %= 360;
            }
            direction += 360;
        } else  if (direction > 360) {
            direction %= 360;
        }
        if (direction >= 337.5F && direction < 22.5F) {
            return North;
        } else if (direction >= 22.5F && direction < 67.5F) {
            return NorthEast;
        } else if (direction >= 67.5F && direction < 112.5F) {
            return East;
        } else if (direction >= 112.5F && direction < 157.5F) {
            return SouthEast;
        } else if (direction >= 157.5F && direction < 202.5F) {
            return South;
        } else if (direction >= 202.5F && direction < 247.5F) {
            return SouthWest;
        } else if (direction >= 247.5F && direction < 292.5F) {
            return West;
        } else {
            return NorthWest;
        }
```

#### Wind Level

| Value of Speed    | Level | Level Name    | English          | 中文       |
| ----------------- | ----- | -------------- |---------------- | -------- |
| [0, 1)            | 0     |  CALM          | Calm            | 无风      |
| [1, 6)            | 1     |  LIGHT_AIR     | Light air       | 软风      |
| [6, 12)           | 2     |  LIGHT         | Light breeze    | 轻风      |
| [12, 20)          | 3     |  GENTLE        | Gentle breeze   | 微风      |
| [20, 29)          | 4     |  MODERATE      | Moderate breeze | 和风      |
| [29, 39)          | 5     |  FRESH         | Fresh breeze    | 清风      |
| [39, 50)          | 6     |  STRONG        | Strong breeze   | 强风      |
| [50, 62)          | 7     |  NEAR_GALE     | Near gale       | 劲风      |
| [62, 75)          | 8     |  GALE          | Gale            | 大风      |
| [75, 89)          | 9     |  STRONG_GALE   | Strong gale     | 烈风      |
| [89, 103)         | 10    |  STORM         | Storm           | 狂风      |
| [103, 117)        | 11    |  VIOLENT_STORM | Violent storm   | 暴风      |
| [117, +)          | 12    |  HURRICANE     | Hurricane       | 台风/飓风  |


### Cloud

#### Cloud Rate

| Value        | English       | 中文  |
| ------------ | ------------- | ---- |
| [0%, 10%]    | Clear         | 晴天  |
| (10%, 30%]   | Partly Cloudy | 少云  |
| (30%, 70%]   | Partly Cloudy | 多云  |
| (70%, +)     | Cloudy        | 阴天  |

### Air Quality Index

#### AQI

| Rate       | Level | Level Name               | Color             | English                         | 中文     |
| ---------- | ----- | ------------------------ | ----------------- |-------------------------------- | ------- |
| (0,50]     | 1     |  GOOD                    | Green `#00E400`   | Good                            | 优       |
| (50, 100]  | 2     |  MODERATE                | Yellow `#FFFF00`  |  Moderate                       | 良       |
| (100, 150] | 3     |  UNHEALTHY_FOR_SENSITIVE | Orange `#FF7E00`  |  Unhealthy for Sensitive Groups | 轻度污染  |
| (150, 200] | 4     |  UNHEALTHY               | Red `#FF0000`     |  Unhealthy                      | 中度污染  |
| (200, 300] | 5     |  VERY_UNHEALTHY          | Purple `#8f3f97`  |  Very Unhealthy                 | 重度污染  |
| (300, +)   | 6     |  HAZARDOUS               | Maroon `#7E0023`  |  Hazardous                      | 严重污染  |
