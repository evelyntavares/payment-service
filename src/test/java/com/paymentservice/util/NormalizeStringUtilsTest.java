package com.paymentservice.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class NormalizeStringUtilsTest {

  @ParameterizedTest
  @MethodSource("stringProvider")
  void should(String input, String expectedResult) {
    final String normalizedResult = NormalizeStringUtils.normalize(input);

    assertEquals(expectedResult, normalizedResult);
  }

  private static Stream<Arguments> stringProvider() {
    return Stream.of(
        Arguments.of("ã é ï ô ù", "a-e-i-o-u"),
        Arguments.of("AbCdE", "abcde"),
        Arguments.of("  test 2 ", "test-2"),
        Arguments.of("-test-3-", "test-3-"),
        Arguments.of("@-test-4-&-#", "test-4--"));
  }
}
