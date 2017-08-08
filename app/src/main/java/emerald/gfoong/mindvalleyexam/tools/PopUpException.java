package emerald.gfoong.mindvalleyexam.tools;

import android.content.DialogInterface;

import java.util.HashMap;

import emerald.gfoong.mindvalleyexam.R;


/**
 * Copyright (C) 2016 - 2017 Steven Foong Ghin Yew <stevenfgy@yahoo.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

public class PopUpException extends Exception {
	public static final int MESSAGE_TOAST   = 0;
	public static final int MESSAGE_1_BTN   = 1;
	public static final int MESSAGE_2_BTN   = 2;
	public static final int MESSAGE_3_BTN   = 3;

	private HashMap<Short, ButtonWrapper> buttonMap;
	private DialogInterface.OnCancelListener backPressListener;
	private int errorType;
	private String errorMsg;
	private int errorIcon;

	public PopUpException(int errorType){
		this.errorType      = errorType;
		this.errorMsg       = null;
		this.errorIcon		= R.drawable.msg_icon_timer;
	}

	public PopUpException(String errMsg, int errType, int errIcon){
		super(errMsg);
		this.errorType      = errType;
		this.errorMsg       = errMsg;
		this.errorIcon		= errIcon;
		this.buttonMap      = new HashMap<>();
	}

	public void setListener(short buttonPosition,
							String btnText,
							DialogInterface.OnClickListener listener){
		buttonMap.put(buttonPosition, new ButtonWrapper(btnText, listener));
	}

	public void setBackPressListener(DialogInterface.OnCancelListener backPressListener) {
		this.backPressListener = backPressListener;
	}

	public DialogInterface.OnClickListener getListener(short buttonPosition){
		return buttonMap.get(buttonPosition).getOnClickListener();
	}

	public String getBtnText(short buttonPosition){
		return buttonMap.get(buttonPosition).getButtonText();
	}

	public DialogInterface.OnCancelListener getBackPressListener() {
		if(backPressListener == null){
			return new DialogInterface.OnCancelListener() {
				@Override
				public void onCancel(DialogInterface dialog) {
					dialog.cancel();
				}
			};
		} else {
			return backPressListener;
		}
	}

	public int getErrorType(){
		return errorType;
	}

	public String getErrorMsg() {
		if(errorMsg == null){
			errorMsg = "";
		}
		return errorMsg;
	}

	public int getErrorIcon(){
		return errorIcon;
	}

}
