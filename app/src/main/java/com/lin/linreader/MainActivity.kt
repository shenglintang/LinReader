package com.lin.linreader

//相当于findviewbyid（）
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.lin.linreader.activity.HomeActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme_NoActionBar)
        super.onCreate(savedInstanceState)
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }


}
