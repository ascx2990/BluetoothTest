package com.example.bluetoothtest1;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AlertDialogFragment extends DialogFragment {
	private String Title;
	private String Message;
	private int buttonCount;
	private OnDialogFragmentClickListener listener;
	private OnDialogFragmentClickListener cancleListener;
	private OnDialogFragmentClickListener neutralListener;
	private int checkId;
	private int falseId1111;
	private int neutralId;
	private AlertDialog dialog;

	/**
	 * @param Title
	 * @param Message
	 * @param buttonCount
	 *            button���ƶq,���p1�ӷ|���HNeutralButton���D
	 * @param listener
	 */
	public AlertDialogFragment(String Title, String Message, int buttonCount, OnDialogFragmentClickListener listener,
			OnDialogFragmentClickListener cancleListener) {
		// TODO Auto-generated constructor stub
		this.Title = Title;
		this.Message = Message;
		this.buttonCount = buttonCount;
		this.listener = listener;
		this.cancleListener = cancleListener;
		checkId = R.string.dialog_check;
		falseId = R.string.dialog_cancel;
	}

	public AlertDialogFragment(String Title, String Message, int buttonCount, OnDialogFragmentClickListener listener,
			OnDialogFragmentClickListener cancleListener, int checkId, int cancelId) {
		// TODO Auto-generated constructor stub
		this.Title = Title;
		this.Message = Message;
		this.buttonCount = buttonCount;
		this.listener = listener;
		this.cancleListener = cancleListener;
		this.checkId = checkId;
		this.falseId = cancelId;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setCancelable(false);
	}

	public AlertDialogFragment(String Title, String Message, int buttonCount, OnDialogFragmentClickListener listener,
			int checkId, int falseId) {
		// TODO Auto-generated constructor stub
		this.Title = Title;
		this.Message = Message;
		this.buttonCount = buttonCount;
		this.listener = listener;
		this.checkId = checkId;
		this.falseId = falseId;

	}

	public AlertDialogFragment(String Title, String Message, int buttonCount,
			OnDialogFragmentClickListener checkListener, OnDialogFragmentClickListener falseListener,
			OnDialogFragmentClickListener neutralListener, int checkId, int falseId, int neutralId) {
		this.Title = Title;
		this.Message = Message;
		this.buttonCount = buttonCount;
		this.listener = checkListener;
		this.cancleListener = falseListener;
		this.neutralListener = neutralListener;
		this.checkId = checkId;
		this.falseId = falseId;
		this.neutralId = neutralId;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(getActivity(), R.style.Dialog_Style);
		Builder builder = new AlertDialog.Builder(contextThemeWrapper);
		builder.setTitle(Title);
		LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		TextView tv = (TextView) inflater.inflate(R.layout.diaolg_textview, null);
		tv.setText(Message);

		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT, 1.0f);
		tv.setLayoutParams(params);
		builder.setView(tv);
		builder.setPositiveButton(checkId, new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				if (listener != null) {
					listener.onClick();
				}
			}
		});
		if (buttonCount > 2) {
			builder.setNeutralButton(neutralId, new DialogInterface.OnClickListener() {

				public void onClick(DialogInterface dialog, int which) {
					if (neutralListener != null) {
						neutralListener.onClick();
					}
				}
			});
		}
		if (buttonCount > 1) {
			builder.setNegativeButton(falseId, new DialogInterface.OnClickListener() {

				public void onClick(DialogInterface dialog, int which) {
					if (cancleListener != null) {
						cancleListener.onClick();
					}
				}
			});

		}
		dialog = builder.show();
		Button btnPositive = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
		Button btnNeutral = dialog.getButton(AlertDialog.BUTTON_NEUTRAL);
		Button btnNegative = dialog.getButton(AlertDialog.BUTTON_NEGATIVE);
		btnPositive.setTextSize(getResources().getDimension(R.dimen.dialog_button_size));
		btnNeutral.setTextSize(getResources().getDimension(R.dimen.dialog_button_size));
		btnNegative.setTextSize(getResources().getDimension(R.dimen.dialog_button_size));
		return dialog;
	}

	public interface OnDialogFragmentClickListener {
		void onClick();
	}

}
