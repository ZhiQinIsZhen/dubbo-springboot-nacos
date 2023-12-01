package com.liyz.dubbo.common.util.deserializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.liyz.dubbo.common.util.DateUtil;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.Date;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/12/1 11:07
 */
public class DateTimeDeserializer extends JsonDeserializer<Date> {

    @Override
    public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        String text = p.getText();
        if (StringUtils.isBlank(text)) {
            return null;
        }
        if (text.length() == DateUtil.PATTERN_DATE_TIME.length()) {
            return DateUtil.parse(text, DateUtil.PATTERN_DATE_TIME);
        }
        if (text.length() == DateUtil.DATE_OPTIONAL_TIME.length()) {
            return DateUtil.parse(text, DateUtil.DATE_OPTIONAL_TIME);
        }
        return DateUtil.parse(text, DateUtil.PATTERN_DATE);
    }
}
