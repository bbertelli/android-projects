package utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.support.v7.app.AlertDialog;


/**
 * Criado por Bruno Bertelli em 07/10/2016.
 *
 * Classe responsável em criar os Dialogs usados no app.
 */
public abstract class DialogUtils {

    /**
     * Cria e exibe um simples progress dialog que contém um título e uma mensagem.
     *
     * @param title título do dialog.
     * @param message mensagem do dialog.
     * @param activity tela que irá hospedar o dialog.
     * @return retorna um progress dialog para a tela que irá hospedar o dialog.
     */
    public static ProgressDialog showProgressSimple(String title, int message, Activity activity){
        return ProgressDialog.show(activity, title, activity.getString(message), true);
    }

    /**
     * Cria e exibe um AlertDialog simples apenas com mensagem informativa.
     *
     * @param title título do dialog.
     * @param message mensagem do dialog.
     * @param activity tela que irá hospedar o dialog.
     * @param okButton evento que será executado no botão ok, se caso for null
     *                 ele apenas fechará o dialog.
     */
    public static void showSimpleAlertDialog(String title, String message, Activity activity, OnClickListener okButton) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(title);
        builder.setMessage(message);

        if (okButton != null){
            builder.setPositiveButton(activity.getString(android.R.string.ok), okButton);
        }else {
            builder.setPositiveButton(activity.getString(android.R.string.ok), new OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
        }

        builder.create().show();
    }

    /**
     * Cria e exibe um AlertDialog com os botões de afirmação e negação
     * que podem implementar eventos customizados de clique.
     *
     * @param title título do dialog.
     * @param message mensagem do dialog.
     * @param activity tela que irá hospedar o dialog.
     * @param okButton evento que será executado no botão positivo.
     * @param cancelButton evento que será executado no botão negativo, se caso for null
     *                     ele apenas fechará o dialog.
     */
    public static void showCustomAlertDialog(int title, int message, Activity activity,
                                       OnClickListener okButton, String positiveBtnName,
                                             OnClickListener cancelButton, String negativeBtnName) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(false);

        if (positiveBtnName != null){
            builder.setPositiveButton(positiveBtnName, okButton);
        }else {
            builder.setPositiveButton(activity.getString(android.R.string.ok), okButton);
        }

        if (cancelButton != null) {
            if (negativeBtnName != null){
                builder.setNegativeButton(negativeBtnName, cancelButton);
            }else {
                builder.setNegativeButton(activity.getString(android.R.string.cancel), cancelButton);
            }
        } else {
            builder.setNegativeButton(activity.getString(android.R.string.cancel),
                new OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }
            );
        }

        builder.create().show();
    }
}
