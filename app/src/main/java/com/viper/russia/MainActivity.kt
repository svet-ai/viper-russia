package com.viper.russia

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView

class MainActivity : Activity() {
    private val bg = Color.rgb(11, 11, 16)
    private val panel = Color.rgb(27, 27, 36)
    private val accent = Color.rgb(210, 30, 45)
    private lateinit var content: LinearLayout
    private lateinit var status: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = bg
        window.navigationBarColor = bg

        val scroll = ScrollView(this)
        content = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER_HORIZONTAL
            setPadding(22, 38, 22, 28)
            setBackgroundColor(bg)
        }
        scroll.addView(content)
        setContentView(scroll)
        showHome()
    }

    private fun showHome() {
        content.removeAllViews()
        val logo = TextView(this).apply {
            text = "VIPER\nRUSSIA"
            textSize = 36f
            gravity = Gravity.CENTER
            setTextColor(Color.WHITE)
            typeface = Typeface.DEFAULT_BOLD
        }
        content.addView(logo, lp())
        content.addView(label("МОБИЛЬНЫЙ ROLEPLAY-ПРОЕКТ", 12f, Color.LTGRAY))
        content.addView(space(24))

        status = TextView(this).apply {
            text = "ИГРОВОЙ СЕРВЕР\n94.23.168.153:3123\n\nИгровой клиент не установлен. Для входа потребуется совместимый Android-клиент GTA San Andreas Multiplayer."
            textSize = 15f
            gravity = Gravity.CENTER
            setTextColor(Color.WHITE)
            setPadding(18, 22, 18, 22)
            setBackgroundColor(panel)
        }
        content.addView(status, lp())

        addButton("ИГРАТЬ") {
            status.text = "Не удалось запустить игру: совместимый игровой клиент пока не установлен. Установите подходящий клиент, затем мы подключим его к лаунчеру."
        }
        addButton("ИГРОВОЙ КЭШ") {
            showMessage("ИГРОВОЙ КЭШ", "Здесь будет загрузка, проверка целостности и распаковка игрового архива. Для включения функции нужна проверенная прямая ссылка на ZIP и манифест файлов.")
        }
        addButton("НОВОСТИ") {
            showMessage("НОВОСТИ", "Новости пока не подключены. Для публикации новостей потребуется серверная API или база данных.")
        }
        addButton("ПРОФИЛЬ") {
            showMessage("ПРОФИЛЬ", "Система аккаунтов будет подключена после настройки backend и правил авторизации.")
        }
        addButton("НАСТРОЙКИ") {
            showMessage("НАСТРОЙКИ", "Версия лаунчера: 1.0.0\nЯзык: Русский\nСервер: 94.23.168.153:3123")
        }
        content.addView(space(22))
        content.addView(label("VIPER RUSSIA  •  ВЕРСИЯ 1.0.0", 11f, Color.GRAY))
    }

    private fun showMessage(title: String, message: String) {
        content.removeAllViews()
        content.addView(label(title, 23f, Color.WHITE))
        content.addView(space(18))
        val box = TextView(this).apply {
            text = message
            textSize = 15f
            setTextColor(Color.WHITE)
            setPadding(18, 22, 18, 22)
            setBackgroundColor(panel)
        }
        content.addView(box, lp())
        addButton("НАЗАД") { showHome() }
    }

    private fun addButton(title: String, action: () -> Unit) {
        val button = Button(this).apply {
            text = title
            textSize = 14f
            setTextColor(Color.WHITE)
            setBackgroundColor(accent)
            isAllCaps = false
            setOnClickListener { action() }
        }
        content.addView(button, LinearLayout.LayoutParams(-1, 56).apply { topMargin = 12 })
    }

    private fun label(value: String, size: Float, color: Int) = TextView(this).apply {
        text = value
        textSize = size
        gravity = Gravity.CENTER
        setTextColor(color)
        setPadding(0, 8, 0, 8)
    }

    private fun space(height: Int) = TextView(this).apply { height }
    private fun lp() = LinearLayout.LayoutParams(-1, -2)
}
