package com.sjtu.handtohandnew.Activity;

/*import androidx.appcompat.app.AppCompatActivity;*/
import android.os.Bundle;
import com.sjtu.handtohandnew.Base.BaseActivity;
import com.sjtu.handtohandnew.Base.BaseInterface;
import com.sjtu.handtohandnew.R;

public class AddressActivity extends BaseActivity implements BaseInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        InitView();
        InitData();
        InitViewOper();
    }

    @Override
    public void InitView() {

    }

    @Override
    public void InitData() {

    }

    @Override
    public void InitViewOper() {

    }
}
