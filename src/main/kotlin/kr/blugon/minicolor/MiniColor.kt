package kr.blugon.minicolor

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.Color
import java.util.*

enum class MiniColor {
    BLACK,
    DARK_BLUE,
    DARK_GREEN,
    DARK_AQUA,
    DARK_RED,
    DARK_PURPLE,
    GOLD,
    GRAY,
    DARK_GRAY,
    BLUE,
    GREEN,
    AQUA,
    RED,
    LIGHT_PURPLE,
    YELLOW,
    WHITE,

    BOLD,
    MAGIC,
    OBFUSCATED,
    STRIKETHROUGH,
    UNDERLINE,
    ITALIC,
    RESET;

    companion object {
        val String.miniMessage: Component
            get() = MiniMessage.miniMessage().deserialize(this)


        fun of(r: Int, g: Int, b: Int): String = of(Color.fromRGB(r, g, b))
        fun ofClose(r: Int, g: Int, b: Int): String = ofClose(Color.fromRGB(r, g, b))

        fun of(color: Color): String = MiniColor.of("#${String.format("%08x", color.asRGB()).substring(2)}")
        fun ofClose(color: Color): String = MiniColor.ofClose("#${String.format("%08x", color.asRGB()).substring(2)}")

        fun of(color: String): String = "<color:$color>"
        fun ofClose(color: String): String = "</color:$color>"

        val RandomHex: String
            get() {
                val nextInt = Random().nextInt(0xffffff + 1)
                val colorCode = String.format("#%06x", nextInt)
                return colorCode
            }
    }


    override fun toString(): String {
        return when(this) {
            MAGIC ->  "<obfuscated>"
            UNDERLINE -> "<underlined>"
            else -> "<${this.name.lowercase()}>"
        }
    }

    val close: String
        get() {
            return when(this) {
                MAGIC ->  "</obfuscated>"
                UNDERLINE -> "</underlined>"
                else -> "</${this.name.lowercase()}>"
            }
        }
}