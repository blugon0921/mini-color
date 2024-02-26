# mini-color

[![mini-color](https://img.shields.io/badge/mini%20color-1.0.0-blue.svg)]()
<br><br>
[![Java](https://img.shields.io/badge/Java-17-FF7700.svg?logo=java)]()
[![Kotlin](https://img.shields.io/badge/Kotlin-1.9.22-186FCC.svg?logo=kotlin)]()
[![PaperMC](https://img.shields.io/badge/PaperMC-1.20.4-222222.svg)]()


<br/>
<br/>

## Usage
Default Color - MiniColor.[Color] (MiniColor.RED)<br/>
Hex Color - MiniColor.of("[Hex]") (MiniColor.of("#FF0000"))<br/>
RGB Color - MiniColor.of(red, green, blue)<br/>
Bukkit Color - MiniColor.of([org.bukkit.Color])<br/>

Close Default Color Tag - MiniColor.RED.close<br/>
Close Hex,RGB,Bukkit Color Tag - MiniColor.ofClose([Color])


Create Mini Message - "This is MiniMessage".miniMessage

Random HexCode - MiniColor.RandomHex (= return "#FF0000")

<br/>

### Use API


## Maven
```xml
<repositories>
    <repository>
        <id>kr.blugon</id>
        <url>https://repo.blugon.kr/repository/maven-public/</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>kr.blugon</groupId>
        <artifactId>mini-color</artifactId>
        <version>VERSION</version>
    </dependency>
</dependencies>
```


## Groovy
```gradle
repositories {
    maven { 'https://repo.blugon.kr/repository/maven-public/' }
}

dependencies {
    implementation 'kr.blugon:mini-color:VERSION'
}
```

## Kotlin DSL
```kotlin
repositories {
    maven("https://repo.blugon.kr/repository/maven-public/")
}

dependencies {
    implementation("kr.blugon:mini-color:VERSION")
}
```
