package com.example.calculator

import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull

class CalculatorViewModelTest {

    @Test
    fun onActionEnterNumberAndEnterOperation() {
        val calculatorViewModel = CalculatorViewModel()
        calculatorViewModel.onAction(CalculatorAction.Number(2))
        calculatorViewModel.onAction(CalculatorAction.Operation(CalculatorOperation.Add))
        calculatorViewModel.onAction(CalculatorAction.Number(4))

        val uiState = calculatorViewModel.calculatorState
        assertEquals("2+4", uiState.value.number1 + uiState.value.operation?.symbol + uiState.value.number2)
    }

    @Test
    fun onActionCalculateAdd() {
        val calculatorViewModel = CalculatorViewModel()
        calculatorViewModel.onAction(CalculatorAction.Number(2))
        calculatorViewModel.onAction(CalculatorAction.Operation(CalculatorOperation.Add))
        calculatorViewModel.onAction(CalculatorAction.Number(4))
        calculatorViewModel.onAction(CalculatorAction.Calculate)
        val uiState = calculatorViewModel.calculatorState
        assertEquals("6.0", uiState.value.number1)
    }

    @Test
    fun onActionCalculateSubtract() {
        val calculatorViewModel = CalculatorViewModel()
        calculatorViewModel.onAction(CalculatorAction.Number(2))
        calculatorViewModel.onAction(CalculatorAction.Operation(CalculatorOperation.Subtract))
        calculatorViewModel.onAction(CalculatorAction.Number(4))
        calculatorViewModel.onAction(CalculatorAction.Calculate)
        val uiState = calculatorViewModel.calculatorState
        assertEquals("-2.0", uiState.value.number1)
    }

    @Test
    fun onActionCalculateMultiply() {
        val calculatorViewModel = CalculatorViewModel()
        calculatorViewModel.onAction(CalculatorAction.Number(2))
        calculatorViewModel.onAction(CalculatorAction.Operation(CalculatorOperation.Multiply))
        calculatorViewModel.onAction(CalculatorAction.Number(4))
        calculatorViewModel.onAction(CalculatorAction.Calculate)
        val uiState = calculatorViewModel.calculatorState
        assertEquals("8.0", uiState.value.number1)
    }

    @Test
    fun onActionCalculateDivide() {
        val calculatorViewModel = CalculatorViewModel()
        calculatorViewModel.onAction(CalculatorAction.Number(2))
        calculatorViewModel.onAction(CalculatorAction.Operation(CalculatorOperation.Divide))
        calculatorViewModel.onAction(CalculatorAction.Number(4))
        calculatorViewModel.onAction(CalculatorAction.Calculate)
        val uiState = calculatorViewModel.calculatorState
        assertEquals("0.5", uiState.value.number1)
    }

    @Test
    fun onActionCalculateWhenNullOperation() {
        val calculatorViewModel = CalculatorViewModel()
        calculatorViewModel.onAction(CalculatorAction.Number(2))
        calculatorViewModel.onAction(CalculatorAction.Calculate)
        val uiState = calculatorViewModel.calculatorState
        assertEquals("2", uiState.value.number1)
    }

    @Test
    fun onActionWhenClear() {
        val calculatorViewModel = CalculatorViewModel()
        calculatorViewModel.onAction(CalculatorAction.Number(2))
        calculatorViewModel.onAction(CalculatorAction.Operation(CalculatorOperation.Divide))
        calculatorViewModel.onAction(CalculatorAction.Number(4))
        calculatorViewModel.onAction(CalculatorAction.Clear)
        val uiState = calculatorViewModel.calculatorState
        assertEquals("", uiState.value.number1)
        assertNull(uiState.value.operation)
        assertEquals("", uiState.value.number2)
    }

    @Test
    fun onActionWhenDeleteNumber1() {
        val calculatorViewModel = CalculatorViewModel()
        calculatorViewModel.onAction(CalculatorAction.Number(2))
        calculatorViewModel.onAction(CalculatorAction.Delete)
        val uiState = calculatorViewModel.calculatorState
        assertEquals("", uiState.value.number1)
    }

    @Test
    fun onActionWhenDeleteOperation() {
        val calculatorViewModel = CalculatorViewModel()
        calculatorViewModel.onAction(CalculatorAction.Number(2))
        calculatorViewModel.onAction(CalculatorAction.Operation(CalculatorOperation.Divide))
        calculatorViewModel.onAction(CalculatorAction.Delete)
        val uiState = calculatorViewModel.calculatorState
        assertNull(uiState.value.operation)
    }

    @Test
    fun onActionWhenDeleteNumber2() {
        val calculatorViewModel = CalculatorViewModel()
        calculatorViewModel.onAction(CalculatorAction.Number(2))
        calculatorViewModel.onAction(CalculatorAction.Operation(CalculatorOperation.Divide))
        calculatorViewModel.onAction(CalculatorAction.Number(4))
        calculatorViewModel.onAction(CalculatorAction.Delete)
        val uiState = calculatorViewModel.calculatorState
        assertEquals("", uiState.value.number2)
    }

    @Test
    fun onActionWhenNegateNumber1() {
        val calculatorViewModel = CalculatorViewModel()
        calculatorViewModel.onAction(CalculatorAction.Number(2))
        calculatorViewModel.onAction(CalculatorAction.Negate)
        val uiState = calculatorViewModel.calculatorState
        assertEquals("-2", uiState.value.number1)
    }

    @Test
    fun onActionWhenNegateNumber2() {
        val calculatorViewModel = CalculatorViewModel()
        calculatorViewModel.onAction(CalculatorAction.Number(2))
        calculatorViewModel.onAction(CalculatorAction.Operation(CalculatorOperation.Divide))
        calculatorViewModel.onAction(CalculatorAction.Number(4))
        calculatorViewModel.onAction(CalculatorAction.Negate)
        val uiState = calculatorViewModel.calculatorState
        assertEquals("-4", uiState.value.number2)
    }

    @Test
    fun onActionWhenEnterDecimalNumber1() {
        val calculatorViewModel = CalculatorViewModel()
        calculatorViewModel.onAction(CalculatorAction.Number(2))
        calculatorViewModel.onAction(CalculatorAction.Decimal)
        val uiState = calculatorViewModel.calculatorState
        assertEquals("2.", uiState.value.number1)
    }

    @Test
    fun onActionWhenEnterDecimalNumber2() {
        val calculatorViewModel = CalculatorViewModel()
        calculatorViewModel.onAction(CalculatorAction.Number(2))
        calculatorViewModel.onAction(CalculatorAction.Operation(CalculatorOperation.Divide))
        calculatorViewModel.onAction(CalculatorAction.Number(4))
        calculatorViewModel.onAction(CalculatorAction.Decimal)
        val uiState = calculatorViewModel.calculatorState
        assertEquals("4.", uiState.value.number2)
    }
}