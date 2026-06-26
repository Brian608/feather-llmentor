package org.feather.llm.mentor.agent.tools;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @projectName: feather-llmentor
 * @package: org.feather.llm.mentor.agent.tools
 * @className: Stock
 * @author: feather
 * @description:
 * @since: 2026-06-26 3:36 PM
 * @version: 1.0
 */
public record Stock(Date today, BigDecimal bigDecimal) {
}

