package my.book.musicplayer.tool;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import my.book.musicplayer.R;

public class XfDialog extends Dialog  {
	public XfDialog(Context context) {
		super(context);
	}

	public XfDialog(Context context, int theme) {
		super(context, theme);
	}
	public static class Builder {

		private Context context;
		private int mIcon = -1;// ��ʾͼ��
		private CharSequence mTitle;// ��ʾ����
		private CharSequence mMessage;// ��ʾ����
		private CharSequence mPositiveButtonText;// ȷ����ť�ı�
		private CharSequence mNegativeButtonText;// �ܾ�ť�ı�
		private CharSequence mNeutralButtonText;// �м䰴ť�ı�
		private boolean mCancelable = true;// �Ƿ�����ȡ���
		private int mViewSpacingLeft;
		private int mViewSpacingTop;
		private int mViewSpacingRight;
		private int mViewSpacingBottom;
		private boolean mViewSpacingSpecified = false;

		// ��ʾ����View
		private View mView;

		private OnClickListener mPositiveButtonClickListener,
				mNegativeButtonClickListener, mNeutralButtonClickListener;
		private OnCancelListener mCancelListener;// ȡ����¼�
		private OnKeyListener mKeyListener;// ������

		public Builder(Context context) {
			this.context = context;
		}

		public Builder setMessage(CharSequence message) {
			this.mMessage = message;
			return this;
		}

		public Builder setMessage(int message) {
			this.mMessage = context.getText(message);
			return this;
		}

		public Builder setTitle(int title) {
			this.mTitle = context.getText(title);
			return this;
		}

		public Builder setTitle(CharSequence title) {
			this.mTitle = title;
			return this;
		}

		public Builder setIcon(int icon) {
			this.mIcon = icon;
			return this;
		}

		public Builder setView(View view) {
			this.mView = view;
			mViewSpacingSpecified = false;
			return this;
		}

		public Builder setView(View view, int left, int top, int right,
				int bottom) {
			this.mView = view;
			this.mViewSpacingLeft = left;
			this.mViewSpacingTop = top;
			this.mViewSpacingRight = right;
			this.mViewSpacingBottom = bottom;
			mViewSpacingSpecified = true;
			return this;
		}

		public Builder setPositiveButton(int textId,
				final OnClickListener listener) {
			this.mPositiveButtonText = context.getText(textId);
			this.mPositiveButtonClickListener = listener;
			return this;
		}

		public Builder setPositiveButton(String text,
				final OnClickListener listener) {
			this.mPositiveButtonText = text;
			this.mPositiveButtonClickListener = listener;
			return this;
		}

		public Builder setNeutralButton(int textId,
				final OnClickListener listener) {
			this.mNeutralButtonText = context.getText(textId);
			this.mNeutralButtonClickListener = listener;
			return this;
		}

		public Builder setNeutralButton(String text,
				final OnClickListener listener) {
			this.mNeutralButtonText = text;
			this.mNeutralButtonClickListener = listener;
			return this;
		}

		public Builder setNegativeButton(int textId,
				final OnClickListener listener) {
			this.mNegativeButtonText = context.getText(textId);
			this.mNegativeButtonClickListener = listener;
			return this;
		}

		public Builder setNegativeButton(String text,
				final OnClickListener listener) {
			this.mNegativeButtonText = text;
			this.mNegativeButtonClickListener = listener;
			return this;
		}

		public Builder setCancelable(boolean cancelable) {
			this.mCancelable = cancelable;
			return this;
		}

		public Builder setOnCancelListener(OnCancelListener listener) {
			this.mCancelListener = listener;
			return this;
		}

		public Builder setOnKeyListener(OnKeyListener listener) {
			this.mKeyListener = listener;
			return this;
		}

		public XfDialog create() {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			final XfDialog dialog = new XfDialog(context, R.style.XfDialog);
			dialog.setCancelable(mCancelable);
			// ����ȡ����¼�
			if (mCancelListener != null) {
				dialog.setOnCancelListener(mCancelListener);
			}
			// ���ü��̼����¼�
			if (mKeyListener != null) {
				dialog.setOnKeyListener(mKeyListener);
			}
			// ��ȡ�Ի��򲼾�
			View layout = inflater.inflate(R.layout.alert_dialog,
					(ViewGroup) (((Activity) context)
							.findViewById(R.id.parentPanel)));
			layout.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
					LayoutParams.WRAP_CONTENT));
			// ���ñ���
			((TextView) layout.findViewById(R.id.alertTitle)).setText(mTitle);
			// ����ͼ��
			if (mIcon != -1) {
				((ImageView) layout.findViewById(R.id.icon))
						.setBackgroundResource(mIcon);
			}
			int count = 0;
			// ����ȷ����ť
			if(setButton(layout, mPositiveButtonText, R.id.button1, dialog, mPositiveButtonClickListener)) count++;
			// ���þܾ�ť
			if(setButton(layout, mNegativeButtonText, R.id.button2, dialog, mNegativeButtonClickListener)) count++;
			// �����м䰴ť
			if(setButton(layout, mNeutralButtonText, R.id.button3, dialog, mNeutralButtonClickListener)) count++;
			
			if(count==0){
				layout.findViewById(R.id.buttonPanel).setVisibility(View.GONE);
			}
			//һ����ťʱ����ʾ���߿ռ�
			if (count == 1) {
				layout.findViewById(R.id.leftSpacer)
						.setVisibility(View.INVISIBLE);
				layout.findViewById(R.id.rightSpacer).setVisibility(
						View.INVISIBLE);
			}
			// ������ʾ��Ϣ
			if (!TextUtils.isEmpty(mMessage)) {
				((TextView) layout.findViewById(R.id.message))
						.setText(mMessage);
			} else {
				((LinearLayout) layout.findViewById(R.id.contentPanel))
						.setVisibility(View.GONE);
			}
			// ������ʾ���ݲ���
			if (mView != null) {
				final FrameLayout customPanel = (FrameLayout) layout
						.findViewById(R.id.customPanel);
				if (mViewSpacingSpecified) {
					customPanel.setPadding(mViewSpacingLeft, mViewSpacingTop,
							mViewSpacingRight, mViewSpacingBottom);
				}
				customPanel.addView(mView);
			} else {
				((FrameLayout) layout.findViewById(R.id.customPanel))
						.setVisibility(View.GONE);
			}
			dialog.setContentView(layout);
			return dialog;
		}

		public XfDialog show() {
			XfDialog dialog = create();
			dialog.show();
			return dialog;
		}

		private boolean setButton(View layout, CharSequence mPositiveButtonText2, int id,
				final Dialog dialog, final OnClickListener listener) {
			if (!TextUtils.isEmpty(mPositiveButtonText2)) {
				final Button button1 = (Button) layout.findViewById(id);
				button1.setText(mPositiveButtonText2);
				if (listener != null) {
					button1.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View v) {
							listener.onClick(dialog,
									DialogInterface.BUTTON_POSITIVE);
						}
					});
				}else{
					//Ĭ���¼�Ϊ�رնԻ���
					button1.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View v) {
							dialog.cancel();
							dialog.dismiss();
						}
					});
				}
				return true;
			} else {
				layout.findViewById(id).setVisibility(View.GONE);
				return false;
			}
		}
	}
}
