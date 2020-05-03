package com.wardlee.unitconverter;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    // Declare variables for the interface fields
    // (2x text fields and 2x spinners)
    private Spinner fromSpinner, toSpinner;
    private EditText inputText, resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up an adapter to grab the "from" spinner option values from the XML file
        // @see res/values/strings.xml
        ArrayAdapter<CharSequence> unitListAdapter = ArrayAdapter.createFromResource(this, R.array.units, android.R.layout.simple_spinner_item);
        unitListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Set up adapters to grab the "to" spinner option values from the XML file
        // Distances, weights, and temperatures
        // @see res/values/strings.xml
        ArrayAdapter<CharSequence> distanceAdapter = ArrayAdapter.createFromResource(this, R.array.units, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> weightAdapter = ArrayAdapter.createFromResource(this, R.array.units, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> temperatureAdapter = ArrayAdapter.createFromResource(this, R.array.units, android.R.layout.simple_spinner_item);

        // Find the "from" spinner and set it to use the unit list adapter
        fromSpinner = findViewById(R.id.spinner_from);
        fromSpinner.setAdapter(unitListAdapter);

        // Find the text fields and assign them to the private variables at the top of this class
        inputText = findViewById(R.id.editText_from);
        resultText = findViewById(R.id.editText_to);

        // Find the to spinner
        toSpinner = findViewById(R.id.spinner_to);

        // When the from spinner value is changed, change the adapter used by the to spinner accordingly
        fromSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String selectedUnit = parent.getItemAtPosition(position).toString();
                switch (selectedUnit)
                {
                    case "Metre":
                    case "Centimetre":
                    case "Foot":
                    case "Inch":
                        toSpinner.setAdapter(new ArrayAdapter<String>(MainActivity.this,
                                android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.distances)));
                        break;
                    case "Kilogram":
                    case "Gram":
                    case "Ounce":
                    case "Pound":
                        toSpinner.setAdapter(new ArrayAdapter<String>(MainActivity.this,
                                android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.weights)));
                        break;
                    case "Degrees Celsius":
                    case "Degrees Fahrenheit":
                    case "Degrees Kelvin":
                        toSpinner.setAdapter(new ArrayAdapter<String>(MainActivity.this,
                                android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.temperatures)));
                        break;

                }

                // Make the to spinner visible
                toSpinner.setVisibility(View.VISIBLE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
                // can leave this empty
            }
        });
    }

    /**
     * The main convert method - called when the button is clicked
     * @param view
     */
    public void convertUnits(View view) {

        // Get the selected values from the spinners,
        // and cast them to strings so our Unit enum will understand them
        String fromUnitString = (String) fromSpinner.getSelectedItem();
        String toUnitString = (String) toSpinner.getSelectedItem();

        // Get the user's input string and convert it to a double
        double input = Double.parseDouble(inputText.getText().toString());

        // Get the units from the enum
        Converter.Unit fromUnit = Converter.Unit.getUnit(fromUnitString);
        Converter.Unit toUnit = Converter.Unit.getUnit(toUnitString);

        // Create a converter object and convert
        Converter converter = new Converter(fromUnit, toUnit, input);
        double result = converter.convert();

        // Insert the result into the text field in the interface
        resultText.setText(String.valueOf(result));

        // Close the keyboard for a smoother user experience
        // @ref https://stackoverflow.com/questions/3400028/close-virtual-keyboard-on-button-press
        InputMethodManager inputManager = (InputMethodManager) getSystemService(this.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }
}