package com.example.apple.demofragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements Fragment1.OnFirstFragmentListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment2 fragment2 = new Fragment2();
        Fragment1 fragment1 = new Fragment1();
        //if only have one Fragment ==> Portrait mode
        if(findViewById(R.id.contentFrame)!=null){

            //remove the exist fragment before add new one
            if(savedInstanceState!= null){
                getFragmentManager().executePendingTransactions();
                Fragment fragmentById = getFragmentManager().findFragmentById(R.id.contentFrame);
                getFragmentManager().beginTransaction().remove(fragmentById).commit();
            }
            // add new one
            getFragmentManager().beginTransaction().add(R.id.contentFrame,fragment1).commit();
        }else {
            //landscape mode
            if(savedInstanceState!= null){
                getFragmentManager().executePendingTransactions();
                Fragment firstFragmentById = getFragmentManager().findFragmentById(R.id.fragment1);

                if(firstFragmentById != null)
                getFragmentManager().beginTransaction().remove(firstFragmentById);
                Fragment secondFragmentById = getFragmentManager().findFragmentById(R.id.fragment2);
                if(secondFragmentById != null) {
                    getFragmentManager().beginTransaction().remove(secondFragmentById);
                }
            }
            getFragmentManager().beginTransaction().add(R.id.fragment1,fragment1 ).commit();
            getFragmentManager().beginTransaction().add(R.id.fragment2,fragment2).commit();
        }
    }

    @Override
    public void onItemPress(String message) {
        Fragment2 fragment2 = Fragment2.newInstance(message);
        if(findViewById(R.id.contentFrame) != null){
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.contentFrame,fragment2);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }else {
            getFragmentManager().beginTransaction().replace(R.id.fragment2,fragment2 ).commit();
        }

    }
}
