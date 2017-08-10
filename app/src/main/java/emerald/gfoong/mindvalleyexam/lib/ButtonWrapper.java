package emerald.gfoong.mindvalleyexam.lib;

import android.content.DialogInterface;


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

public class ButtonWrapper {

	public static final short POSITIVE_BUTTON   = 0;
	public static final short NEUTRAL_BUTTON    = 1;
	public static final short NEGATIVE_BUTTON   = 2;

	private String buttonText;
	private DialogInterface.OnClickListener onClickListener;

	public ButtonWrapper(String buttonText, DialogInterface.OnClickListener onClickListener){
		this.buttonText         = buttonText;
		this.onClickListener    = onClickListener;
	}

	public DialogInterface.OnClickListener getOnClickListener() {
		return onClickListener;
	}

	public String getButtonText() {
		return buttonText;
	}
}
