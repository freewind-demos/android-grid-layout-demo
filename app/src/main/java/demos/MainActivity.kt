package demos.android.grid.layout.demo

import android.os.Bundle
import android.widget.Button
import android.widget.GridLayout
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        val gridLayout = findViewById<GridLayout>(R.id.gridLayout)
        for (i in 1..9) {
            val btn = Button(this)
            btn.text = "按钮$i"
            val params = GridLayout.LayoutParams().apply {
                rowSpec = GridLayout.spec((i-1) / 3)
                columnSpec = GridLayout.spec((i-1) % 3)
                width = 0
                height = GridLayout.LayoutParams.WRAP_CONTENT
            }
            gridLayout.addView(btn, params)
        }
    }
}
