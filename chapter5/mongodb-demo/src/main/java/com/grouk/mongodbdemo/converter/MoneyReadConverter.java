package com.grouk.mongodbdemo.converter;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.core.convert.converter.Converter;

/**
 * @author lizhengjun
 */
public class MoneyReadConverter implements Converter<Long, Money> {
    @Override
    public Money convert(Long aLong) {
        return Money.ofMinor(CurrencyUnit.ofCountry("CNY"), aLong);
    }
}
