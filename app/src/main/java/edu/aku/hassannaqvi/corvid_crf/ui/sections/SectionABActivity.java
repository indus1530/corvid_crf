package edu.aku.hassannaqvi.corvid_crf.ui.sections;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.validatorcrawler.aliazaz.Validator;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import edu.aku.hassannaqvi.corvid_crf.R;
import edu.aku.hassannaqvi.corvid_crf.contracts.FormsContract;
import edu.aku.hassannaqvi.corvid_crf.core.DatabaseHelper;
import edu.aku.hassannaqvi.corvid_crf.core.MainApp;
import edu.aku.hassannaqvi.corvid_crf.databinding.ActivitySectionAbBinding;
import edu.aku.hassannaqvi.corvid_crf.ui.other.MainActivity;
import edu.aku.hassannaqvi.corvid_crf.utils.Util;

public class SectionABActivity extends AppCompatActivity {

    ActivitySectionAbBinding bi;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_ab);
        bi.setCallback(this);
        db = MainApp.appInfo.getDbHelper();
        MainApp.setGPS(this); // Set GPS

    }


    public void BtnContinue() {
        if (formValidation()) {
            try {
                SaveDraft();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (UpdateDB()) {
                finish();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        }
    }


    private boolean UpdateDB() {
        long updcount = db.addForm(MainApp.fc);
        MainApp.fc.set_ID(String.valueOf(updcount));
        if (updcount > 0) {
            MainApp.fc.set_UID(MainApp.fc.getDeviceID() + MainApp.fc.get_ID());
            db.updatesFormColumn(FormsContract.FormsTable.COLUMN_UID, MainApp.fc.get_UID());
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }

    }

    private void SaveDraft() throws JSONException {

        MainApp.fc = new FormsContract();
        MainApp.fc.setFormDate(new SimpleDateFormat("dd-MM-yy HH:mm").format(new Date().getTime()));
        MainApp.fc.setUser(MainApp.userName);
        MainApp.fc.setDeviceID(MainApp.appInfo.getDeviceID());
        MainApp.fc.setDevicetagID(MainApp.appInfo.getTagName());
        MainApp.fc.setAppversion(MainApp.appInfo.getAppVersion());
        /*MainApp.fc.setClusterCode(bi.a101.getText().toString());
        MainApp.fc.setHhno(bi.a112.getText().toString());
        MainApp.fc.setLuid(bl.getLUID());*/
        //MainApp.setGPS(this); // Set GPS

        JSONObject json = new JSONObject();

        /*json.put("imei", MainApp.IMEI);
        json.put("rndid", bl.get_ID());
        json.put("luid", bl.getLUID());
        json.put("randDT", bl.getRandomDT());
        json.put("hh03", bl.getStructure());
        json.put("hh07", bl.getExtension());
        json.put("hhhead", bl.getHhhead());
        json.put("hh09", bl.getContact());
        json.put("hhss", bl.getSelStructure());
        json.put("hhheadpresent", bi.checkHHHeadpresent.isChecked() ? "1" : "2");
        json.put("hhheadpresentnew", bi.newHHheadname.getText().toString());*/

        json.put("A1", bi.a1.getText().toString());

        json.put("A2", bi.a2.getText().toString());

        json.put("A3", bi.a3a.isChecked() ? "1"
                : bi.a3b.isChecked() ? "2"
                : "0");

        json.put("A4", bi.a4a.isChecked() ? "1"
                : bi.a4b.isChecked() ? "2"
                : bi.a4c.isChecked() ? "3"
                : bi.a498.isChecked() ? "98"
                : "0");

        json.put("A51", bi.a51a.isChecked() ? "1"
                : bi.a51b.isChecked() ? "2"
                : "0");

        json.put("A52", bi.a52a.isChecked() ? "1"
                : bi.a52b.isChecked() ? "2"
                : "0");

        json.put("A53", bi.a53a.isChecked() ? "1"
                : bi.a53b.isChecked() ? "2"
                : "0");

        json.put("A54", bi.a54a.isChecked() ? "1"
                : bi.a54b.isChecked() ? "2"
                : "0");

        json.put("A55", bi.a55a.isChecked() ? "1"
                : bi.a55b.isChecked() ? "2"
                : "0");

        json.put("A56", bi.a56a.isChecked() ? "1"
                : bi.a56b.isChecked() ? "2"
                : "0");

        json.put("A57", bi.a57a.isChecked() ? "1"
                : bi.a57b.isChecked() ? "2"
                : "0");

        json.put("A61", bi.a61a.isChecked() ? "1"
                : bi.a61b.isChecked() ? "2"
                : "0");

        json.put("A62", bi.a62a.isChecked() ? "1"
                : bi.a62b.isChecked() ? "2"
                : "0");

        json.put("A63", bi.a63a.isChecked() ? "1"
                : bi.a63b.isChecked() ? "2"
                : "0");

        json.put("A64", bi.a64a.isChecked() ? "1"
                : bi.a64b.isChecked() ? "2"
                : "0");

        json.put("A65", bi.a65a.isChecked() ? "1"
                : bi.a65b.isChecked() ? "2"
                : "0");

        json.put("A66", bi.a66a.isChecked() ? "1"
                : bi.a66b.isChecked() ? "2"
                : "0");

        json.put("A67", bi.a67a.isChecked() ? "1"
                : bi.a67b.isChecked() ? "2"
                : "0");

        json.put("A66", bi.a66a.isChecked() ? "1"
                : bi.a66b.isChecked() ? "2"
                : "0");

        json.put("A69", bi.a69a.isChecked() ? "1"
                : bi.a69b.isChecked() ? "2"
                : "0");

        json.put("A610", bi.a610a.isChecked() ? "1"
                : bi.a610b.isChecked() ? "2"
                : "0");

        json.put("A611", bi.a611a.isChecked() ? "1"
                : bi.a611b.isChecked() ? "2"
                : "0");

        json.put("A612", bi.a612a.isChecked() ? "1"
                : bi.a612b.isChecked() ? "2"
                : "0");

        json.put("A613", bi.a613a.isChecked() ? "1"
                : bi.a613b.isChecked() ? "2"
                : "0");
        json.put("A61396", bi.a61396.getText().toString());

        json.put("A71", bi.a71a.isChecked() ? "1"
                : bi.a71b.isChecked() ? "2"
                : "0");

        json.put("A72", bi.a72a.isChecked() ? "1"
                : bi.a72b.isChecked() ? "2"
                : "0");

        json.put("A73", bi.a73a.isChecked() ? "1"
                : bi.a73b.isChecked() ? "2"
                : "0");

        json.put("A74", bi.a74a.isChecked() ? "1"
                : bi.a74b.isChecked() ? "2"
                : "0");

        json.put("A75", bi.a75a.isChecked() ? "1"
                : bi.a75b.isChecked() ? "2"
                : "0");

        json.put("A77", bi.a77a.isChecked() ? "1"
                : bi.a77b.isChecked() ? "2"
                : "0");

        json.put("A77", bi.a77a.isChecked() ? "1"
                : bi.a77b.isChecked() ? "2"
                : "0");

        json.put("A77", bi.a77a.isChecked() ? "1"
                : bi.a77b.isChecked() ? "2"
                : "0");

        json.put("A79", bi.a79a.isChecked() ? "1"
                : bi.a79b.isChecked() ? "2"
                : "0");

        json.put("A710", bi.a710a.isChecked() ? "1"
                : bi.a710b.isChecked() ? "2"
                : "0");

        json.put("A711", bi.a711a.isChecked() ? "1"
                : bi.a711b.isChecked() ? "2"
                : "0");

        json.put("A712", bi.a712a.isChecked() ? "1"
                : bi.a712b.isChecked() ? "2"
                : "0");

        json.put("A713", bi.a713a.isChecked() ? "1"
                : bi.a713b.isChecked() ? "2"
                : "0");
        json.put("A71396", bi.a71396.getText().toString());

        MainApp.fc.setsInfo(String.valueOf(json));
        MainApp.fc.setIstatus("1");
        MainApp.fc.setEndingdatetime(new SimpleDateFormat("dd-MM-yy HH:mm").format(new Date().getTime()));

        Toast.makeText(this, "Form Submitted Successfully...", Toast.LENGTH_LONG).show();

    }


    private boolean formValidation() {
        return Validator.emptyCheckingContainer(this, bi.fldGrpSectionA);
    }

    public void BtnEnd() {
        if (formValidation()) {
            Util.contextEndActivity(this);
        }
    }

}
