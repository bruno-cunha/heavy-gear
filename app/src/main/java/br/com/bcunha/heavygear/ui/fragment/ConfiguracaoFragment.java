package br.com.bcunha.heavygear.ui.fragment;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;

import br.com.bcunha.heavygear.R;

/**
 * Created by bruno on 15/05/17.
 */

public class ConfiguracaoFragment extends PreferenceFragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferences);
    }
}
