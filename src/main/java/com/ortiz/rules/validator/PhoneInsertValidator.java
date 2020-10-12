package com.ortiz.rules.validator;

import br.com.fluentvalidator.AbstractValidator;
import com.ortiz.domain.Phone;
import com.ortiz.rules.validator.utils.PredicatesUtils;
import org.springframework.stereotype.Component;

import static br.com.fluentvalidator.predicate.LogicalPredicate.not;
import static br.com.fluentvalidator.predicate.ObjectPredicate.nullValue;
import static br.com.fluentvalidator.predicate.StringPredicate.stringEmptyOrNull;

@Component
public class PhoneInsertValidator extends AbstractValidator<Phone> {

    @Override
    public void rules() {
        setPropertyOnContext(this.getClass().getName());

        ruleFor(Phone::getId)
                .must(stringEmptyOrNull())
                .withMessage("Phone Id must be empty")
                .critical();

        ruleFor(Phone::getDdi)
                .must(not(nullValue()))
                .withMessage("DDI is required")
                .must(PredicatesUtils.integerBetween(1, 999))
                .withMessage("DDI invalid")
                .critical();

        ruleFor(Phone::getDdd)
                .must(not(nullValue()))
                .withMessage("DDD is required")
                .must(PredicatesUtils.integerBetween(1, 999))
                .withMessage("DDD invalid")
                .critical();

        ruleFor(Phone::getNumber)
                .must(not(nullValue()))
                .withMessage("Phone number is required")
                .critical();
    }
}

