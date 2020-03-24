package com.example.presidentapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.presidentapp.Adapter.RuleList;
import com.example.presidentapp.Model.Rule;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Rules extends AppCompatActivity {
    EditText editTextRule;
    Button btnAddRule;

    DatabaseReference databaseRule;

    ListView listViewRules;

    List<Rule> ruleList;
    String currentuserId = FirebaseAuth.getInstance().getCurrentUser().getUid();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);

        databaseRule = FirebaseDatabase.getInstance().getReference("Users").child(currentuserId).child("New Building").child("Rules");

        editTextRule = (EditText) findViewById(R.id.add_rule_txtfld);
        btnAddRule = findViewById(R.id.add_rule_btn);

        listViewRules = findViewById(R.id.listViewRules);

        ruleList = new ArrayList<>();
        btnAddRule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addRule();
                editTextRule.setText(null);
            }
        });

        listViewRules.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Rule rule = ruleList.get(position);

                showupdatedialog(rule.getRuleId(), rule.getRule());
                return false;
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseRule.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                ruleList.clear();

                for (DataSnapshot ruleSnapshot : dataSnapshot.getChildren()) {
                    Rule rule = ruleSnapshot.getValue(Rule.class);

                    ruleList.add(rule);
                }
                RuleList adapter = new RuleList(Rules.this, ruleList);
                listViewRules.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void showupdatedialog(final String ruleId, String rule) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.update_rule_dialog, null);
        dialogBuilder.setView(dialogView);

        final EditText editTextRule = (EditText) dialogView.findViewById(R.id.update_rule_edit_txt);
        final Button buttonUpdate = dialogView.findViewById(R.id.update_rule_btn);
        final Button buttonDelete = dialogView.findViewById(R.id.delete_rule_btn);

        dialogBuilder.setTitle("Updating rule: " + rule);

        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();


        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rule = editTextRule.getText().toString().trim();

                if (TextUtils.isEmpty(rule)) {
                    editTextRule.setError("Rule required");
                    return;
                }
                updateRule(ruleId, rule);
                alertDialog.dismiss();
            }
        });
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteRule(ruleId);
                alertDialog.dismiss();
            }
        });


    }

    private void deleteRule(String ruleId) {
        DatabaseReference drRule = FirebaseDatabase.getInstance().getReference("rules").child(currentuserId).child(ruleId);
        drRule.removeValue();
        Toast.makeText(this, "Rule is deleted", Toast.LENGTH_LONG).show();
    }

    private boolean updateRule(String id, String rule) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("rules").child(currentuserId).child(id);
        Rule rule1 = new Rule(id, rule);
        databaseReference.setValue(rule1);
        Toast.makeText(this, "Rule Updated", Toast.LENGTH_LONG).show();
        return true;
    }

    private void addRule() {
        String rule = editTextRule.getText().toString().trim();
        if (!TextUtils.isEmpty(rule)) {
            String ruleId = databaseRule.push().getKey();
            Rule rule1 = new Rule(ruleId, rule);
            databaseRule.child(ruleId).setValue(rule1);
            Toast.makeText(this, "Rule added", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "You should enter the rule", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
