package br.com.bcunha.heavygear.ui.fragment;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;

import br.com.bcunha.heavygear.R;
import br.com.bcunha.heavygear.ui.activities.ConfiguracaoActivity;
import br.com.bcunha.heavygear.ui.activities.HeavyGearActivity;

/**
 * Created by bruno on 05/06/17.
 */

public class OrdemDialogFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        int idOrdem = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext())
                                       .getInt(ConfiguracaoActivity.PREF_ID_ORDEM, 2);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.ordem)
               .setSingleChoiceItems(R.array.ordem, idOrdem, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                HeavyGearActivity heavyGearActivity = ((HeavyGearActivity) getActivity());
                heavyGearActivity.prefIdOrdem = which;
                heavyGearActivity.atualizaOrdemExibicao();
                dismiss();
            }
        });
        return builder.create();
    }
}
