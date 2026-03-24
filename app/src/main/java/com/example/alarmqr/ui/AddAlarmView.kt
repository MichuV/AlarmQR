package com.example.alarmqr.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TimeInput
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.alarmqr.ui.theme.FitButton
import com.example.alarmqr.ui.theme.WideButton
import java.util.Calendar
import java.util.Date
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import java.text.SimpleDateFormat
import java.util.Locale
import com.example.alarmqr.ui.theme.SuccessText
import com.example.alarmqr.ui.theme.ErrorText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddAlarmView(){
    val titleState = rememberTextFieldState()
    val alarmOffset = rememberTextFieldState()
    val currentTime = Calendar.getInstance()
    val timePickerState = rememberTimePickerState(
        initialHour = currentTime.get(Calendar.HOUR_OF_DAY),
        initialMinute = currentTime.get(Calendar.MINUTE),
        is24Hour = true,
    )
    val tomorrow = Calendar.getInstance().apply {
        add(Calendar.DAY_OF_YEAR, 1)
    }
    val datePickerState = rememberDatePickerState(tomorrow.timeInMillis)
    var showDialog by remember { mutableStateOf(false) }
    var qrErrorState = "QR Code not scanned"
    var qrSuccessState = "QR Code scanned successfully"

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(50.dp))
            OutlinedTextField(
                state = titleState,
                label = { Text("Alarm Title") },
                lineLimits = TextFieldLineLimits.SingleLine
            )

            Spacer(modifier = Modifier.height(40.dp))
            HorizontalDivider(thickness = 2.dp)
            Spacer(modifier = Modifier.height(40.dp))

            TimeInput(
                state = timePickerState,
                colors = TimePickerDefaults.colors(
                    timeSelectorSelectedContainerColor = MaterialTheme.colorScheme.tertiary
                ))
            Spacer(modifier = Modifier.height(20.dp))

            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth(0.7f)) {
                Text("${
                    SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(Date(datePickerState.selectedDateMillis ?: 0L))
                    }",
                )
                Spacer(modifier = Modifier.width(20.dp))
                WideButton("Change Date", {showDialog = true})
            }

            if (showDialog) {
                DatePickerDialog(
                    onDismissRequest = { showDialog = false },
                    confirmButton = {
                        FitButton("OK") {
                            showDialog = false
                        }
                    }
                ) {
                    DatePicker(datePickerState)
                }
            }

            Spacer(modifier = Modifier.height(40.dp))
            HorizontalDivider(thickness = 2.dp)
            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = "Enter time for next alarm and scan QR Code that will disable the alarm",
                modifier = Modifier.fillMaxWidth(0.7f),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(30.dp))
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth(0.7f)) {
                OutlinedTextField(
                    state = alarmOffset,
                    label = { Text("Time (s)") },
                    lineLimits = TextFieldLineLimits.SingleLine,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth(0.5f)
                )
                Spacer(modifier = Modifier.width(20.dp))
                WideButton("QR Code", {})
            }
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = qrErrorState,
                textAlign = TextAlign.Center,
                color = ErrorText
            )

            Spacer(modifier = Modifier.height(40.dp))
            FitButton("Add", {})
        }
    }
}

fun ValidateOffset(offsetText: String): Int {
    return offsetText.toIntOrNull() ?: 0
}