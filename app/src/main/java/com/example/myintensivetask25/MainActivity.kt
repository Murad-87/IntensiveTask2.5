package com.example.myintensivetask25

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.core.app.ShareCompat
import com.example.myintensivetask25.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.openWebsiteButton.setOnClickListener {
            val uri = Uri.parse(binding.websiteEdittext.text.toString())
            val intent = Intent(Intent.ACTION_VIEW, uri)

            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent);
            } else {
                Log.d("ImplicitIntents", "Can't handle this!");
            }
        }

        binding.openLocationButton.setOnClickListener {
            val addressUri  = Uri.parse("geo:0,0?q=${binding.locationEdittext.text.toString()}")
            val intent = Intent(Intent.ACTION_VIEW, addressUri )
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent);
            } else {
                Log.d("ImplicitIntents", "Can't handle this!");
            }
        }

        binding.shareTextButton.setOnClickListener {
            val txt  = binding.shareEdittext.text.toString()
            val mimeType = "text/plain"
            ShareCompat.IntentBuilder
                .from(this)
                .setType(mimeType)
                .setChooserTitle("Share text with")
                .setText(txt)
                .startChooser();
        }


        binding.btnCamera.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if(intent.resolveActivity(this.packageManager) != null) {
                startActivityForResult(intent, 42)
            }

        }

    }
}