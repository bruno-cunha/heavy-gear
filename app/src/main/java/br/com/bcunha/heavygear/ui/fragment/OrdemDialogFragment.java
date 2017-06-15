package br.com.bcunha.heavygear.ui.fragment;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;

import br.com.bcunha.heavygear.R;
import br.com.bcunha.heavygear.ui.activities.HeavyGearActivity;

/**
 * Created by bruno on 05/06/17.
 */

public class OrdemDialogFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.ordem)
        .setSingleChoiceItems(R.array.ordem, 0, new DialogInterface.OnClickListener() {
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
