package com.wardlee.unitconverter;

public class Converter {

    // Set up a variable for the number to use to calculate the conversion
    private final double conversion;

    /**
     * Use an enum to create a group of constants for unit types
     * in a type-safe way
     * @ref https://androidkennel.org/unit-converter-android-tutorial/
     */
    public enum Unit {
        METRE,
        CENTIMETRE,
        FOOT,
        INCH,
        KILOGRAM,
        GRAM,
        OUNCE,
        POUND,
        CELSIUS,
        FAHRENHEIT,
        KELVIN;

        /**
         * Method to convert text to one of the unit constants
         * @param input - input string to convert
         * @return Unit - the found unit in our enum
         */
        public static Unit getUnit(String input) {

            // Remove the "Degrees" qualifier from the label to get just the part we need,
            // e.g. "Degrees celsius" will now just be "celsius"
            input = input.replaceAll("Degrees ", "");

            // Make sure the input is in the correct case to match to a unit in our group
            input = input.toUpperCase();

            // Loop through the units to find the one we're looking for
            for (Unit unit : Unit.values()) {
                if (input.equals(unit.toString())) {
                    return unit;
                }
            }

            // Unit not found, something has gone wrong
            throw new IllegalArgumentException("Unit " + input + " not found");
        }
    }


    /**
     * The class constructor
     * @param from - the unit to convert from
     * @param to - the unit to convert to
     * @param input - the value to convert
     */
    public Converter(Unit from, Unit to, double input) {

        double result = 1.0;

        // Work out the formula to use for our calculation and assign it to the result variable
        switch(from) {
            case METRE:
                switch(to) {
                    case METRE:
                        result = input;
                        break;
                    case INCH:
                        result = input * 39.3701;
                        break;
                    case CENTIMETRE:
                        result = input * 100;
                        break;
                    case FOOT:
                        result = input * 3.28084;
                        break;
                }
                break;
            case CENTIMETRE:
                switch(to) {
                    case CENTIMETRE:
                        result = input;
                        break;
                    case INCH:
                        result = input * 0.393701;
                        break;
                    case METRE:
                        result = input * 0.01;
                        break;
                    case FOOT:
                        result = input * 0.0328084;
                        break;
                }
                break;
            case FOOT:
                switch(to) {
                    case FOOT:
                        result = input;
                        break;
                    case INCH:
                        result = input * 12;
                        break;
                    case CENTIMETRE:
                        result = input * 30.48;
                        break;
                    case METRE:
                        result = input * 0.3048;
                        break;
                }
                break;
            case INCH:
                switch(to) {
                    case INCH:
                        result = input;
                        break;
                    case METRE:
                        result = input * 0.0254;
                        break;
                    case CENTIMETRE:
                        result = input * 2.54;
                        break;
                    case FOOT:
                        result = input * 0.0833333;
                        break;
                }
                break;
            case KILOGRAM:
                switch(to) {
                    case KILOGRAM:
                        result = input;
                        break;
                    case GRAM:
                        result = input * 1000;
                        break;
                    case OUNCE:
                        result = input * 35.274;
                        break;
                    case POUND:
                        result = input * 2.205;
                        break;
                }
                break;
            case GRAM:
                switch(to) {
                    case GRAM:
                        result = input;
                        break;
                    case KILOGRAM:
                        result = input * 0.001;
                        break;
                    case OUNCE:
                        result = input * 0.0352;
                        break;
                    case POUND:
                        result = input * 0.0022;
                        break;
                }
                break;
            case OUNCE:
                switch(to) {
                    case OUNCE:
                        result = input;
                        break;
                    case KILOGRAM:
                        result = input * 0.028;
                        break;
                    case GRAM:
                        result = input * 28.35;
                        break;
                    case POUND:
                        result = input * 0.0625;
                        break;
                }
                break;
            case POUND:
                switch(to) {
                    case POUND:
                        result = input;
                        break;
                    case KILOGRAM:
                        result = input * 0.456;
                        break;
                    case OUNCE:
                        result = input * 16;
                        break;
                    case GRAM:
                        result = input * 453.59;
                        break;
                }
                break;
            case CELSIUS:
                switch(to) {
                    case CELSIUS:
                        result = input;
                        break;
                    case FAHRENHEIT:
                        result = (input * 9/5) + 32;
                        break;
                    case KELVIN:
                        result = input + 273.15;
                        break;
                }
                break;
            case FAHRENHEIT:
                switch(to) {
                    case FAHRENHEIT:
                        result = input;
                        break;
                    case CELSIUS:
                        result = (input - 32) * 5/9;
                        break;
                    case KELVIN:
                        result = (input - 32) * 5/9 + 273.15;
                        break;
                }
                break;
            case KELVIN:
                switch(to) {
                    case KELVIN:
                        result = input;
                        break;
                    case CELSIUS:
                        result = input - 273.15;
                        break;
                    case FAHRENHEIT:
                        result = (input - 273.15) * 9/5 + 32;
                        break;
                }
                break;
        }

        // Assign the result we've found to the conversion variable at the top of this class
        conversion = result;
    }


    /**
     * Method to return the result (called by the button)
     *
     * @return - the converted value
     */
    double convert() {

        // Return the conversion
        return conversion;
    }
}