package com.paymentservice.util;

import java.text.Normalizer;
import org.apache.commons.lang3.StringUtils;

public class NormalizeStringUtils {
  public static String normalize(String source) {

    return Normalizer.normalize(source, Normalizer.Form.NFD)
        .replaceAll("[^\\p{ASCII}]", "")
        .replaceAll("[+^~!@#$%&*()]*", "")
        .replaceFirst("[-+^~!@#$%&*()]*", "")
        .trim()
        .replace(StringUtils.SPACE, "-")
        .toLowerCase();
  }
}
