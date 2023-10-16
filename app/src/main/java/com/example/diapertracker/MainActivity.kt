package com.example.diapertracker

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    //Declare vars but set the value later
    private lateinit var dirtyButton: RadioButton
    private lateinit var wetButton: RadioButton
    private lateinit var dryButton: RadioButton
    private lateinit var currentTime: EditText
    private lateinit var diaperChangesText: TextView
    private lateinit var diaperChangesCount: TextView

    //Counter variable
    private var diaperCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Get a reference to our buttons and set listeners
        val addButton: Button = findViewById(R.id.main_btn_add)
        val clearButton: Button = findViewById(R.id.main_btn_clear)

        addButton.setOnClickListener {addNewDiaper()}
        clearButton.setOnClickListener {clear()}

        //Set values to other views
        dirtyButton = findViewById(R.id.main_rb_dirty)
        wetButton = findViewById(R.id.main_rb_wet)
        dryButton = findViewById(R.id.main_rb_dry)
        currentTime = findViewById(R.id.main_et_time)
        diaperChangesText = findViewById(R.id.main_tv_diaper_changes)
        diaperChangesCount = findViewById(R.id.main_tv_diaper_counter)
    }
    //Create new diaper to add to list
    private fun addNewDiaper() {
        //Get the current time
        var timeOfChange = "00:00"
        if (currentTime.text.toString() != ""){
            timeOfChange = currentTime.text.toString()
        }
        val newDiaper: String = when {
            dirtyButton.isChecked -> "- A dirty diaper was changed at $timeOfChange"
            wetButton.isChecked -> "- A wet diaper was changed at $timeOfChange"
            else -> "- A dry diaper was changed at $timeOfChange"
        }


        diaperCount++

        //Update our diaper list
        updateDiaperList(newDiaper)

    }

    //Update the diaper list
    private fun updateDiaperList(newDiaper: String) {
        //Get old list of diapers and add new one to end
        val oldDiapers = diaperChangesText.text.toString()
        val updatedDiapers = "$oldDiapers\n$newDiaper"
        diaperChangesText.text = updatedDiapers
        diaperChangesCount.text = "Total Diapers Changed: $diaperCount"

        //Clear the editText
        currentTime.setText("")

        //Hide the keyboard
        hideKeyboard()
    }

    private fun hideKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE
        ) as InputMethodManager
        imm.hideSoftInputFromWindow(currentTime.windowToken, 0)
    }


    //Clear all diapers from our list
    private fun clear() {
        //Reset all UI text and counters
        diaperChangesText.text = ""
        diaperChangesCount.text = "Total Diapers Changed: 0"
        diaperCount = 0
        currentTime.setText("")

    }
}