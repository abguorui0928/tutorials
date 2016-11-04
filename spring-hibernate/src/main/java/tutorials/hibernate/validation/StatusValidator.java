package tutorials.hibernate.validation;

import java.util.Set;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.google.common.collect.Sets;

public class StatusValidator implements ConstraintValidator<Status, String> {
    private final Set<String> STATUS = Sets.newHashSet("created", "paid", "shipped", "closed");

    public void initialize(Status status) {}

    public boolean isValid(String value, ConstraintValidatorContext context) {
        return STATUS.contains(value);
    }
}
