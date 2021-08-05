package com.larry.fc.finalproject.api.dto;

import com.larry.fc.finalproject.core.util.Period;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Larry
 */
public class FormatTest {

    @Test
    void test() {
        final String format = EngagementEmailStuff.getEndAtFormat(
                Period.of(
                        LocalDateTime.of(2021, 8, 1, 10, 0),
                        LocalDateTime.of(2021, 8, 1, 18, 0)
                ), EngagementEmailStuff.MAIL_TIME_FORMAT,
                EngagementEmailStuff.endAtFormatParts
        );
        assertEquals("a hh시 mm분", format);

        final String format2 = EngagementEmailStuff.getEndAtFormat(
                Period.of(
                        LocalDateTime.of(2021, 8, 1, 10, 0),
                        LocalDateTime.of(2021, 8, 3, 18, 0)
                ), EngagementEmailStuff.MAIL_TIME_FORMAT,
                EngagementEmailStuff.endAtFormatParts
        );
        assertEquals("dd일(E) a hh시 mm분", format2);

        final String format3 = EngagementEmailStuff.getEndAtFormat(
                Period.of(
                        LocalDateTime.of(2021, 8, 1, 10, 0),
                        LocalDateTime.of(2021, 9, 3, 18, 0)
                ), EngagementEmailStuff.MAIL_TIME_FORMAT,
                EngagementEmailStuff.endAtFormatParts
        );
        assertEquals("MM월 dd일(E) a hh시 mm분", format3);
    }
}
