package com.example.presidentapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.widgets.Snapshot;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Rules extends AppCompatActivity{
    EditText editTextRule;
    Button btnAddRule;

    DatabaseReference databaseRule;

    ListView listViewRules;

    List<Rule> ruleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);

        databaseRule = FirebaseDatabase.getInstance().getReference("rules");

        editTextRule = (EditText) findViewById(R.id.add_rule_txtfld);
        btnAddRule = findViewById(R.id.add_rule_btn);

        listViewRules = findViewById(R.id.listViewRules);

        ruleList = new ArrayList<>();
        btnAddRule.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                addRule();
                editTextRule.setText(null);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseRule.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                ruleList.clear();

                for(DataSnapshot ruleSnapshot : dataSnapshot.getChildren()){
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

    private void addRule(){
        String rule =  editTextRule.getText().toString().trim();

        if(!TextUtils.isEmpty(rule)){
               String id = databaseRule.push().getKey();
               Rule rule1 = new Rule(rule);
               databaseRule.child(id).setValue(rule1);
               Toast.makeText(this,"Rule added",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "You should enter the rule", Toast.LENGTH_LONG).show();
        }
    }
}
