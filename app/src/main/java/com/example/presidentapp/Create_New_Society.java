package com.example.presidentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.ConsumerIrManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.presidentapp.Model.CreateNewSocietyModel;
import com.example.presidentapp.Model.EventModel;
import com.example.presidentapp.Model.Rule;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.Array;
import java.sql.Struct;
import java.util.ArrayList;

public class Create_New_Society extends AppCompatActivity{

    Spinner sp_state, sp_city, sp_buildingtype;
    ArrayList<String> arrayList_parent;
    ArrayAdapter<String> arrayAdapter_parent;

    ArrayList<String> arrayList_AndhraPradesh, arrayList_ArunachalPradesh, arrayList_Assam, arrayList_Bihar, arrayList_Chandigarh,
            arrayList_Chhattisgarh, arrayList_Dadra_Nagar_Haveli, arrayList_Daman_and_Diu, arrayList_Delhi, arrayList_Goa, arrayList_Gujarat,
    arrayList_Haryana, arrayList_HimachalPradesh, arrayList_JammuKashmir, arrayList_Jharkhand, arrayList_Karnataka, arrayList_Kerala,
    arrayList_Lakshadweep, arrayList_MadhyaPradesh, arrayList_Maharashtra, arrayList_Manipur, arrayList_Meghalaya, arrayList_Mizoram,
    arrayList_Nagaland, arrayList_Odisha, arrayList_Puducherry, arrayList_Punjab, arrayList_Rajasthan, arrayList_Sikkim, arrayList_TamilNadu,
    arrayList_Telangana, arrayList_Tripura, arrayList_Uttarakhand, arrayList_UttarPradesh, arrayList_WestBengal;

    ArrayAdapter<String> arrayAdapter_child;

    Button btnCreateNewSociety;
    DatabaseReference databaseReferenceNewSociety;
    TextInputEditText buildingname, buildingwings, buildingaddress;
    String get1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create__new__society);

        sp_state = findViewById(R.id.building_state_spinner);
        sp_city = findViewById(R.id.building_city_spinner);
        sp_buildingtype = findViewById(R.id.building_type_spinner);
        btnCreateNewSociety = findViewById(R.id.submit_new_society_button);
        buildingname = findViewById(R.id.building_name);
        buildingwings = findViewById(R.id.building_wings);
        buildingaddress = findViewById(R.id.building_address);
        databaseReferenceNewSociety = FirebaseDatabase.getInstance().getReference("New Buildings");
        arrayList_parent = new ArrayList<>();
        arrayList_parent.add("Andhra Paresh");
        arrayList_parent.add("Arunachal Pradesh");
        arrayList_parent.add("Assam");
        arrayList_parent.add("Bihar");
        arrayList_parent.add("Chandigarh");
        arrayList_parent.add("Chhattisgarh");
        arrayList_parent.add("Dadra and Nagar Haveli (UT)");
        arrayList_parent.add("Daman and Diu (UT)");
        arrayList_parent.add("Delhi ");
        arrayList_parent.add("Goa");
        arrayList_parent.add("Gujarat");
        arrayList_parent.add("Haryana");
        arrayList_parent.add("Himachal Pradesh");
        arrayList_parent.add("Jammu and Kashmir");
        arrayList_parent.add("Jharkhand");
        arrayList_parent.add("Karnataka");
        arrayList_parent.add("Kerala");
        arrayList_parent.add("Lakshadweep (UT)");
        arrayList_parent.add("Madhya Pradesh");
        arrayList_parent.add("Maharashtra");
        arrayList_parent.add("Manipur");
        arrayList_parent.add("Meghalaya");
        arrayList_parent.add("Mizoram");
        arrayList_parent.add("Nagaland");
        arrayList_parent.add("Odisha");
        arrayList_parent.add("Puducherry (UT)");
        arrayList_parent.add("Punjab");
        arrayList_parent.add("Rajasthan");
        arrayList_parent.add("Sikkim");
        arrayList_parent.add("Tamil Nadu");
        arrayList_parent.add("Telangana");
        arrayList_parent.add("Tripura");
        arrayList_parent.add("Uttarakhand");
        arrayList_parent.add("Uttar Pradesh");
        arrayList_parent.add("West Bengal");


        arrayAdapter_parent = new ArrayAdapter<>(getApplication(),android.R.layout.simple_spinner_dropdown_item,arrayList_parent);

        sp_state.setAdapter(arrayAdapter_parent);

        btnCreateNewSociety.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                CreateNewSociety();
                finish();
            }
        });

        //child City process
        //position 0
        arrayList_AndhraPradesh = new ArrayList<>();
        arrayList_AndhraPradesh.add("Anantapur");
        arrayList_AndhraPradesh.add("Chittoor");
        arrayList_AndhraPradesh.add("East Godavari");
        arrayList_AndhraPradesh.add("Guntur");
        arrayList_AndhraPradesh.add("Krishna");
        arrayList_AndhraPradesh.add("Kurnool");
        arrayList_AndhraPradesh.add("Nellore");
        arrayList_AndhraPradesh.add("Prakasam");
        arrayList_AndhraPradesh.add("Srikakulam");
        arrayList_AndhraPradesh.add("Visakhapatnam");
        arrayList_AndhraPradesh.add("Vizianagaram");
        arrayList_AndhraPradesh.add("West Godavari");
        arrayList_AndhraPradesh.add("YSR Kadapa");
        //position 1
        arrayList_ArunachalPradesh = new ArrayList<>();
        arrayList_ArunachalPradesh.add("Tawang");
        arrayList_ArunachalPradesh.add("West Kameng");
        arrayList_ArunachalPradesh.add("East Kameng");
        arrayList_ArunachalPradesh.add("Papum Pare");
        arrayList_ArunachalPradesh.add("Kurung Kumey");
        arrayList_ArunachalPradesh.add("Kra Daadi");
        arrayList_ArunachalPradesh.add("Lower Subansiri");
        arrayList_ArunachalPradesh.add("Upper Subansiri");
        arrayList_ArunachalPradesh.add("West Siang");
        arrayList_ArunachalPradesh.add("East Siang");
        arrayList_ArunachalPradesh.add("Siang");
        arrayList_ArunachalPradesh.add("Upper Siang");
        arrayList_ArunachalPradesh.add("Lower Siang");
        arrayList_ArunachalPradesh.add("Lower Dibang Valley");
        arrayList_ArunachalPradesh.add("Dibang Valley");
        arrayList_ArunachalPradesh.add("Anjaw");
        arrayList_ArunachalPradesh.add("Lohit");
        arrayList_ArunachalPradesh.add("Namsai");
        arrayList_ArunachalPradesh.add("Changlang");
        arrayList_ArunachalPradesh.add("Tirap");
        arrayList_ArunachalPradesh.add("Longding");
        //position 2
        arrayList_Assam = new ArrayList<>();
        arrayList_Assam.add("Baksa");
        arrayList_Assam.add("Barpeta");
        arrayList_Assam.add("Biswanath");
        arrayList_Assam.add("Baksa");
        arrayList_Assam.add("Bongaigaon");
        arrayList_Assam.add("Cachar");
        arrayList_Assam.add("Charaideo");
        arrayList_Assam.add("Chirang");
        arrayList_Assam.add("Darrang");
        arrayList_Assam.add("Dhemaji");
        arrayList_Assam.add("Dhubri");
        arrayList_Assam.add("Dibrugarh");
        arrayList_Assam.add("Goalpara");
        arrayList_Assam.add("Golaghat");
        arrayList_Assam.add("Hailakandi");
        arrayList_Assam.add("Hojai");
        arrayList_Assam.add("Jorhat");
        arrayList_Assam.add("Kamrup Metropolitan");
        arrayList_Assam.add("Kamrup");
        arrayList_Assam.add("Karbi Anglong");
        arrayList_Assam.add("Karimganj");
        arrayList_Assam.add("Kokrajhar");
        arrayList_Assam.add("Lakhimpur");
        arrayList_Assam.add("Majuli");
        arrayList_Assam.add("Morigaon");
        arrayList_Assam.add("Nagaon");
        arrayList_Assam.add("Nalbari");
        arrayList_Assam.add("Dima Hasao");
        arrayList_Assam.add("Sivasagar");
        arrayList_Assam.add("Sonitpur");
        arrayList_Assam.add("South Salmara-Mankachar");
        arrayList_Assam.add("Tinsukia");
        arrayList_Assam.add("Udalguri");
        arrayList_Assam.add("West Karbi Anglong");
        //position 3
        arrayList_Bihar = new ArrayList<>();
        arrayList_Bihar.add("Araria");
        arrayList_Bihar.add("Arwal");
        arrayList_Bihar.add("Aurangabad");
        arrayList_Bihar.add("Banka");
        arrayList_Bihar.add("Begusarai");
        arrayList_Bihar.add("Bhagalpur");
        arrayList_Bihar.add("Bhojpur");
        arrayList_Bihar.add("Buxar");
        arrayList_Bihar.add("Darbhanga");
        arrayList_Bihar.add("East Champaran (Motihari)");
        arrayList_Bihar.add("Gaya");
        arrayList_Bihar.add("Gopalganj");
        arrayList_Bihar.add("Jamui");
        arrayList_Bihar.add("Jehanabad");
        arrayList_Bihar.add("Kaimur (Bhabua)");
        arrayList_Bihar.add("Katihar");
        arrayList_Bihar.add("Khagaria");
        arrayList_Bihar.add("Kishanganj");
        arrayList_Bihar.add("Lakhisarai");
        arrayList_Bihar.add("Madhepura");
        arrayList_Bihar.add("Madhubani");
        arrayList_Bihar.add("Munger (Monghyr)");
        arrayList_Bihar.add("Muzaffarpur");
        arrayList_Bihar.add("Nalanda");
        arrayList_Bihar.add("Nawada");
        arrayList_Bihar.add("Patna");
        arrayList_Bihar.add("Purnia ");
        arrayList_Bihar.add("Rohtas");
        arrayList_Bihar.add("Saharsa");
        arrayList_Bihar.add("Samastipur");
        arrayList_Bihar.add("Sheikhpura");
        arrayList_Bihar.add("Sheohar");
        arrayList_Bihar.add("Sitamarhi");
        arrayList_Bihar.add("Siwan");
        arrayList_Bihar.add("Supaul");
        arrayList_Bihar.add("Vaishali");
        arrayList_Bihar.add("West Champaran");
        //position 4
        arrayList_Chandigarh = new ArrayList<>();
        arrayList_Chandigarh.add("Chandigarh");

        //positon 5
        arrayList_Chhattisgarh = new ArrayList<>();
        arrayList_Chhattisgarh.add("Balod");
        arrayList_Chhattisgarh.add("Baloda Bazar");
        arrayList_Chhattisgarh.add("Balrampur");
        arrayList_Chhattisgarh.add("Bastar");
        arrayList_Chhattisgarh.add("Bemetara");
        arrayList_Chhattisgarh.add("Bijapur");
        arrayList_Chhattisgarh.add("Bilaspur");
        arrayList_Chhattisgarh.add("Dantewada (South Bastar)");
        arrayList_Chhattisgarh.add("Dhamtari");
        arrayList_Chhattisgarh.add("Durg");
        arrayList_Chhattisgarh.add("Gariyaband");
        arrayList_Chhattisgarh.add("Janjgir-Champa");
        arrayList_Chhattisgarh.add("Jashpur");
        arrayList_Chhattisgarh.add("Kabirdham (Kawardha)");
        arrayList_Chhattisgarh.add("Kanker (North Bastar)");
        arrayList_Chhattisgarh.add("Kondagaon");
        arrayList_Chhattisgarh.add("Korba");
        arrayList_Chhattisgarh.add("Korea (Koriya)");
        arrayList_Chhattisgarh.add("Mahasamund");
        arrayList_Chhattisgarh.add("Mungeli");
        arrayList_Chhattisgarh.add("Narayanpur");
        arrayList_Chhattisgarh.add("Raigarh");
        arrayList_Chhattisgarh.add("Raipur");
        arrayList_Chhattisgarh.add("Rajnandgaon");
        arrayList_Chhattisgarh.add("Sukma");
        arrayList_Chhattisgarh.add("Surajpur");
        arrayList_Chhattisgarh.add("Surguja");
        //position 6
        arrayList_Dadra_Nagar_Haveli = new ArrayList<>();
        arrayList_Dadra_Nagar_Haveli.add("Dadra & Nagar Haveli");
        //position 7
        arrayList_Daman_and_Diu = new ArrayList<>();
        arrayList_Daman_and_Diu.add("Daman");
        arrayList_Daman_and_Diu.add("Diu");
        //position 8
        arrayList_Delhi = new ArrayList<>();
        arrayList_Delhi.add("Central Delhi");
        arrayList_Delhi.add("East Delhi");
        arrayList_Delhi.add("West Delhi");
        arrayList_Delhi.add("New Delhi");
        arrayList_Delhi.add("North Delhi");
        arrayList_Delhi.add("North East  Delhi");
        arrayList_Delhi.add("North West  Delhi");
        arrayList_Delhi.add("Shahdara");
        arrayList_Delhi.add("South Delhi");
        arrayList_Delhi.add("South East Delhi");
        arrayList_Delhi.add("South West Delhi");
        arrayList_Delhi.add("West Delhi");
       //position 9
        arrayList_Goa = new ArrayList<>();
        arrayList_Goa.add("North Goa");
        arrayList_Goa.add("South Goa");
        //position 10
        arrayList_Gujarat = new ArrayList<>();
        arrayList_Gujarat.add("Ahmedabad");
        arrayList_Gujarat.add("Amreli");
        arrayList_Gujarat.add("Anand");
        arrayList_Gujarat.add("Aravalli");
        arrayList_Gujarat.add("Banaskantha (Palanpur)");
        arrayList_Gujarat.add("Bharuch");
        arrayList_Gujarat.add("Bhavnagar");
        arrayList_Gujarat.add("Botad");
        arrayList_Gujarat.add("Chhota Udepur");
        arrayList_Gujarat.add("Dahod");
        arrayList_Gujarat.add("Dangs (Ahwa)");
        arrayList_Gujarat.add("Devbhoomi Dwarka");
        arrayList_Gujarat.add("Gandhinagar");
        arrayList_Gujarat.add("Gir Somnath");
        arrayList_Gujarat.add("Jamnagar");
        arrayList_Gujarat.add("Junagadh");
        arrayList_Gujarat.add("Kachchh");
        arrayList_Gujarat.add("Kheda (Nadiad)");
        arrayList_Gujarat.add("Mahisagar");
        arrayList_Gujarat.add("Mehsana");
        arrayList_Gujarat.add("Morbi");
        arrayList_Gujarat.add("Narmada (Rajpipla)");
        arrayList_Gujarat.add("Navsari");
        arrayList_Gujarat.add("Panchmahal (Godhra)");
        arrayList_Gujarat.add("Patan");
        arrayList_Gujarat.add("Porbandar");
        arrayList_Gujarat.add("Rajkot");
        arrayList_Gujarat.add("Sabarkantha (Himmatnagar)");
        arrayList_Gujarat.add("Surat");
        arrayList_Gujarat.add("Surendranagar");
        arrayList_Gujarat.add("Tapi (Vyara)");
        arrayList_Gujarat.add("Vadodara");
        arrayList_Gujarat.add("Valsad");
        //position 11
        arrayList_Haryana = new ArrayList<>();
        arrayList_Haryana.add("Ambala");
        arrayList_Haryana.add("Bhiwani");
        arrayList_Haryana.add("Charkhi Dadri");
        arrayList_Haryana.add("Faridabad");
        arrayList_Haryana.add("Fatehabad");
        arrayList_Haryana.add("Gurgaon");
        arrayList_Haryana.add("Hisar");
        arrayList_Haryana.add("Jhajjar");
        arrayList_Haryana.add("Jind");
        arrayList_Haryana.add("Kaithal");
        arrayList_Haryana.add("Karnal");
        arrayList_Haryana.add("Kurukshetra");
        arrayList_Haryana.add("Mahendragarh");
        arrayList_Haryana.add("Mewat");
        arrayList_Haryana.add("Palwal");
        arrayList_Haryana.add("Panchkula");
        arrayList_Haryana.add("Panipat");
        arrayList_Haryana.add("Rewari");
        arrayList_Haryana.add("Rohtak");
        arrayList_Haryana.add("Sirsa");
        arrayList_Haryana.add("Sonipat");
        arrayList_Haryana.add("Yamunanagar");
        //position 12
        arrayList_HimachalPradesh = new ArrayList<>();
        arrayList_HimachalPradesh.add("Bilaspur");
        arrayList_HimachalPradesh.add("Chamba");
        arrayList_HimachalPradesh.add("Hamirpur");
        arrayList_HimachalPradesh.add("Kangra");
        arrayList_HimachalPradesh.add("Kinnaur");
        arrayList_HimachalPradesh.add("Kullu");
        arrayList_HimachalPradesh.add("Lahaul & Spiti");
        arrayList_HimachalPradesh.add("Mandi");
        arrayList_HimachalPradesh.add("Shimla");
        arrayList_HimachalPradesh.add("Sirmaur ");
        arrayList_HimachalPradesh.add("Solan");
        arrayList_HimachalPradesh.add("Una");
        //position 13
        arrayList_JammuKashmir = new ArrayList<>();
        arrayList_JammuKashmir.add("Anantnag");
        arrayList_JammuKashmir.add("Bandipore");
        arrayList_JammuKashmir.add("Baramulla");
        arrayList_JammuKashmir.add("Budgam");
        arrayList_JammuKashmir.add("Doda");
        arrayList_JammuKashmir.add("Ganderbal");
        arrayList_JammuKashmir.add("Jammu");
        arrayList_JammuKashmir.add("Kargil");
        arrayList_JammuKashmir.add("Kathua");
        arrayList_JammuKashmir.add("Kishtwar");
        arrayList_JammuKashmir.add("Kulgam");
        arrayList_JammuKashmir.add("Kupwara");
        arrayList_JammuKashmir.add("Leh");
        arrayList_JammuKashmir.add("Poonch");
        arrayList_JammuKashmir.add("Pulwama");
        arrayList_JammuKashmir.add("Rajouri");
        arrayList_JammuKashmir.add("Ramban");
        arrayList_JammuKashmir.add("Reasi");
        arrayList_JammuKashmir.add("Samba");
        arrayList_JammuKashmir.add("Shopian");
        arrayList_JammuKashmir.add("Srinagar");
        arrayList_JammuKashmir.add("Udhampur");
        //position 14
        arrayList_Jharkhand = new ArrayList<>();
        arrayList_Jharkhand.add("Bokaro");
        arrayList_Jharkhand.add("Chatra");
        arrayList_Jharkhand.add("Deoghar");
        arrayList_Jharkhand.add("Dhanbad");
        arrayList_Jharkhand.add("Dumka");
        arrayList_Jharkhand.add("East Singhbhum");
        arrayList_Jharkhand.add("Garhwa");
        arrayList_Jharkhand.add("Giridih");
        arrayList_Jharkhand.add("Godda");
        arrayList_Jharkhand.add("Gumla");
        arrayList_Jharkhand.add("Hazaribag");
        arrayList_Jharkhand.add("Jamtara");
        arrayList_Jharkhand.add("Khunti");
        arrayList_Jharkhand.add("Koderma");
        arrayList_Jharkhand.add("Latehar");
        arrayList_Jharkhand.add("Lohardaga");
        arrayList_Jharkhand.add("Pakur");
        arrayList_Jharkhand.add("Palamu");
        arrayList_Jharkhand.add("Ramgarh");
        arrayList_Jharkhand.add("Ranchi");
        arrayList_Jharkhand.add("Sahibganj");
        arrayList_Jharkhand.add("Seraikela-Kharsawan");
        arrayList_Jharkhand.add("Simdega");
        arrayList_Jharkhand.add("West Singhbhum");
       //position 15
        arrayList_Karnataka = new ArrayList<>();
        arrayList_Karnataka.add("Bagalkot");
        arrayList_Karnataka.add("Ballari (Bellary)");
        arrayList_Karnataka.add("Bengaluru (Bangalore) Rural");
        arrayList_Karnataka.add("Bengaluru (Bangalore) Urban");
        arrayList_Karnataka.add("Bidar");
        arrayList_Karnataka.add("Chamarajanagar");
        arrayList_Karnataka.add("Chikballapur");
        arrayList_Karnataka.add("Chikkamagaluru (Chikmagalur)");
        arrayList_Karnataka.add("Chitradurga");
        arrayList_Karnataka.add("Dakshina Kannada");
        arrayList_Karnataka.add("Davangere");
        arrayList_Karnataka.add("Dharwad");
        arrayList_Karnataka.add("Gadag");
        arrayList_Karnataka.add("Hassan");
        arrayList_Karnataka.add("Haveri");
        arrayList_Karnataka.add("Kalaburagi (Gulbarga)");
        arrayList_Karnataka.add("Kodagu");
        arrayList_Karnataka.add("Kolar");
        arrayList_Karnataka.add("Koppal");
        arrayList_Karnataka.add("Mandya");
        arrayList_Karnataka.add("Mysuru (Mysore)");
        arrayList_Karnataka.add("Raichur");
        arrayList_Karnataka.add("Ramanagara");
        arrayList_Karnataka.add("Shivamogga (Shimoga)");
        arrayList_Karnataka.add("Tumakuru (Tumkur)");
        arrayList_Karnataka.add("Udupi");
        arrayList_Karnataka.add("Uttara Kannada");
        arrayList_Karnataka.add("Vijayapura ");
        arrayList_Karnataka.add("Yadgir");
        //positon 16
        arrayList_Kerala = new ArrayList<>();
        arrayList_Kerala.add("Alappuzha");
        arrayList_Kerala.add("Ernakulam");
        arrayList_Kerala.add("Idukki");
        arrayList_Kerala.add("Kannur");
        arrayList_Kerala.add("Kasaragod");
        arrayList_Kerala.add("Kollam");
        arrayList_Kerala.add("Kottayam");
        arrayList_Kerala.add("Kozhikode");
        arrayList_Kerala.add("Malappuram");
        arrayList_Kerala.add("Palakkad");
        arrayList_Kerala.add("Pathanamthitta");
        arrayList_Kerala.add("Thiruvananthapuram");
        arrayList_Kerala.add("Thrissur");
        arrayList_Kerala.add("Wayanad");
        //position 17
        arrayList_Lakshadweep = new ArrayList<>();
        arrayList_Lakshadweep.add("Agatti");
        arrayList_Lakshadweep.add("Amini");
        arrayList_Lakshadweep.add("Androth");
        arrayList_Lakshadweep.add("Bithra");
        arrayList_Lakshadweep.add("Chethlath");
        arrayList_Lakshadweep.add("Kavaratti");
        arrayList_Lakshadweep.add("Kadmath");
        arrayList_Lakshadweep.add("Kalpeni");
        arrayList_Lakshadweep.add("Kilthan");
        arrayList_Lakshadweep.add("Minicoy");
        //position 18
        arrayList_MadhyaPradesh = new ArrayList<>();
        arrayList_MadhyaPradesh.add("Agar Malwa");
        arrayList_MadhyaPradesh.add("Alirajpur");
        arrayList_MadhyaPradesh.add("Anuppur");
        arrayList_MadhyaPradesh.add("Ashoknagar");
        arrayList_MadhyaPradesh.add("Balaghat");
        arrayList_MadhyaPradesh.add("Barwani");
        arrayList_MadhyaPradesh.add("Betul");
        arrayList_MadhyaPradesh.add("Bhind");
        arrayList_MadhyaPradesh.add("Bhopal");
        arrayList_MadhyaPradesh.add("Burhanpur");
        arrayList_MadhyaPradesh.add("Chhatarpur");
        arrayList_MadhyaPradesh.add("Chhindwara");
        arrayList_MadhyaPradesh.add("Damoh");
        arrayList_MadhyaPradesh.add("Datia");
        arrayList_MadhyaPradesh.add("Dewas");
        arrayList_MadhyaPradesh.add("Dhar");
        arrayList_MadhyaPradesh.add("Dindori");
        arrayList_MadhyaPradesh.add("Guna");
        arrayList_MadhyaPradesh.add("Gwalior");
        arrayList_MadhyaPradesh.add("Harda");
        arrayList_MadhyaPradesh.add("Hoshangabad");
        arrayList_MadhyaPradesh.add("Indore");
        arrayList_MadhyaPradesh.add("Jabalpur");
        arrayList_MadhyaPradesh.add("Jhabua");
        arrayList_MadhyaPradesh.add("Katni");
        arrayList_MadhyaPradesh.add("Khandwa");
        arrayList_MadhyaPradesh.add("Khargone");
        arrayList_MadhyaPradesh.add("Mandla");
        arrayList_MadhyaPradesh.add("Mandsaur");
        arrayList_MadhyaPradesh.add("Morena");
        arrayList_MadhyaPradesh.add("Narsinghpur");
        arrayList_MadhyaPradesh.add("Neemuch");
        arrayList_MadhyaPradesh.add("Panna");
        arrayList_MadhyaPradesh.add("Raisen");
        arrayList_MadhyaPradesh.add("Rajgarh");
        arrayList_MadhyaPradesh.add("Ratlam");
        arrayList_MadhyaPradesh.add("Rewa");
        arrayList_MadhyaPradesh.add("Sagar");
        arrayList_MadhyaPradesh.add("Satna");
        arrayList_MadhyaPradesh.add("Sehore");
        arrayList_MadhyaPradesh.add("Seoni");
        arrayList_MadhyaPradesh.add("Shahdol");
        arrayList_MadhyaPradesh.add("Shajapur");
        arrayList_MadhyaPradesh.add("Sheopur");
        arrayList_MadhyaPradesh.add("Shivpuri");
        arrayList_MadhyaPradesh.add("Sidhi");
        arrayList_MadhyaPradesh.add("Singrauli");
        arrayList_MadhyaPradesh.add("Tikamgarh");
        arrayList_MadhyaPradesh.add("Ujjain");
        arrayList_MadhyaPradesh.add("Umaria");
        arrayList_MadhyaPradesh.add("Vidisha");
        //positon 19
        arrayList_Maharashtra = new ArrayList<>();
        arrayList_Maharashtra.add("Ahmednagar");
        arrayList_Maharashtra.add("Akola");
        arrayList_Maharashtra.add("Amravati");
        arrayList_Maharashtra.add("Aurangabad");
        arrayList_Maharashtra.add("Beed");
        arrayList_Maharashtra.add("Bhandara");
        arrayList_Maharashtra.add("Buldhana");
        arrayList_Maharashtra.add("Chandrapur");
        arrayList_Maharashtra.add("Dhule");
        arrayList_Maharashtra.add("Gadchiroli");
        arrayList_Maharashtra.add("Gondia");
        arrayList_Maharashtra.add("Hingoli");
        arrayList_Maharashtra.add("Jalgaon");
        arrayList_Maharashtra.add("Jalna");
        arrayList_Maharashtra.add("Kolhapur");
        arrayList_Maharashtra.add("Latur");
        arrayList_Maharashtra.add("Mumbai City");
        arrayList_Maharashtra.add("Mumbai Suburban");
        arrayList_Maharashtra.add("Nagpur");
        arrayList_Maharashtra.add("Nanded");
        arrayList_Maharashtra.add("Nandurbar");
        arrayList_Maharashtra.add("Nashik");
        arrayList_Maharashtra.add("Osmanabad");
        arrayList_Maharashtra.add("Palghar");
        arrayList_Maharashtra.add("Parbhani");
        arrayList_Maharashtra.add("Pune");
        arrayList_Maharashtra.add("Raigad");
        arrayList_Maharashtra.add("Ratnagiri");
        arrayList_Maharashtra.add("Sangli");
        arrayList_Maharashtra.add("Satara");
        arrayList_Maharashtra.add("Sindhudurg");
        arrayList_Maharashtra.add("Solapur");
        arrayList_Maharashtra.add("Thane");
        arrayList_Maharashtra.add("Wardha");
        arrayList_Maharashtra.add("Washim");
        arrayList_Maharashtra.add("Yavatmal");
        //position 20
        arrayList_Manipur = new ArrayList<>();
        arrayList_Manipur.add("Bishnupur");
        arrayList_Manipur.add("Chandel");
        arrayList_Manipur.add("Churachandpur");
        arrayList_Manipur.add("Imphal East");
        arrayList_Manipur.add("Imphal West");
        arrayList_Manipur.add("Jiribam");
        arrayList_Manipur.add("Kakching");
        arrayList_Manipur.add("Kamjong");
        arrayList_Manipur.add("Kangpokpi");
        arrayList_Manipur.add("Noney");
        arrayList_Manipur.add("Pherzawl");
        arrayList_Manipur.add("Senapati");
        arrayList_Manipur.add("Tamenglong");
        arrayList_Manipur.add("Tengnoupal");
        arrayList_Manipur.add("Thoubal");
        arrayList_Manipur.add("Ukhrul");
        



        sp_state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    arrayAdapter_child=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,arrayList_AndhraPradesh);
                }
                if(position==1){
                    arrayAdapter_child=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,arrayList_ArunachalPradesh);
                }
                if(position==2){
                    arrayAdapter_child=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,arrayList_Assam);
                }
                if(position==3){
                    arrayAdapter_child=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,arrayList_Bihar);
                }
                if(position==4){
                    arrayAdapter_child=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,arrayList_Chandigarh);
                }
                if(position==5){
                    arrayAdapter_child=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,arrayList_Chhattisgarh);
                }
                if(position==6){
                    arrayAdapter_child=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,arrayList_Dadra_Nagar_Haveli);
                }
                if(position==7){
                    arrayAdapter_child=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,arrayList_Daman_and_Diu);
                }
                if(position==8){
                    arrayAdapter_child=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,arrayList_Delhi);
                }
                if(position==9){
                    arrayAdapter_child=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,arrayList_Goa);
                }
                if(position==10){
                    arrayAdapter_child=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,arrayList_Gujarat);
                }
                if(position==11){
                    arrayAdapter_child=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,arrayList_Haryana);
                }
                if(position==12){
                    arrayAdapter_child=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,arrayList_HimachalPradesh);
                }
                if(position==13){
                    arrayAdapter_child=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,arrayList_JammuKashmir);
                }
                if(position==14){
                    arrayAdapter_child=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,arrayList_Jharkhand);
                }
                if(position==15){
                    arrayAdapter_child=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,arrayList_Karnataka);
                }
                if(position==16){
                    arrayAdapter_child=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,arrayList_Kerala);
                }
                if(position==17){
                    arrayAdapter_child=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,arrayList_Lakshadweep);
                }
                if(position==18){
                    arrayAdapter_child=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,arrayList_MadhyaPradesh);
                }
                if(position==19){
                    arrayAdapter_child=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,arrayList_Maharashtra);
                }
                if(position==20){
                    arrayAdapter_child=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,arrayList_Manipur);
                }
                if(position==21){
                    arrayAdapter_child=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,arrayList_Meghalaya);
                }
                if(position==22){
                    arrayAdapter_child=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,arrayList_Mizoram);
                }
                if(position==23){
                    arrayAdapter_child=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,arrayList_Nagaland);
                }
                if(position==23){
                    arrayAdapter_child=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,arrayList_Odisha);
                }
                if(position==24){
                    arrayAdapter_child=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,arrayList_Puducherry);
                }
                if(position==25){
                    arrayAdapter_child=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,arrayList_Punjab);
                }
                if(position==26){
                    arrayAdapter_child=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,arrayList_Rajasthan);
                }
                if(position==27){
                    arrayAdapter_child=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,arrayList_Sikkim);
                }
                if(position==28){
                    arrayAdapter_child=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,arrayList_TamilNadu);
                }
                if(position==29){
                    arrayAdapter_child=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,arrayList_Telangana);
                }
                if(position==30){
                    arrayAdapter_child=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,arrayList_Tripura);
                }
                if(position==31){
                    arrayAdapter_child=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,arrayList_Uttarakhand);
                }
                if(position==32){
                    arrayAdapter_child=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,arrayList_UttarPradesh);
                }
                if(position==33){
                    arrayAdapter_child=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,arrayList_WestBengal);
                }
                sp_city.setAdapter(arrayAdapter_child);
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void CreateNewSociety(){

        String getbuildingtype =  sp_buildingtype.getSelectedItem().toString().trim();
        String getbuildingstate = sp_state.getSelectedItem().toString().trim();
        String getbuildingcity = sp_city.getSelectedItem().toString().trim();
        String getbuildingname = buildingname.getText().toString().trim();
        String getbuildingwings = buildingwings.getText().toString().trim();
        String getbuildingaddress = buildingaddress.getText().toString().trim();

        if(!TextUtils.isEmpty(getbuildingtype)){
            String buildingId = databaseReferenceNewSociety.push().getKey();
            CreateNewSocietyModel createNewSocietyModel = new CreateNewSocietyModel(buildingId, getbuildingtype,getbuildingname,getbuildingwings,getbuildingstate,getbuildingcity,getbuildingaddress);
            databaseReferenceNewSociety.child(buildingId).setValue(createNewSocietyModel);
            Toast.makeText(this,"New Society added",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "You should enter the type", Toast.LENGTH_LONG).show();
        }
    }
}
