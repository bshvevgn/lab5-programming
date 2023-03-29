package validators.coordinatesValidators;

import parameters.Coordinates;
import validators.Validator;

import java.util.Objects;

import static java.lang.Double.parseDouble;

public class YCoordinateValidator implements Validator {
    public boolean validate(String value) {
        try {
            Float.parseFloat(value);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Введён неправильный тип данных. Требуется: Float.\n");
            return false;
        }
    }
}
