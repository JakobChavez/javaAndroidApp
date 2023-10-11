package com.example.myapplication1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
class MainActivity : AppCompatActivity() {
    private lateinit var etUserTile: EditText
    private lateinit var etprice: EditText
    private lateinit var etuserLocation: EditText
    private lateinit var etuserPhone: EditText
    private lateinit var etuserPostalCode: EditText
    private lateinit var etuserCountry: EditText
    private lateinit var btnSaveData: Button

    private lateinit var dbRef: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        etUserTile = findViewById(R.id.etUserTitle)
        etprice = findViewById(R.id.etPrice)
        etuserLocation = findViewById(R.id.etUserLocation)
        etuserPhone = findViewById(R.id.etUserPhoneNumber)
        etuserPostalCode = findViewById(R.id.etUserPostalCode)
        etuserCountry = findViewById(R.id.etUserCountry)
        btnSaveData = findViewById(R.id.button)
        dbRef = FirebaseDatabase.getInstance().getReference("Users")
        btnSaveData.setOnClickListener {
            saveUserData()
        }
    }

    private fun saveUserData() {
        var title = etUserTile.text.toString()
        var price = etprice.text.toString()
        var location = etuserLocation.text.toString()
        var phone = etuserPhone.text.toString()
        var postalcode = etuserPostalCode.text.toString()
        var country = etuserCountry.text.toString()
        if (title.isEmpty()) {
            etUserTile.error = "Please enter Title"
        }
        if (price.isEmpty()) {
            etprice.error = "Please enter Price"
        }
        if (location.isEmpty()) {
            etuserLocation.error = "Please Enter Location"
        }
        if (phone.isEmpty()) {
            etuserPhone.error = "Please Enter PhoneNumber"
        }
        if (postalcode.isEmpty()) {
            etuserPostalCode.error = "Please Enter PostalCode"
        }
        if (country.isEmpty()) {
            etuserCountry.error = "Please Enter your country"
        }
        val userID = dbRef.push().key!!
        val user = User(userID, title, price, location, phone, postalcode, country)
        dbRef.child(userID).setValue(user)
            .addOnCompleteListener {
                Toast.makeText(this, "Data inserted Successfully", Toast.LENGTH_LONG).show()
            }.addOnFailureListener { err ->
                Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
            }
    }
    }
