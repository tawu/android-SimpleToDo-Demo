package com.codepath.simpletodo;

//import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;


public class EditItemActivity extends AppCompatActivity
{
    private EditText etEditItem;
    private Integer itemPositionInArray;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        etEditItem = (EditText) findViewById(R.id.etEditItem);
        getDataFromIntent();

        showKeyboard(etEditItem);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void showKeyboard(View view)
    {
        if(view.requestFocus())
        {
            InputMethodManager imm =(InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(view,InputMethodManager.SHOW_IMPLICIT);
        }
    }

    private void getDataFromIntent()
    {
        String itemText = getIntent().getStringExtra("itemText");
        itemPositionInArray = getIntent().getIntExtra("itemPosition", 0);

        setInputField(itemText);
    }

    public void onSaveItem(View v)
    {
        Intent intent= new Intent();
        intent.putExtra("editedItemText", etEditItem.getText().toString());
        intent.putExtra("editedItemPosition", itemPositionInArray);

        setResult(RESULT_OK, intent);
        finish();
    }

    private void setInputField(String text)
    {
        etEditItem.setText(text);
        etEditItem.setSelection(text.length());
    }
}
