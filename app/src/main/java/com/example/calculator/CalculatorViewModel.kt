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
        }
    }

    private fun enterOperation(operation: CalculatorOperation) {
        if(_calculatorState.value.number1.isBlank()){
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
        TODO("Not yet implemented")
    }

    private fun calculate() {
        TODO("Not yet implemented")
    }
}