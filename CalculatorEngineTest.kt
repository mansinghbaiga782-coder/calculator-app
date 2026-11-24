package com.example

import com.example.domain.CalcError
import com.example.domain.CalculatorEngine
import com.example.domain.EvalResult
import com.example.domain.IndianNumberFormatter
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class CalculatorEngineTest {

  @Test
  fun basicArithmetic_respectsPrecedence() {
    val result = CalculatorEngine.evaluate("100+50×2")
    assertTrue(result is EvalResult.Success)
    assertEquals("200", (result as EvalResult.Success).formatted)
  }

  @Test
  fun percentageAddition_computesGstStyle() {
    val result = CalculatorEngine.evaluate("1000+18%")
    assertTrue(result is EvalResult.Success)
    assertEquals("1,180", (result as EvalResult.Success).formatted)
  }

  @Test
  fun percentageSubtraction_computesDiscountStyle() {
    val result = CalculatorEngine.evaluate("1000−10%")
    assertTrue(result is EvalResult.Success)
    assertEquals("900", (result as EvalResult.Success).formatted)
  }

  @Test
  fun divisionByZero_returnsFailure() {
    val result = CalculatorEngine.evaluate("500÷0")
    assertEquals(EvalResult.Failure(CalcError.DIVIDE_BY_ZERO), result)
  }

  @Test
  fun indianFormatting_groupsLakhsAndCrores() {
    val formatted = IndianNumberFormatter.formatPlainNumericString("1250000.5")
    assertEquals("12,50,000.5", formatted)
  }
}
