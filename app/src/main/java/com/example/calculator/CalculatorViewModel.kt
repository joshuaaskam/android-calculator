package com.example.calculator

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CalculatorViewModel : ViewModel() {
    private val _calculatorState = MutableStateFlow(CalculatorState())
    val calculatorState: StateFlow<CalculatorState> = _calculatorState.asStateFlow()

    fun onAction(action: CalculatorAction) {
        when(action) {
            is CalculatorAction.Calculate -> calculate()
            is CalculatorAction.Decimal -> enterDecimal()
            is CalculatorAction.Delete -> delete()
            is CalculatorAction.Number -> enterNumber(action.number)
            is CalculatorAction.Operation -> enterOperation(action.operation)
            is CalculatorAction.Clear -> _calculatorState.value = CalculatorState()
            is CalculatorAction.Negate -> negate()
        }
    }

    private fun negate() {
        if(_calculatorState.value.operation == null && _calculatorState.value.number1.isNotBlank() && _calculatorState.value.number1 != "0"){
            _calculatorState.update { currentState ->
                currentState.copy(
                    number1 = if(currentState.number1.contains("-")) currentState.number1.drop(1)
                    else "-" + currentState.number1
                )
            }
        }
        else if(_calculatorState.value.operation != null && _calculatorState.value.number2.isNotBlank() && _calculatorState.value.number2 != "0"){
            _calculatorState.update { currentState ->
                currentState.copy(
                    number2 = if(currentState.number2.contains("-")) currentState.number2.drop(1)
                    else "-" + currentState.number2
                )
            }
        }
    }

    private fun enterOperation(operation: CalculatorOperation) {
        if(_calculatorState.value.number1.isBlank() || _calculatorState.value.number1 == "-"){
            return
        }
        _calculatorState.update { currentState ->
            currentState.copy(
                operation = operation
            )
        }
    }

    private fun enterNumber(number: Int) {
        if(_calculatorState.value.number2.isBlank() && _calculatorState.value.operation == null && !(_calculatorState.value.number1 == "0" && number == 0)){
            _calculatorState.update { currentState ->
                currentState.copy(
                    number1 = if(currentState.number1 == "0") number.toString() else currentState.number1 + number.toString()
                )
            }
        }
        else if(_calculatorState.value.operation != null && !(_calculatorState.value.number2 == "0" && number == 0)){
            _calculatorState.update { currentState ->
                currentState.copy(
                    number2 = if(currentState.number2 == "0") number.toString() else currentState.number2 + number.toString()
                )
            }
        }
    }

    private fun delete() {
        if(_calculatorState.value.number2.isNotBlank()){
            _calculatorState.update { currentState ->
                currentState.copy(
                    number2 = currentState.number2.dropLast(1)
                )
            }
        }
        else if(_calculatorState.value.operation != null){
            _calculatorState.update { currentState ->
                currentState.copy(
                    operation = null
                )
            }
        }
        else if(_calculatorState.value.number1.isNotBlank()){
            _calculatorState.update { currentState ->
                currentState.copy(
                    number1 = currentState.number1.dropLast(1)
                )
            }
        }
    }

    private fun enterDecimal() {
        val number1 = _calculatorState.value.number1
        val number2 = _calculatorState.value.number2
        val operator = _calculatorState.value.operation?.symbol ?: ""
        if(operator.isBlank() && !number1.contains(".")){
            _calculatorState.update { currentState ->
                currentState.copy(
                    number1 = if(number1.isBlank()) "0." else "$number1."
                )
            }
        }
        else {
            _calculatorState.update { currentState ->
                currentState.copy(
                    number2 = if(number2.isBlank()) "0." else "$number2."
                )
            }
        }
    }

    private fun calculate() {
        val number1 = _calculatorState.value.number1
        val number2 = _calculatorState.value.number2
        val operator = _calculatorState.value.operation?.symbol ?: ""
        if(number1.isBlank() || operator.isBlank() || number2.isBlank() || number2 == "-"){
            return
        }
        val ans: Double = when(operator){
            "+" -> number1.toDouble() + number2.toDouble()
            "-" -> number1.toDouble() - number2.toDouble()
            "x" -> number1.toDouble() * number2.toDouble()
            "÷" -> number1.toDouble() / number2.toDouble()
            else -> 0.0
        }

        _calculatorState.value = CalculatorState(number1 = ans.toString())
    }
}