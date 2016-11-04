package tutorials.hibernate.validation;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

public class ValidationApp {

    public static void main(String[] args) {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Set<ConstraintViolation<Order>> result = validatorFactory.getValidator().validate(new Order());
        for (ConstraintViolation<Order> violation : result) {
            System.out.println(violation.getPropertyPath().toString() + violation.getMessage());
        }
    }
}
