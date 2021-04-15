package com.tech.imagecapture

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //camera wil open and we can click the picture
        btn_camera.setOnClickListener {
            //alert dialog

            var dialog = AlertDialog.Builder(this)
            dialog.setTitle("open camera?")
            dialog.setMessage("Do you want to open camera?")
            dialog.setIcon(R.drawable.ic_baseline_camera_24)

            var listener = DialogInterface.OnClickListener { dialog, which ->
                if (which == DialogInterface.BUTTON_POSITIVE) {
                    var intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    startActivityForResult(intent, 15)
                } else if (which == DialogInterface.BUTTON_NEGATIVE) {
                    finish()
                    dialog.dismiss()
                } else if (which == DialogInterface.BUTTON_NEUTRAL) {
                    dialog.dismiss()
                }

            }
            dialog.setPositiveButton("yes", listener)
            dialog.setNegativeButton("no", listener)
            dialog.setNeutralButton("cancel", listener)
            dialog.show()

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        var clicked_image = data?.extras?.get("data") as Bitmap
        image_view.setImageBitmap(clicked_image)
    }
}